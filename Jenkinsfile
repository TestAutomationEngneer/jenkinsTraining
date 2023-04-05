pipeline
{
    agent any
    tools{
    jdk "jdk"
    }

    stages {
        stage('Build') {
            steps {
                echo '>>>Conecting to git repo - DAREK >>>>>>>>>>>>>>>'
                git 'https://github.com/TestAutomationEngneer/jenkinsTraining.git'
//                 git branch: "${branch}", url: 'https://github.com/TestAutomationEngneer/jenkinsTraining.git'
                bat 'mvn clean compile'
                //sh 'mvn clean compile' - dla linuxa
            }
        }
         stage('Test') {
            steps {
                echo '>>>Test execution - DAREK >>>>>>>>>>>>>>>'

                bat 'mvn clean test'
                //sh 'mvn clean compile' - dla linuxa
            }
        }
    }
}
