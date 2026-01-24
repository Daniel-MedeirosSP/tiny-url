# TU - Tiny Url
Encurtador de Url em SpringBoot com uso de MongoDB para persistência



# SWAGGER
http://localhost:8081/tu/swagger-ui/index.html


# ACTUATOR
## 🛠️ Monitoramento e Documentação

A aplicação utiliza o **Spring Boot Actuator** para monitoramento e **SpringDoc OpenAPI** para documentação.

| Recurso | Descrição | Endereço (Local)                                                |
| :--- | :--- |:----------------------------------------------------------------|
| **Swagger UI** | Interface visual para testar os endpoints da API | [Abrir Swagger](http://localhost:8081/tu/swagger-ui/index.html) |
| **OpenAPI Docs** | Definições JSON da API no padrão OpenAPI 3 | [Ver JSON](http://localhost:8081/tu/v3/api-docs)                |
| **Health Check** | Verifica se a App e o MongoDB estão funcionando | [Ver Status](http://localhost:8081/tu/actuator/health)          |
| **Metrics** | Estatísticas de memória, CPU e conexões | [Ver Métricas](http://localhost:8081/tu/actuator/metrics)       |
| **Mappings** | Lista todos os caminhos (URLs) mapeados na App | [Ver Mappings](http://localhost:8081/tu/actuator/mappings)      |
| **Env** | Exibe as variáveis de ambiente e propriedades | [Ver Env](http://localhost:8081/tu/actuator/env)                |
| **Beans** | Lista todos os Spring Beans criados no contexto | [Ver Beans](http://localhost:8081/tu/actuator/beans)            |

> 💡 **Nota:** Os endpoints do `/actuator` (exceto o health) exigem a configuração `management.endpoints.web.exposure.include: "*"` no arquivo `application.yml`.


### Configuração Local
Para rodar o projeto, configure as seguintes variáveis de ambiente:
- `MONGODB_USER`: Seu usuário do MongoDB.
- `MONGODB_PASSWORD`: Sua senha do MongoDB.


### Index.html
Atualizar o endereço de domínio para voltar ao root
**http://localhost:8081/tu/**

### Gerando Imagem
**podman build -t tiny-url-app .**

### Passos para criar um POD com banco de dados e aplicação para rodar local em container
**1- podman pod create --name tinyurl_infra -p 8081:8081 -p 27017:27017**

**2- Criar o Container do Mongo dentro deste POD: 
podman run -d --name mongodb_tiny --pod tinyurl_infra -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=password docker.io/library/mongo:latest**


**3- Criar o Imagem do Tiny-Url:
podman build -t tinyurl_app:latest .**

**4- Criar o Container da aplicação dentro do POD:
podman run -d --name tinyurl_container --pod tinyurl_infra -e MONGODB_USER -e MONGODB_PASSWORD tinyurl_app:latest

## 📞 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/daniel-medeiros-9b853544/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Daniel-MedeirosSP/tiny-url)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:seu-email@exemplo.com)

**Desenvolvido por:** Daniel Medeiros  
**Email:** daniel.medeirossp@gmail.com
