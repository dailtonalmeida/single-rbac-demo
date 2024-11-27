# Use the official AWS Corretto base image
FROM amazoncorretto:21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
# Replace "target/app.jar" with the actual path to your JAR file
COPY target/single-rbac-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application listens on (default for Spring Boot is 8080)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
