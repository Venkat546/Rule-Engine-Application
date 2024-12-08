# Use an official JDK as a parent image
FROM openjdk:23

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build artifact to the container
COPY target/RuleEngineApp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the application runs
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
