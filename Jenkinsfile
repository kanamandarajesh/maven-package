pipeline
{
    agent any
    environment
    {
       DOCKER_IMAGE_NAME = 'my-app'
       DOCKER_IMAGE_TAG = 'latest'
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
    }
}
