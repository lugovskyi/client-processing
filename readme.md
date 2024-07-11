Test tusk includes<br><br>

1. Create a microservice architecture for the following services:<br>
   1.1. auth-service<br>
   1.2. diia-service<br>
   1.3. edr-service<br>
   1.4. fraud-service<br>
   1.5. client-data-service<br>

Order<br>

auth-service -> diia-service -> edr-service -> fraud-service -> client-data-service <br><br>
To run project <br>

```
./gradlew bootBuildImage

docker compose up
```

<br><br>
queues and exchanges creating automatically<br>
db creating automatically but cleans after restart of client-data-service<br>
<br><br>

test postman collection<br><br>

[Test collection.postman_collection.json](https://github.com/lugovskyi/client-processing/blob/master/Test%20collection.postman_collection.json)
