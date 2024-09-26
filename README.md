To run Maven with Java 17 on your Jenkins server, you need to ensure that Jenkins uses the correct Java version during your Maven builds. Here's a step-by-step guide to switch to Java 17 for your Maven build in Jenkins:

## 1. Install Java 17 on Jenkins Server
Ensure Java 17 is installed on the Jenkins server. You've confirmed Java 17 is already installed.

## 2. Set up Java 17 in Jenkins

Tool Configuration:
In Jenkins, go to Manage Jenkins >  Tool Configuration.
Scroll down to the JDK section.
Add a new JDK, name it (e.g., Java 17), and set the installation path where Java 17 is installed. select install automatically > select from Install from adoptium.net and select the versrion.

## 3. set up Maven 3.6.3
in jenkins tool configurarion 
in jenkins , go to manage jenkins > Tool Configuration
Scroll down to the Maven section.
Add a new MAVEN, name it (e.g., Maven 3.6.3), select install automatically > select from Install from apache and select the versrion.


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

## 1. Grant Jenkins User Access to Files and Directories
If the Jenkins user doesn't have access to certain directories or files, you can modify permissions using chown or chmod.
For example, if you need to grant Jenkins access to the /var/lib/jenkins/workspace/project-1 directory:

sudo chown -R jenkins:jenkins /var/lib/jenkins/workspace/project-1/Dockerfile
This command makes the Jenkins user the owner of the directory.

If the file permissions are an issue (for example, with Docker or JAR files), you can also adjust them:

sudo chmod -R 755 /var/lib/jenkins/workspace/project-1/Dockerfile
This grants read, write, and execute permissions for the owner (Jenkins) and read and execute permissions for others.

## set up the docker credentials 
1. Creating Docker Credentials in Jenkins
If you haven’t already created the Docker credentials, follow these steps:

Step-by-Step to Create Docker Credentials
Access Jenkins:

Log in to your Jenkins dashboard.
Navigate to Manage Jenkins:

Click on Manage Jenkins from the left-hand menu.
Go to Manage Credentials:

Click on Manage Credentials.
Choose the Credential Domain:

Click on (global) (or select another domain if needed).
Add Credentials:

Click on Add Credentials on the left side.
Fill in the Details:

Kind: Select Username with password.
Scope: Set it to Global.
Username: Enter your Docker registry username (e.g., your Docker Hub username).
Password: Enter your Docker registry password.
ID: Assign a unique identifier for the credentials, such as dockerhub-creds.
Description: (Optional) Add a brief description for clarity.
Save:

Click the OK button to save your credentials.
