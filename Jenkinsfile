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
                    docker save $IMAGE_NAME -o /tmp/task-manager-app.tar
                    docker cp /tmp/task-manager-app.tar minikube:/tmp/task-manager-app.tar
                    docker exec minikube ctr -n k8s.io images import /tmp/task-manager-app.tar
                    rm /tmp/task-manager-app.tar
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