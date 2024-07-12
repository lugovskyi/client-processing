Test task includes<br><br>

5 Spring boot services <br>
1st - REST api service, that sands data to rabbit queue. Have auth by simle session id, with simple validation just for example <br>
2d, 3d, 4th - services that listen queue, fetch data from mocked external service and send it to another queue<br>
5th - service that listen queue and save data from message to db using jpa <br><br>

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
