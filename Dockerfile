# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Download the Maven dependencies
RUN mvn dependency:go-offline

# Build the application
RUN mvn package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/cjva2-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
