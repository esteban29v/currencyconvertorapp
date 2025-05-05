# Currency Converter

A simple currency converter application using Spring Boot with Strategy Pattern and Retryable.

## How to run

Needs Java 17 or higher.

```bash
mvn spring-boot:run
```

## How to test

```bash
curl -X POST http://localhost:8080/currency -H 'Content-Type: application/json' -d '{"value": 100, "currency": "COP"}'
```
