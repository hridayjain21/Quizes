# Use Java 17 runtime image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the jar built from Maven
COPY target/QuizService-0.0.1-SNAPSHOT.jar app.jar

# Optional JVM tuning flags can be passed via JAVA_OPTS
ENV JAVA_OPTS=""

# Run the jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
