mvn clean install

docker build . -t gryffindor937/discoveryservicedeployment:1.0

docker push gryffindor937/discoveryservicedeployment:1.0

kubectl apply -f deployment.yml