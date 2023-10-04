## Set up 

# docker compose 
* ./mvnw clean package -DskipTests
* docker compose build
* docker compose up

<img width="530" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/f6e19749-38c8-47fc-bd07-506338068914">
<img width="771" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/5dbba4ef-08d3-4765-a51e-01100da6c71e">
<img width="734" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/6374f8d3-2fde-4cf6-83d7-3d2f9334c205">



# Tests run

* ./mvnw test 

<img width="485" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/cb43990b-5bc9-47d2-9632-582701116f3a">


# PostmanCollection

* in the root path > tenpo.postman_collection.json

# Tasks and checklist
Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:

Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje
que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje
devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes
consideraciones:

- [x] El servicio externo puede ser un mock, tiene que devolver el % sumado. 
<img width="546" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/5968bb20-d90f-47d1-abe1-1e9c8deb7bc3">



- [x] Dado que ese % varía poco, podemos considerar que el valor que devuelve ese servicio no va cambiar por 30 minutos **(Cache store)**. 

- [x] Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la
api.
 **(for example cache the result 60 m , if fail after 3 retries try to use last api result from cache. If doesnt way have value . throws  a Repository Exception)**

- [x] Si el servicio falla, se puede reintentar hasta 3 veces.

- [x] Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en
Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado, y en caso de
falla, no debe impactar el llamado al servicio principal.
 **(Implement logging filter getting data in all request / responses and store async in a table in postgreSql )**

<img width="717" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/68d774b0-15bd-4688-87a5-55614b113848">

- [x]La api soporta recibir como máximo 3 rpm (request / minuto), en caso de superar ese umbral, debe retornar un error con
el código http y mensaje adecuado.
**(Done. using ratelimiter from com.google.guava with an interceptor java . If rate limit is excedeed return a custom exception
managed with a 429 status code )**
<img width="645" alt="image" src="https://github.com/WalterVito/tenpo/assets/31138725/fe26de13-0230-4d3d-b30b-58010548b9df">

- [x] El historial se debe almacenar en una database PostgreSQL.

- [x] Incluir errores http. Mensajes y descripciones para la serie 4XX.
**( We implement a custom handled of exception and logger for unexpected exceptions with a friendly message but logging al stacktrace)**

- [x] Se deben incluir tests unitarios.

- [x] Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos
también debe correr en un contenedor docker. Recomendación usar docker compose

- [x] Docker compose and dockerfile

- [x] Debes agregar un Postman Collection o Swagger para que probemos tu API
**(Added postman collection in the repository)**

- [] Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y
cómo utilizarlo.

(private) switch to public

- [x] Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una
réplica del servicio funcionando en paralelo.
**(Done. but with a questions about rpm and api response with 30m retention . If we want implement the sames requests limits and same api responses cache in all repliques 
we can do it whit a distributed cache like as memcached / dynamo/ redis)**
