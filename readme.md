# Projeto Integrador - Grupo 4

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

O objetivo deste desafio é aplicar e consolidar os conteúdos ministrados durante o Programa de aceleração MeLi.
Ao longo do desafio, implementamos um sistema que simula uma situação real vivida no mercado livre, coordenando compras, estoques, produtos e depósitos.

O sistema foi implementado uilizando Java + SpringBoot, além de Postgres como SGBD.
## Documentação

[Documentação](/document/readme.md)


[Changelog](/document/changelog.md)


## Instalação

```bash
git clone https://github.com/alansiq/projeto_integrador
cd projeto_integrador
```

## Uso

```
mvnw spring-boot:run
```

Para inicializar o banco de dados:

```
docker run --name postgres-db -e POSTGRES_PASSWORD=docker -p 5432:5432 -d postgres
```

Para testar os endpoints contidos na documentação, as coleções o postman podem ser acessadas no [seguinte link](/document).
As requisições devem ser feitas em:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/20819592-c6b6537e-8c59-4586-8f0a-6749e30f202b?action=collection%2Ffork&collection-url=entityId%3D20819592-c6b6537e-8c59-4586-8f0a-6749e30f202b%26entityType%3Dcollection%26workspaceId%3Dd64d0851-ac01-4ad9-9d72-4e34bfade50a)
```
localhost:8080
```

## Quem fez

- [Alan Siqueira](https://github.com/alansiq)
- [Flávio Germano](https://github.com/fgnanni)
- [Hugo Fellipe](https://github.com/hugofellipecruz)
- [José Alves](https://github.com/josalves-meli)
- [Marianna Rodrigues](https://github.com/marianna-meli)
- [Rafael Luiz](https://github.com/rafaelluiz-meli)
- [Victor Santos](https://github.com/victosantos)


## License
[MIT](https://choosealicense.com/licenses/mit/)