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

test request

``` curl
curl --location 'http://localhost:8001/api/v1/client/register' \
--header 'x-request-sid: 0a6da901-9c35-4e62-9bd9-348ddb5f0c7d' \
--header 'Content-Type: application/json' \
--data '{
"id":"100503",
"taxNumber":"7234567890"
}'
```