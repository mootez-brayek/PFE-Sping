pipeline {
    agent any

    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    environment {
         dockerRegistry = 'bmootez/spring-pfe'
         dockerCredential = 'dockerhub_id'
         dockerImage = ''
         latestDockerImage=''
    }

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'master',
                    url: 'git@github.com:mootez-brayek/PFE-Sping.git',
                    credentialsId: '30b84a81-08b9-4be5-85bd-be8ad70e7964'
            }
        }
        stage('cleaning java Project'){
            steps{
                sh 'mvn clean compile'
                }
        }
        stage('build artifact'){
            steps{
                sh 'mvn package'
            }
        }

        stage('testing maven'){
             steps{
                 sh 'mvn -version'
             }
        }
        stage('Building docker images') {
             steps {
                   script {
                   dockerImage = docker.build("${dockerRegistry}:1.0")
                   latestDockerImage = docker.build("${dockerRegistry}:latest")
                   }
             }
        }
               stage('Deploy docker images') {
                    steps {
                        script {
                            docker.withRegistry( '', dockerCredential ) {
                                dockerImage.push()
                                latestDockerImage.push()
                           }
                        }
                    }
               }



    }
}
