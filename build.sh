cd moviecruiserauthenticationservices
source ./env-variable.sh
mvn clean package
docker build -t user-service .
cd ..
cd backend
source ./env-variable.sh
mvn clean package
docker build -t movie-service .
cd ..

