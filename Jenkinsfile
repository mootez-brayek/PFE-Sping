pipeline {
    agent any

    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
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
        stage('commit version update') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'cc4c42c7-2e7b-4096-abbe-84221378e26f',
                        usernameVariable: 'mootez-brayek',
                        passwordVariable: 'mootezbrayek98'
                    )]) {
                        sh 'git config --global user.email "jenkins-se-ci@example.com"'
                        sh 'git config --global user.name "jenkins-se"'
                        sh "git remote set-url origin https://${GITHUB_USERNAME}:${GITHUB_PASSWORD}@github.com/mootez-brayek/PFE-Sping.git"
                        sh 'git add .'
                        sh 'git commit -m "[ci: version update]"'
                        sh 'git push origin HEAD:service/reglement'
                    }
                }
            }
        }

















    }
}
