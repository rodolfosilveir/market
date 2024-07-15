# market

`Microserviço responsável pela loja de vinhos e gerenciamento de clientes`

# Pré Requisitos

Para que seja possível rodar essa aplicação é necessário atender alguns requisitos básicos.

- JDK 17 LTS
- Gradle 8.8+
 
# Compilando e inicializando

Assim como todo projeto *Gradle*, é necessário primeiramente realizarmos a geração dos fontes. Conforme o exemplo abaixo:

```bash
./gradlew clean build
```

# Mostrando endpoints expostos

Assim que o projeto estiver sendo executado, é possível verificar as APIs expostas acessando a URL:

```
http://localhost:8080/swagger-ui/index.html#/
```

# Testar local

O projeto possui um arquivo do postman chamado Digio Market.postman_collection.json. Aqui esta um curl de exemplo

```
curl --location http://localhost:8080/clientes-fieis