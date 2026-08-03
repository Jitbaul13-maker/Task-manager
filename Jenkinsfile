pipeline {
    agent any

    environment {
        DB_URL = 'jdbc:postgresql://postgres:5432/taskmanager'
        IMAGE_NAME = 'task-manager-app:v1'
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

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Load Image Into Minikube') {
            steps {
                sh '''
                    docker save task-manager-app:v1 | \
                    docker exec -i minikube ctr -n k8s.io images import -
                '''
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                    kubectl apply -f k8s/
                    kubectl rollout restart deployment/task-manager
                    kubectl rollout status deployment/task-manager --timeout=180s
                '''
            }
        }

        stage('Verify') {
            steps {
                sh '''
                    kubectl get pods
                    kubectl get services
                '''
            }
        }
    }
}