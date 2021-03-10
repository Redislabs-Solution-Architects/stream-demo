# Stream Demo

### Getting started
(Optional) Start a Redis server:
```shell
redis-server
```

Start the stream listener app:
```shell
./gradlew bootRun
```

Generate messages using [RIOT Gen](https://developer.redislabs.com/riot/riot-gen.html):
```shell
riot-gen import --end 10000000 id=#index xadd --keyspace events
```

You should see stats printed in the stream listener app console:
```shell
-- Meters ----------------------------------------------------------------------
eventsConsumed
             count = 743850
         mean rate = 43687.26 events/second
     1-minute rate = 49792.70 events/second
     5-minute rate = 49818.97 events/second
    15-minute rate = 49826.11 events/second
```

### Configuration
The stream listener is a Spring Boot app, and like all Spring Boot apps can be configured using program arguments such as:
```shell
--spring.redis.host=myhost --spring.redis.port=6380 ...
```
With gradle you can configure the app using the `--args` option:
```shell
./gradlew bootRun --args="--spring.redis.host=myhost --spring.redis.port=6380"
```

### Additional Links
These additional references should also help you:

* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-redis)
* [Spring Data Redis Configuration Properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#spring.redis.client-name)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
