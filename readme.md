# spring-boot-external-property-override-issue
reproduce an edge case issue in spring-boot-1.3.x when overriding a property from an external jar (due to spring-framework maybe?)

This is quite an edge case as a set of condition have to be met in order to encounter the problem.
Namely:
- having an `application.properties` in a jar dependency inherited by the main spring-boot app
- having an `application.yml` located at the root classpath of the spring-boot app
- using the `default` profile when starting the spring-boot app

How to use:
The `common` external jar dependency is a maven project located in the main project. It has to be packaged and install first.\

``
cd common
./mvnw clean install
cd ..
./mvnw clean spring-boot:run
``
You can use the provided `start.sh` script which does exactly this (on *NIX env only)

You'll see these logs:

```
Registering configuration
Sending logs to 'common.local'
```

Now, after having installed ``common``, run

`./mvnw spring-boot:run -Dspring.profiles.active=dev`

which will display

```
Registering configuration
Sending logs to 'app.dev'
```

According to the `application.yml`:

```
app:
    logging:
        logstash:
            host: app.default
---
spring:
    profiles: dev
app:
    logging:
        logstash:
            host: app.dev
```

running `./mvnw spring-boot:run` should display

```
Registering configuration
Sending logs to 'app.default'
```

but it doesn't take into account the 'non profiled' default config and instead display the property value coming from the `common` jar:

```
Registering configuration
Sending logs to 'common.local'
```

NB: this is only the case when using an `application.properties` format in the external jar.
If you suppress or rename `application.properties` and rename `application.bla` to `application.yml` and relaunch `./start.sh` everything will behave as expected and you'll get :

```
Registering configuration
Sending logs to 'app.default'
```