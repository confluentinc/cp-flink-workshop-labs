package io.confluent.workshop.ci;

import org.apache.flink.table.api.*;
import org.apache.flink.types.Row;
import org.apache.flink.util.CloseableIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.flink.table.api.Expressions.row;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdersJobTest {

    @Test
    void filterKeepsOnlyHighValueOrders() throws Exception {
        EnvironmentSettings settings = EnvironmentSettings.inStreamingMode();
        TableEnvironment tEnv = TableEnvironment.create(settings);

        Table input = tEnv.fromValues(
            DataTypes.ROW(
                DataTypes.FIELD("order_id", DataTypes.STRING().notNull()),
                DataTypes.FIELD("customer_id", DataTypes.INT().notNull()),
                DataTypes.FIELD("product_id", DataTypes.STRING().notNull()),
                DataTypes.FIELD("price", DataTypes.DOUBLE().notNull())),
            row("a", 1, "p1", 50.0),
            row("b", 2, "p2", 150.0),
            row("c", 3, "p3", 100.0),
            row("d", 4, "p4", 250.0));

        Table filtered = OrdersJob.highValueOrders(input, 100.0);

        List<String> ids = new ArrayList<>();
        try (CloseableIterator<Row> it = filtered.execute().collect()) {
            it.forEachRemaining(r -> ids.add((String) r.getField("order_id")));
        }
        ids.sort(String::compareTo);
        assertEquals(List.of("b", "d"), ids); // 50.0 and 100.0 excluded by > 100
    }
}
