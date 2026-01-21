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