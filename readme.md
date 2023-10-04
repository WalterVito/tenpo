## Set up 

# docker compose 
* ./mvnw clean package -DskipTests
* docker compose build
* docker compose up

  ![image](https://github.com/WalterVito/tenpo/assets/31138725/fe681c3a-80c6-418e-84aa-bdd6fc1ecfc4)


#Tests run

*./mvnw test 

![image](https://github.com/WalterVito/tenpo/assets/31138725/fc04ab38-a2bd-42b0-82c9-eabd848b5df4)

# PostmanCollection

*in the root path > tenpo.postman_collection.json

# Tasks and checklist
Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:

Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje
que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje
devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes
consideraciones:

- [x] El servicio externo puede ser un mock, tiene que devolver el % sumado. 
![image](https://github.com/WalterVito/tenpo/assets/31138725/9647c1f9-d960-486d-b4d4-a514d08f5c82)


- [x] With Cache Implementation - Dado que ese % varía poco, podemos considerar que el valor que devuelve ese servicio no va cambiar por 30 minutos. 

- [x] Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la
api.
 ** (for example cache the result 60 m , if fail after 3 retries try to use last api result from cache. If doesnt way have value . throws  a Repository Exception) **

- [x] Si el servicio falla, se puede reintentar hasta 3 veces.

- [x] Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en
Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado, y en caso de
falla, no debe impactar el llamado al servicio principal.
 **  (Implement logging filter getting data off all request / responses and store in a table in postgreSql ) **

<img width="994" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/6faf5a7c-c472-49e4-be6b-725a5ca6e48b">

![image](https://github.com/WalterVito/tenpo/assets/31138725/bc85898a-9f06-45ac-bbf9-64ebd5120723)



- [x]La api soporta recibir como máximo 3 rpm (request / minuto), en caso de superar ese umbral, debe retornar un error con
el código http y mensaje adecuado.
**  (Done. using ratelimiter from com.google.guava with an interceptor java . If rate limit is excedeed return a custom exception
managed with a 429 status code )**
![image](https://github.com/WalterVito/tenpo/assets/31138725/d0a4d9b7-5eb6-4648-a274-977d6db2ea8c)


- [x] El historial se debe almacenar en una database PostgreSQL.


- [x] Incluir errores http. Mensajes y descripciones para la serie 4XX.
**  ( We implement a custom handled of exception and logger for unexpected exceptions with a friendly message but logging al stacktrace) **

- [x] Se deben incluir tests unitarios.


- [x] Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos
también debe correr en un contenedor docker. Recomendación usar docker compose

- [x] Docker compose and dockerfile

- [x] Debes agregar un Postman Collection o Swagger para que probemos tu API
** (Add postman collection in the repository) **

- [] Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y
cómo utilizarlo.

(private) switch to public

- [x] Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una
réplica del servicio funcionando en paralelo.
** (Done. but with a questions about rpm and api response with 30m retention . If we want implement the sames requests limits and same api responses cache in all repliques 
we can do it whit a distributed cache like as memcached / dynamo/ redis)**
