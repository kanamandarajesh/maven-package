To run Maven with Java 17 on your Jenkins server, you need to ensure that Jenkins uses the correct Java version during your Maven builds. Here's a step-by-step guide to switch to Java 17 for your Maven build in Jenkins:

## 1. Install Java 17 on Jenkins Server
Ensure Java 17 is installed on the Jenkins server. You've confirmed Java 17 is already installed.

## 2. Set up Java 17 in Jenkins
Global Tool Configuration:
In Jenkins, go to Manage Jenkins >  Tool Configuration.
Scroll down to the JDK section.
Add a new JDK, name it (e.g., Java 17), and set the installation path where Java 17 is installed. select install automatically > select from Install from adoptium.net and select the versrion.
-----------------------------------------
Since your JAR file is created in `/var/lib/jenkins/workspace/project-1/target`, you should create the `Dockerfile` in the root directory of your Jenkins project workspace, which is `/var/lib/jenkins/workspace/project-1/`.

### Steps to create the `Dockerfile`:

1. **Go to the project root directory**:
   - This is `/var/lib/jenkins/workspace/project-1/`, where your `target` directory is located.
  
2. **Create the `Dockerfile`**:
   - Run the following command inside the root directory of your project:
     ```bash
     touch /var/lib/jenkins/workspace/project-1/Dockerfile
     ```

3. **Edit the `Dockerfile`**:
   - Open the `Dockerfile` using any text editor and paste the following content:

     ```Dockerfile
     # Use the official OpenJDK image as a base image
     FROM openjdk:17-jdk-alpine

     # Set the working directory inside the container
     WORKDIR /app

     # Copy the JAR file from the host into the container
     COPY target/my-app.jar /app/my-app.jar

     # Specify the command to run your app
     ENTRYPOINT ["java", "-jar", "my-app.jar"]

     # Expose the port the application runs on (optional)
     EXPOSE 8080
     ```

4. **Save the `Dockerfile`**.

Now your Jenkins pipeline will look for the `Dockerfile` in the root directory of the project when building the Docker image.

#### Pipeline Command:
No need to change the pipeline script since the `Dockerfile` will be found in the root directory:

```groovy
docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} .
``` 

This setup should now work, and Jenkins will create the Docker image using your `Dockerfile` and the JAR file generated in the `target` folder.
