pipeline
{
    agent any
    environment
    {
       DOCKER_IMAGE_NAME = 'dockerspacex/my-app'
       DOCKER_IMAGE_TAG = 'latest'
       //DOCKER_REGISTRY = 'dockerspacex'
    }
    tools
    {
        jdk 'Java 17'
        maven 'Maven 3.6.3'
    }
    stages
    {
        stage('checkout')
        {
            steps
            {
                git branch: 'main', url: 'https://github.com/kanamandarajesh/maven-package.git'
            }
        }
        stage('build jar')
        {
            steps
            {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image')
        {
            steps
            {
                script
                {
                    sh '''
                    docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} .
                    '''
                }
                      
            }
        }
        stage('Login to Docker Registry')
        {
            steps
            {
                script
                {
                    withCredentials([usernamePassword(credentialsId: '3a2a5540-c9f8-46cf-af31-b69252f84a65', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) 
                    {  
                       sh '''
                       echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                       '''
                    }   
                }
            }
        }
        stage('Push Image to Docker Hub')
        {
            steps
            {
                script
                {
                    sh '''
                    docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}
                    '''
                    echo "Image pushed to Docker Hub"
                }
            }
        }
    }
}
