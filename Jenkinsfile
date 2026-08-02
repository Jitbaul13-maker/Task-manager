pipeline {
    agent any

    environment {
        DB_URL = 'jdbc:postgresql://taskmanager-postgres:5432/taskmanager'
    }

    stages {
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
    }
}