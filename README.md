# Prerequisites
## Setup Kubernetes
* Run locally https://kubernetes.io/docs/setup/learning-environment/minikube/
* Google Kubernetes Engine https://cloud.google.com/kubernetes-engine/docs/

## Create Cluster Role
### To enable access to ConfigMaps from  microservices
* sudo kubectl create clusterrolebinding demo-role-binding --clusterrole=view --serviceaccount=default:default

# Maven Build
### reviews-service
* mvn clean package -f apps/reviews-service

### products-service
* mvn clean package -f apps/products-service

# Docker Image Build
## Build command
* sudo docker build --no-cache -t {host}/{image}:{tag} --build-arg jar_file={jarFile} .

### reviews-service
* sudo docker build --no-cache -f apps/reviews-service/Dockerfile -t quebicdocker/ms-demo-reviews-service:0.1.0 --build-arg jar_file=apps/reviews-service/target/reviews-service-0.1.0.jar .

### products-service
* sudo docker build --no-cache -f apps/products-service/Dockerfile -t quebicdocker/ms-demo-products-service:0.1.0 --build-arg jar_file=apps/products-service/target/products-service-0.1.0.jar .

# Docker Image Publish
## Publish command
* sudo docker push {dockerImage}

### reviews-service
* sudo docker push quebicdocker/ms-demo-reviews-service:0.1.0

### products-service
* sudo docker push quebicdocker/ms-demo-products-service:0.1.0


# Kubernetes Deployment
## Create ConfigMap
* kubectl apply -f config-map/common-config.yaml

## Deploy Microservices
### reviews-service
* kubectl apply -f apps/reviews-service/kubernetes.yaml

### products-service
* kubectl apply -f apps/products-service/kubernetes.yaml

## Create static ip
* gcloud compute addresses create ms-demo-static-ip --global

## Create Ingress
* kubectl apply -f ingress.yaml

