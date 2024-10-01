# FraudWatch

## Descrição

FraudWatch é um sistema de análise preditiva desenvolvido para reduzir fraudes em solicitações de sinistros
odontológicos. O sistema envolve duas aplicações: uma para dentistas cadastrarem procedimentos, e outra para que
administradores possam analisar as solicitações com o suporte de Inteligência Artificial (IA). Essa IA será treinada
para identificar padrões fraudulentos.

A aplicação Java será o núcleo da comunicação, responsável por cadastrar usuários e procedimentos, armazenar e processar
dados no banco, além de disponibilizar informações para aplicações web e mobile.

**Tecnologias:** _Java, Spring Boot, JPA, Hibernate, OracleSQL, Docker, .NET, Kotlin., Machine Learning_  
**Link do PITCH:** [FraudWatch](https://www.youtube.com)
## Integrantes

- [Maurício Vieira Pereira](https://github.com/Mauricio-Pereira/)
    - Responsável pelo back-end em Java, incluindo a modelagem do banco de dados, integração com APIs externas, e
      gerenciamento do fluxo de dados no sistema.
- [Luis Otávio Leitão - RM 553542 ](https://github.com/Luiz1614)
    - Desenvolvedor da aplicação web em .NET, focado na criação da arquitetura DevOps, automação de pipelines, e
      implementação de containers Docker.
- [Yago Lucas Gonçalves - RM 553013](https://github.com/yagoluucas/)
    - Desenvolvedor da aplicação mobile em Kotlin, além de ser o responsável pela criação de um modelo de Machine
      Learning para detecção de fraudes. Atua também na documentação e qualidade de software (QA).

## Cronograma

### Atividades da Sprint 1

#### Back-end (Java)

| **Atividade**                                                                          | **Responsável**         | **Prazo de Entrega** |
|----------------------------------------------------------------------------------------|-------------------------|----------------------|
| Modelagem do banco de dados (definir entidades, atributos e relacionamentos)           | Maurício Vieira Pereira | 01/10                |
| Criação do Diagrama Entidade-Relacionamento (DER)                                      | Maurício Vieira Pereira | 01/10                |
| Implementação do banco de dados e criação das tabelas (usando JPA e Hibernate)         | Maurício Vieira Pereira | 01/10                |
| Desenvolvimento dos endpoints RESTful da API para CRUD de Usuários e Endereços         | Maurício Vieira Pereira | 01/10                |
| Inserção de dados no banco de dados para testes iniciais (utilizando bibliotecas Java) | Maurício Vieira Pereira | 01/10                |

#### Web (ASP.NET)

| **Atividade**                                            | **Responsável**    | **Prazo de Entrega** |
|----------------------------------------------------------|--------------------|----------------------|
| Implementação do back-end em .NET e configuração inicial | Luis Otávio Leitão | 01/10                |
| Implementação das rotas da aplicação web (ASP.NET Core)  | Luis Otávio Leitão | 01/10                |

#### DevOps e Infraestrutura

| **Atividade**                                             | **Responsável**    | **Prazo de Entrega** |
|-----------------------------------------------------------|--------------------|----------------------|
| Desenho da arquitetura DevOps (incluindo pipelines CI/CD) | Luis Otávio Leitão | 01/10                |
| Criação e configuração dos containers Docker              | Luis Otávio Leitão | 01/10                |
| Configuração da aplicação em uma máquina virtual (Azure)  | Luis Otávio Leitão | 01/10                |

#### Mobile (Kotlin)

| **Atividade**                                                           | **Responsável**      | **Prazo de Entrega** |
|-------------------------------------------------------------------------|----------------------|----------------------|
| Criação de wireframes para a aplicação mobile (mínimo de 5 telas)       | Yago Lucas Gonçalves | 01/10                |
| Implementação da tela inicial da aplicação mobile em Kotlin             | Yago Lucas Gonçalves | 01/10                |
| Implementação do fluxo de navegação entre as telas no aplicativo mobile | Yago Lucas Gonçalves | 01/10                |

#### Machine Learning e QA

| **Atividade**                                                          | **Responsável**      | **Prazo de Entrega** |
|------------------------------------------------------------------------|----------------------|----------------------|
| Desenvolvimento do modelo de Machine Learning para detecção de fraudes | Yago Lucas Gonçalves | 01/10                |
| Documentação de QA e documentação dos casos de uso para o modelo de IA | Yago Lucas Gonçalves | 01/10                |

---

### Atividades da Sprint 2

- *A serem definidas*

---
### Arquitetura do Projeto

A arquitetura utilizada no projeto FraudWatch é uma arquitetura em camadas. Esta arquitetura é comumente usada em aplicações empresariais e é composta por várias camadas, cada uma com responsabilidades específicas. Aqui está uma breve explicação das camadas típicas e como elas se aplicam ao projeto:  

---
- **Camada de Apresentação (Presentation Layer):**  
  - **Responsabilidade:** Interação com o usuário final, seja através de uma interface web, mobile ou API. 
  - **No Projeto:** A aplicação web em .NET e a aplicação mobile em Kotlin fazem parte desta camada. Elas consomem os serviços RESTful fornecidos pela camada de serviço. 
---
- **Camada de Serviço (Service Layer):**  
  - **Responsabilidade:** Contém a lógica de negócios da aplicação. Esta camada processa as requisições recebidas da camada de apresentação e interage com a camada de persistência para armazenar ou recuperar dados. 
  - **No Projeto:** Os controladores Spring Boot (UsuarioController, EnderecoController, etc.) fazem parte desta camada. Eles expõem endpoints RESTful para operações CRUD e outras funcionalidades. 
---
- **Camada de Persistência (Persistence Layer):**  
  - **Responsabilidade:** Gerenciamento de dados, incluindo a lógica de acesso a dados e a comunicação com o banco de dados.
  - **No Projeto:** Repositórios JPA/Hibernate (UsuarioRepository, EnderecoRepository, etc.) fazem parte desta camada. Eles são responsáveis por interagir com o banco de dados OracleSQL. 
---
- **Camada de Banco de Dados (Database Layer):**
  - **Responsabilidade:** Armazenamento físico dos dados. 
  - **No Projeto:** O banco de dados OracleSQL é utilizado para armazenar dados de usuários, endereços e outras entidades.
---
- **Camada de Infraestrutura** (Infrastructure Layer): 
  - **Responsabilidade:** Orquestração e gerenciamento de containers. 
  - **No Projeto:** Docker para containerização e Azure para hospedagem e orquestração.
---

### Diagrama de Arquitetura
O diagrama de arquitetura do projeto FraudWatch mostra a estrutura geral do sistema, incluindo as camadas de apresentação, serviço, persistência e banco de dados. Ele também destaca as tecnologias e ferramentas utilizadas em cada camada.
*Para melhor visualização, o diagrama está disponível no diretório: `/documentation/FraudWatch_Architecture_Diagram.png`.*
![Diagrama de Arquitetura](/documentation/FraudWatch_Architecture_Diagram.png)

### Diagrama de Classes 
O diagrama de classes representa as principais entidades do projeto FraudWatch, incluindo Usuário e Endereço. Cada entidade possui atributos e métodos que definem seu comportamento e relacionamentos com outras entidades.  
*Para melhor visualização, o diagrama está disponível no diretório: `/documentation/FraudWatch_Class_Diagram.png`.*
![Diagrama de Classes](/documentation/FraudWatch_Class_Diagram.png) 

### Diagrama de Entidade-Relacionamento (DER)
O diagrama entidade-relacionamento (DER) é uma representação visual dos relacionamentos entre as entidades do banco de dados. Ele mostra como as tabelas estão conectadas e quais são as chaves primárias e estrangeiras.  
O diagrama representa as entidades Usuário e Endereço, mostrando como elas se relacionam no banco de dados, além das classes que ainda serão implementadas.  
*Para melhor visualização, o diagrama está disponível no diretório: `/documentation/FraudWatch_DER.png`.*
![DER](/documentation/FraudWatch_DER.png) 

### Descrição do nível de maturidade da API
A API está no Nível 2 do Modelo de Maturidade de Richardson, o que significa que ela utiliza:

1. *Métodos HTTP apropriados (GET, POST, PUT, DELETE).*  
2. *URIs bem definidas para os recursos (/api/enderecos, /api/usuarios).*  
3. *Código de status HTTP adequado nas respostas.*  
4. *No entanto, a API ainda não utiliza HATEOAS (Hypermedia as the Engine of Application State), o que seria necessário para atingir o nível 3 de maturidade.*

### Endpoints das APIs
Aqui está a tabela de endpoints para a API FraudWatch, com uma indicação dos métodos, URIs e uma breve descrição de cada
operação. 
Os corpos das requisições e respostas (JSON) estarão disponíveis no arquivo do Postman, no diretório: `/postman/FraudWatch.postman_collection.json`.
O arquivo também contém exemplos de requisições para cada endpoint assim como os endpoints de teste.

**_USUARIO_**

| Método | Endpoint                                   | Descrição                                                | Corpo da Requisição |
|--------|--------------------------------------------|----------------------------------------------------------|---------------------|
| POST   | `http://localhost:8080/usuario`            | Cria um novo usuário                                     | JSON (no Postman)   |
| POST   | `http://localhost:8080/usuario/list`       | Cria vários usuários de uma vez                          | JSON (no Postman)   |
| GET    | `http://localhost:8080/usuario/page{page}` | Lista todos os usuários paginados(4 usuarios por página) | -                   |
| GET    | `http://localhost:8080/usuario/{id}`       | Busca um usuário específico por ID                       | -                   |
| PUT    | `http://localhost:8080/usuario/{id}`       | Atualiza as informações de um usuário por ID             | JSON (no Postman)   |
| DELETE | `http://localhost:8080/usuario/{id}`       | Deleta um usuário específico por ID                      | -                   |

**_ENDERECO_**

| Método | Endpoint                                     | Descrição                                                  | Corpo da Requisição |
|--------|----------------------------------------------|------------------------------------------------------------|---------------------|
| POST   | `http://localhost:8080/endereco`             | Cria um novo endereço                                      | JSON (no Postman)   |
| POST   | `http://localhost:8080/endereco/list`        | Cria vários endereços de uma vez                           | JSON (no Postman)   |
| GET    | `http://localhost:8080/endereco/page/{page}` | Lista todos os endereços paginados(4 endereços por página) | -                   |
| GET    | `http://localhost:8080/endereco/{id}`        | Busca um endereço específico por ID                        | -                   |
| PUT    | `http://localhost:8080/endereco/{id}`        | Atualiza as informações de um endereço por ID              | JSON (no Postman)   |
| DELETE | `http://localhost:8080/endereco/{id}`        | Deleta um endereço específico por ID                       | -                   |

Essa tabela resume os endpoints da API FraudWatch, indicando quais operações estão disponíveis e quais exigem um corpo
de requisição em JSON, disponível no Postman.


### Como executar o projeto
### Passo a Passo para Rodar o Projeto FraudWatch

#### Pré-requisitos
1. **Java Development Kit (JDK)**: Certifique-se de ter o JDK 11 ou superior instalado.
2. **Gradle**: Certifique-se de ter o Gradle instalado.
3. **Docker**: Certifique-se de ter o Docker instalado e em execução.
4. **Azure CLI**: Certifique-se de ter o Azure CLI instalado e configurado.
5. **IDE**: IntelliJ IDEA ou outra IDE de sua preferência.

#### Passo 1: Clonar o Repositório
Clone o repositório do projeto para sua máquina local:
```sh
git clone https://github.com/Mauricio-Pereira/FraudWatch.git
cd FraudWatch
```

#### Passo 2: Configurar o Banco de Dados
1. **Configuração**: Atualize as configurações de conexão com o banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.show-sql=true
   spring.jpa.generate-ddl=true
   spring.jpa.hibernate.ddl-auto=create
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.OracleDialect
   ```
   É possível alterar o banco de dados para outro de sua preferência, como MySQL, PostgreSQL, etc. Basta alterar as configurações de conexão no arquivo `application.properties`
   e adicionar a dependência correspondente no arquivo `build.gradle`.

#### Passo 2: Construir e executando o projeto
Construa o projeto utilizando o Gradle ou a IDE de sua preferência:
 - No gradle:
```sh
./gradlew build
```
- Na IDE:
  - Importe o projeto no IntelliJ IDEA ou outra IDE de sua preferência.
  - Execute o projeto para construir e baixar as dependências.


#### Passo 3: Acessar a Aplicação
A aplicação estará disponível em `http://localhost:8080`.

#### Passo 4: Testar os Endpoints
Utilize o Postman ou outra ferramenta de sua preferência para testar os 
endpoints da API conforme descrito anteriormente no `README.md`.
É possível importar o arquivo do Postman disponível no diretório `/postman/FraudWatch.postman_collection.json` para ter acesso aos endpoints de teste.
É possível também acessar o Swagger da aplicação em `http://localhost:8080/swagger-ui.html` para visualizar e testar os endpoints.  
*Obs.: PODE SER NECESSÁRIO ALTERAR O JSON DE REQUISIÇÃO PARA OS ENDPOINTS DE USUÁRIO E ENDEREÇO, DE ACORDO COM O MODELO DE DADOS DEFINIDO.*
##### Imagens do Postman
![Postman](/documentation/POSTMAN_RUN_PT1.png)
![Postman](/documentation/POSTMAN_RUN_PT2.png)






