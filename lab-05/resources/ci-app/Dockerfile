FROM confluentinc/cp-flink:2.1.2-cp1-java21
# Application layer (changes frequently) - fat JAR onto the Flink classpath
COPY target/ci-app.jar /opt/flink/usrlib/ci-app.jar
