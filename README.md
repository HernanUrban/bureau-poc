# Bureau Service

Microservicio que orquesta y gestiona las operacions relacionadas con las consultas a los bureaus crediticios de los usuarios.

## Empezando
Clonar el repositorio.  
```sh
git clone https://github.com/HernanUrban/bureau-poc.git
```
Compilacion del proyecto  
```sh 
./mvnw clean package
```  
El POC consta de dos servicios. Uno de ellos *bureau-publisher* es para publicar mensajes que seran procesados por el *bureau-service*.
```sh
$ cd bureau-publisher/target
$ java -jar bureau-publisher-0.0.1-SNAPSHOT.jar

$ cd bureau-service/target
$ java -jar bureau-service-0.0.1-SNAPSHOT.jar

```
Publicar mensaje  
```sh
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id":"1","payload":"prueba"}' \
  http://localhost:8080/bureau/sendMessage
```
En la consola de *bureau-service* se vera que el mensaje ha sido procesado:
```console
Handling bureau message - id: 1, payload: prueba
```

### Prerequisitos

1. Instalar rabbitmq (docker image)

```sh
docker run -d --hostname bureau-poc-rabbit --name bureau-poc-rabbit -p 8585:15672 -p 5672:5672 rabbitmq:3-management
```  
Si el puerto 8585 esta en uso cambiar por algun puerto libre: `-p 8585:15672`  
El plugin quedara accesible en `http://localhost:8585` _(user: guest, pass: guest)_

![rabbit-manager](images/rabbit-manager.png)



### Arquitectura  
![bureau-service](images/bureau-componentes.png)
