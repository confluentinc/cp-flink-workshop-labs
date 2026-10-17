package io.confluent.workshop.ci;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

public class OrdersJob {

    /** Keep only orders whose price is strictly greater than the threshold. */
    public static Table highValueOrders(Table orders, double threshold) {
        return orders.filter($("price").isGreater(threshold));
    }

    public static void main(String[] args) {
        EnvironmentSettings settings = EnvironmentSettings.inStreamingMode();
        TableEnvironment tEnv = TableEnvironment.create(settings);

        String bootstrap = System.getenv().getOrDefault(
            "KAFKA_BOOTSTRAP", "kafka.kafka.svc.cluster.local:9071");
        String srUrl = System.getenv().getOrDefault(
            "SCHEMA_REGISTRY_URL", "http://schemaregistry.kafka.svc.cluster.local:8081");
        double threshold = Double.parseDouble(
            System.getenv().getOrDefault("PRICE_THRESHOLD", "100.0"));

        // Source: DataGen mirroring examples.marketplace.orders
        tEnv.executeSql(
            "CREATE TABLE source_orders (" +
            "  order_id STRING NOT NULL," +
            "  customer_id INT NOT NULL," +
            "  product_id STRING NOT NULL," +
            "  price DOUBLE NOT NULL" +
            ") WITH (" +
            "  'connector' = 'datagen'," +
            "  'rows-per-second' = '10'," +
            "  'fields.order_id.length' = '10'," +
            "  'fields.product_id.length' = '6'," +
            "  'fields.customer_id.min' = '1'," +
            "  'fields.customer_id.max' = '1000'," +
            "  'fields.price.min' = '1.0'," +
            "  'fields.price.max' = '500.0'" +
            ")");

        // Sink: Kafka topic ci-app-orders, Avro via Confluent Schema Registry
        tEnv.executeSql(
            "CREATE TABLE sink_orders (" +
            "  order_id STRING NOT NULL," +
            "  customer_id INT NOT NULL," +
            "  product_id STRING NOT NULL," +
            "  price DOUBLE NOT NULL" +
            ") WITH (" +
            "  'connector' = 'kafka'," +
            "  'topic' = 'ci-app-orders'," +
            "  'properties.bootstrap.servers' = '" + bootstrap + "'," +
            "  'value.format' = 'avro-confluent'," +
            "  'value.avro-confluent.url' = '" + srUrl + "'," +
            "  'value.fields-include' = 'ALL'" +
            ")");

        highValueOrders(tEnv.from("source_orders"), threshold)
            .insertInto("sink_orders")
            .execute();
    }
}
