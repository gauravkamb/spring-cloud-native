$ docker run -d -p 8000:8000 -e "SPLUNK_START_ARGS=--accept-license" -e "SPLUNK_PASSWORD=changeme" --name splunk splunk/splunk:latest


curl https://start.spring.io/starter.zip -d groupId=com.polarbookshop -d artifactId=catalog-service -d name=catalog-service -d packageName=com.polarbookshop.catalogservice -d dependencies=web -d javaVersion=17 -d bootVersion=2.7.18 -d type=gradle-project -o catalog-service.zip

--Run application
./gradlew bootRun

--Build docker image
./gradlew bootBuildImage

--Run docker image
docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.2-SNAPSHOT

--start kubernates cluster
minikube start

--load image into kubernates
minikube image load catalog-service:0.0.2-SNAPSHOT

--create deployment - to manage pods
kubectl create deployment catalog-service --image=catalog-service:0.0.2-SNAPSHOT

-- see deployment
kubectl get deployment

-- get the pod
kubectl get pod

--create service - to expose application running on pod managed by deployment to another services from cluster
kubectl expose deployment catalog-service --name=catalog-service --port=8080

--Port forwading from local maching port to kubernates cluster port
kubectl port-forward service/catalog-service 8000:8080 Forwarding from 127.0.0.1:8000 -> 8080 Forwarding from [::1]:8000 -> 8080

--delete service
kubectl delete service catalog-service

--delete deployment
kubectl delete deployment catalog-service

--stop kubernates
minikube stop

--Run postgres as container
docker run -d --name polar-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=polardb_catalog -p 6432:5432 postgres:14.4