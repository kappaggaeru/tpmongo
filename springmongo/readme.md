El projecto fue realizado con Spring Boot y Docker

En este caso se utilizó un contenedor en el puerto 5050

```docker exec -it <contenedor> bash```

Levantar entorno Maven e instalar dependencias

```mvn clean install```

Correr main Application

```SpringmongoApplication```

Para consumir la API utilizar el puerto 6039 en localhost

```http://localhost:6039/```