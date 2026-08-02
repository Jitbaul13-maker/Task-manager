pipeline {
    agent any

    environment {
        DB_URL = 'jdbc:postgresql://postgres:5432/taskmanager'
    }

    stages {
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw package -DskipTests'
            }
        }
    }
}