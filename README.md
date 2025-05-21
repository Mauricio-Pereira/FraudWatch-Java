# FraudWatch

## Descrição

FraudWatch é um sistema de análise preditiva desenvolvido para reduzir fraudes em solicitações de sinistros
odontológicos. O sistema envolve duas aplicações: uma para dentistas cadastrarem procedimentos, e outra para que
administradores possam analisar as solicitações com o suporte de Inteligência Artificial (IA). Essa IA será treinada
para identificar padrões fraudulentos.

A aplicação Java será o núcleo da comunicação, responsável por cadastrar usuários e procedimentos, armazenar e processar
dados no banco, além de disponibilizar informações para aplicações web e mobile.

**Tecnologias:** _Java, Spring Boot, JPA, Hibernate, OracleSQL, Docker, .NET, Kotlin., Machine Learning_  
**Link do PITCH:** [FraudWatch](https://www.youtube.com/watch?v=jExbQ6DjZaw)
## Integrantes

- [Maurício Vieira Pereira - RM553748 - 2TDSPC](https://github.com/Mauricio-Pereira/)
    - Responsável pelo back-end em Java, incluindo a modelagem do banco de dados, integração com APIs externas, e
      gerenciamento do fluxo de dados no sistema.
- [Luis Otávio Leitão - RM 553542 - 2TDSPC](https://github.com/Luiz1614)
    - Desenvolvedor da aplicação web em .NET, focado na criação da arquitetura DevOps, automação de pipelines, e
      implementação de containers Docker.
- [Vitor de Melo Marques - RM 553483 – 2TDPSR](https://github.com/yagoluucas/)
    - Desenvolvedor da aplicação mobile em Kotlin, além de ser o responsável pela criação de um modelo de Machine
      Learning para detecção de fraudes. Atua também na documentação e qualidade de software (QA).

      
## <u>*--------------------------IMPORTANTE--------------------------*</u>
### <u>*Na tela de cadastro de usuário do projeto spring mvc, apesar de ser apenas 1 unica tela, são 2 cruds feitos de forma simultânea, um de endereço e outro de usuário, pois um não pode existir no sistema sem o outro!*</u>

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
| Criação de wireframes para a aplicação mobile (mínimo de 5 telas)       | Vitor de Melo Marques | 01/10                |
| Implementação da tela inicial da aplicação mobile em Kotlin             | Vitor de Melo Marques | 01/10                |
| Implementação do fluxo de navegação entre as telas no aplicativo mobile | Vitor de Melo Marques | 01/10                |

#### Machine Learning e QA

| **Atividade**                                                          | **Responsável**      | **Prazo de Entrega** |
|------------------------------------------------------------------------|----------------------|----------------------|
| Desenvolvimento do modelo de Machine Learning para detecção de fraudes | Vitor de Melo Marques | 01/10                |
| Documentação de QA e documentação dos casos de uso para o modelo de IA | Vitor de Melo Marques | 01/10                |

#### Revisão e Finalização de Documentação

| **Atividade**                         | **Responsável**                   | **Prazo de Entrega** |
|---------------------------------------|-----------------------------------|----------------------|
| Revisão e finalização da documentação | Maurício Vieira Pereira, Luis Otávio Leitão, Vitor de Melo Marques | 07/10 |
---

### Atividades da Sprint 2


#### Compliance & Quality Assurance

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Desenho da Arquitetura da Solução aplicando TOGAF usando Archi                                           | Maurício Vieira Pereira, Luis Otávio Leitão, Vitor de Melo Marques | 15/10                |
| Desenvolvimento de funções de validação de entrada de dados                                               | Maurício Vieira Pereira                       | 20/10                |
| Criação de procedures para operações CRUD com validação e tratamento de erros                             | Maurício Vieira Pereira                       | 25/10                |
| Execução das procedures via aplicação Java e gravação do vídeo demonstrativo                              | Maurício Vieira Pereira                       | 30/10                |
| Criação de funções com cursor e joins para relatório formatado                                             | Maurício Vieira Pereira                       | 05/11                |
| Desenvolvimento de funções para relatório com regra de negócio específica                                 | Maurício Vieira Pereira                       | 07/11                |
| Revisão e finalização da documentação de QA e Compliance                                                 | Vitor de Melo Marques                          | 08/11                |

#### Mastering Relational and Non-Relational Database

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Implementação das correções no modelo de dados anterior conforme feedback                                | Maurício Vieira Pereira                       | 12/10                |
| Desenvolvimento de funções de validação de entrada de dados (2 funções)                                   | Maurício Vieira Pereira                       | 20/10                |
| Criação de procedures para operações CRUD com validação e tratamento de erros                             | Maurício Vieira Pereira                       | 25/10                |
| Execução das procedures via aplicação Java e demonstração em vídeo                                        | Maurício Vieira Pereira                       | 30/10                |
| Criação de função com cursor e joins para relatório formatado                                              | Maurício Vieira Pereira                       | 05/11                |
| Desenvolvimento de função para relatório com regra de negócio específica                                   | Maurício Vieira Pereira                       | 07/11                |
| Preparação do arquivo ZIP com PDFs, link do vídeo e scripts SQL                                           | Maurício Vieira Pereira                       | 08/11                |

#### DevOps Tools e Cloud Computing

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Provisionamento de recursos no provedor de Nuvem (Azure) e deployment de máquinas virtuais                | Luis Otávio Leitão                             | 15/10                |
| Configuração do monitoramento da saúde da VM, desempenho e dependências de rede                            | Luis Otávio Leitão                             | 18/10                |
| Instalação do Docker nas máquinas virtuais e criação de Dockerfile                                       | Luis Otávio Leitão                             | 22/10                |
| Configuração do Docker Compose para deployment das imagens Docker                                          | Luis Otávio Leitão                             | 25/10                |
| Organização e estruturação dos arquivos de deployment no repositório GitHub                                | Luis Otávio Leitão                             | 28/10                |
| Criação de scripts para deleção do grupo de recursos da VM e anexação das evidências                       | Luis Otávio Leitão                             | 05/11                |
| Documentação detalhada do processo de deployment e utilização de Docker                                   | Luis Otávio Leitão                             | 07/11                |
| Preparação do arquivo PDF com nomes, RMs, links do repositório GitHub e vídeo demonstrativo                | Luis Otávio Leitão                             | 08/11                |

#### Business Development with .NET

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Atualização da descrição geral do projeto no README.md para refletir o progresso e novas funcionalidades | Luis Otávio Leitão                             | 10/10                |
| Configuração das rotas padrão para as páginas da aplicação web                                           | Luis Otávio Leitão                             | 12/10                |
| Implementação de rotas personalizadas na aplicação web                                                    | Luis Otávio Leitão                             | 15/10                |
| Desenvolvimento do layout principal da aplicação com cabeçalho, rodapé e navegação customizada usando Bootstrap | Luis Otávio Leitão                             | 20/10                |
| Desenvolvimento das views correspondentes às principais funcionalidades com validações das viewmodels     | Luis Otávio Leitão                             | 25/10                |
| Criação de ViewModels para transferência de dados entre a camada de apresentação e lógica de negócio      | Luis Otávio Leitão                             | 28/10                |
| Implementação dos controladores para manipulação de requisições HTTP e ações CRUD                         | Luis Otávio Leitão                             | 02/11                |
| Testes e validação das funcionalidades implementadas na camada web                                         | Luis Otávio Leitão                             | 05/11                |
| Atualização da documentação no README.md com novas instruções e funcionalidades                           | Luis Otávio Leitão                             | 07/11                |

#### Disruptive Architectures: IoT, IOB & Generative IA

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Desenvolvimento da versão Beta do projeto com funcionalidades principais                                 | Vitor de Melo Marques                          | 20/10                |
| Apresentação das diferenças entre a etapa atual e a proposta inicial                                     | Vitor de Melo Marques                          | 22/10                |
| Detalhamento dos frameworks/bibliotecas Python, APIs e ferramentas a serem utilizadas                    | Vitor de Melo Marques                          | 25/10                |
| Explicação do funcionamento dos recursos/ferramentas dentro da aplicação                                  | Vitor de Melo Marques                          | 28/10                |
| Descrição da aplicação de conceitos de Machine Learning / IA no projeto                                   | Vitor de Melo Marques                          | 30/10                |
| Organização e atualização da documentação no repositório GitHub                                           | Vitor de Melo Marques                          | 02/11                |
| Gravação e upload do vídeo demonstrativo das funcionalidades do projeto                                   | Vitor de Melo Marques                          | 05/11                |
| Preparação do arquivo ZIP com link do repositório, vídeo e documentação organizada                        | Vitor de Melo Marques                          | 08/11                |

#### Java Advanced

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Aperfeiçoamento da aplicação Java com Spring Boot, incluindo refatorações de código                       | Maurício Vieira Pereira                       | 12/10                |
| Implementação da parte relacionada com HATEOAS para atingir o nível de maturidade 3 do modelo de Richardson | Maurício Vieira Pereira                       | 18/10                |
| Implementação opcional do uso de Lombok para otimização do código                                        | Maurício Vieira Pereira                       | 20/10                |
| Continuação da gestão de configuração dos artefatos no GitHub                                            | Maurício Vieira Pereira                       | 22/10                |
| Desenvolvimento de novos testes para os endpoints da API utilizando Postman ou Insomnia                   | Maurício Vieira Pereira                       | 25/10                |
| Exportação das requisições do Postman para validação dos professores                                      | Maurício Vieira Pereira                       | 28/10                |
| Atualização do cronograma de desenvolvimento e documentação da Sprint 2                                   | Maurício Vieira Pereira                       | 30/10                |
| Revisão e finalização das implementações avançadas na aplicação Java                                      | Maurício Vieira Pereira                       | 05/11                |
| Preparação do arquivo README.md atualizado com novas informações e documentação completa                 | Maurício Vieira Pereira                       | 07/11                |
| Garantia de conformidade com os conceitos REST e documentação dos endpoints no GitHub                     | Maurício Vieira Pereira                       | 08/11                |

#### Mobile App Development

| **Atividade**                                                                                               | **Responsável**                               | **Prazo de Entrega** |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------|----------------------|
| Criação do diagrama de integração/comunicação do aplicativo com APIs/banco de dados e sistemas externos   | Vitor de Melo Marques                          | 15/10                |
| Desenvolvimento visual de 5 telas funcionais do aplicativo mobile                                         | Vitor de Melo Marques                          | 25/10                |
| Implementação das chamadas à API em pelo menos 3 telas do aplicativo                                       | Vitor de Melo Marques                          | 30/10                |
| Aplicação de layout adequado e garantia de bom nível de usabilidade                                       | Vitor de Melo Marques                          | 05/11                |
| Testes das funcionalidades implementadas no aplicativo mobile                                             | Vitor de Melo Marques                          | 07/11                |
| Preparação do documento do Word com imagens, explicações, nomes completos e RMs dos integrantes          | Vitor de Melo Marques                          | 08/11                |
| Atualização do repositório GitHub com o código do aplicativo e documentação                                | Vitor de Melo Marques                          | 08/11                |
| Gravação e upload do vídeo demonstrativo do funcionamento do aplicativo                                   | Vitor de Melo Marques                          | 08/11                |
| Geração e disponibilização do APK para instalação                                                        | Vitor de Melo Marques                          | 08/11                |


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

## Endpoints Localhost

**_USUARIO_**

| Método | Endpoint                                                                                                    | Descrição                                                | Corpo da Requisição |
|--------|-------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|---------------------|
| POST   | `http://localhost:8080/usuario`                                                                             | Cria um novo usuário                                     | JSON (no Postman)   |
| POST   | `http://localhost:8080/usuario/list`                                                                        | Cria vários usuários de uma vez                          | JSON (no Postman)   |
| GET    | `http://localhost:8080/usuario/page{page}`                                                                  | Lista todos os usuários paginados(4 usuarios por página) | -                   |
| GET    | `http://localhost:8080/usuario/{id}`                                                                        | Busca um usuário específico por ID                       | -                   |
| PUT    | `http://localhost:8080/usuario/{id}`                                                                        | Atualiza as informações de um usuário por ID             | JSON (no Postman)   |
| DELETE | `http://localhost:8080/usuario/{id}`                                                                        | Deleta um usuário específico por ID                      | -                   |

| Método Com Procedure | Endpoint                                  | Descrição                                                                                          | Corpo da Requisição |
|--------------------|-------------------------------------------|----------------------------------------------------------------------------------------------------|---------------------|
| POST               | `http://localhost:8080/usuario/procedure` | Cria um novo usuário com validação de CPF, email e senha no banco de dados                         | JSON (no Postman)   |
| PUT                | `http://localhost:8080/usuario/procedure/{id}` | Atualiza as informações de um usuário por ID com validação de CPF, email e senha no banco de dados | JSON (no Postman)   |
| DELETE             | `http://localhost:8080/usuario/procedure/{id}` | Deleta um usuário específico por ID e apaga os endereços associados no banco de dados              | -                   |


**_ENDERECO_**

| Método | Endpoint                                     | Descrição                                                  | Corpo da Requisição |
|--------|----------------------------------------------|------------------------------------------------------------|---------------------|
| POST   | `http://localhost:8080/endereco`             | Cria um novo endereço                                      | JSON (no Postman)   |
| POST   | `http://localhost:8080/endereco/list`        | Cria vários endereços de uma vez                           | JSON (no Postman)   |
| GET    | `http://localhost:8080/endereco/page/{page}` | Lista todos os endereços paginados(4 endereços por página) | -                   |
| GET    | `http://localhost:8080/endereco/{id}`        | Busca um endereço específico por ID                        | -                   |
| PUT    | `http://localhost:8080/endereco/{id}`        | Atualiza as informações de um endereço por ID              | JSON (no Postman)   |
| DELETE | `http://localhost:8080/endereco/{id}`        | Deleta um endereço específico por ID                       | -                   |


| Método Com Procedure | Endpoint                                     | Descrição                                                                                                  | Corpo da Requisição |
|--------------------|----------------------------------------------|------------------------------------------------------------------------------------------------------------|---------------------|
| POST               | `http://localhost:8080/endereco/procedure`  | Cria um novo endereço com validação de CEP, número e complemento no banco de dados                         | JSON (no Postman)   |
| PUT                | `http://localhost:8080/endereco/procedure/{id}` | Atualiza as informações de um endereço por ID com validação de CEP, número e complemento no banco de dados | JSON (no Postman)   |
| DELETE             | `http://localhost:8080/endereco/procedure/{id}` | Deleta um endereço específico por ID; Não permite deletar endereços associados a usuários no banco de dados | -                   |

## Endpoints máquina virtual

### Usuário Endpoints

| Método | Endpoint                                                                                              | Descrição                                                | Corpo da Requisição |
|--------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------|----------------------|
| POST   | `http://191.235.233.101:8080/usuario`                                                                 | Cria um novo usuário                                     | JSON (no Postman)    |
| POST   | `http://191.235.233.101:8080/usuario/list`                                                            | Cria vários usuários de uma vez                          | JSON (no Postman)    |
| GET    | `http://191.235.233.101:8080/usuario/page{page}`                                                      | Lista todos os usuários paginados (4 usuários por página) | -                    |
| GET    | `http://191.235.233.101:8080/usuario/{id}`                                                            | Busca um usuário específico por ID                       | -                    |
| PUT    | `http://191.235.233.101:8080/usuario/{id}`                                                            | Atualiza as informações de um usuário por ID             | JSON (no Postman)    |
| DELETE | `http://191.235.233.101:8080/usuario/{id}`                                                            | Deleta um usuário específico por ID                      | -                    |

### Usuário Endpoints com Procedures

| Método | Endpoint                                                                                              | Descrição                                                                                          | Corpo da Requisição |
|--------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|----------------------|
| POST   | `http://191.235.233.101:8080/usuario/procedure`                                                       | Cria um novo usuário com validação de CPF, email e senha no banco de dados                         | JSON (no Postman)    |
| PUT    | `http://191.235.233.101:8080/usuario/procedure/{id}`                                                  | Atualiza as informações de um usuário por ID com validação de CPF, email e senha no banco de dados | JSON (no Postman)    |
| DELETE | `http://191.235.233.101:8080/usuario/procedure/{id}`                                                  | Deleta um usuário específico por ID e apaga os endereços associados no banco de dados              | -                    |

### Endereço Endpoints

| Método | Endpoint                                                                                              | Descrição                                                  | Corpo da Requisição |
|--------|-------------------------------------------------------------------------------------------------------|------------------------------------------------------------|----------------------|
| POST   | `http://191.235.233.101:8080/endereco`                                                                | Cria um novo endereço                                      | JSON (no Postman)    |
| POST   | `http://191.235.233.101:8080/endereco/list`                                                           | Cria vários endereços de uma vez                           | JSON (no Postman)    |
| GET    | `http://191.235.233.101:8080/endereco/page/{page}`                                                    | Lista todos os endereços paginados (4 endereços por página) | -                    |
| GET    | `http://191.235.233.101:8080/endereco/{id}`                                                           | Busca um endereço específico por ID                        | -                    |
| PUT    | `http://191.235.233.101:8080/endereco/{id}`                                                           | Atualiza as informações de um endereço por ID              | JSON (no Postman)    |
| DELETE | `http://191.235.233.101:8080/endereco/{id}`                                                           | Deleta um endereço específico por ID                       | -                    |

### Endereço Endpoints com Procedures

| Método | Endpoint                                                                                              | Descrição                                                                                                  | Corpo da Requisição |
|--------|-------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|----------------------|
| POST   | `http://191.235.233.101:8080/endereco/procedure`                                                      | Cria um novo endereço com validação de CEP, número e complemento no banco de dados                         | JSON (no Postman)    |
| PUT    | `http://191.235.233.101:8080/endereco/procedure/{id}`                                                 | Atualiza as informações de um endereço por ID com validação de CEP, número e complemento no banco de dados | JSON (no Postman)    |
| DELETE | `http://191.235.233.101:8080/endereco/procedure/{id}`                                                 | Deleta um endereço específico por ID; Não permite deletar endereços associados a usuários no banco de dados | -                    |


Essa tabela resume os endpoints da API FraudWatch, indicando quais operações estão disponíveis e quais exigem um corpo
de requisição em JSON, disponível no Postman.


### 🧠 Dica de Saúde Bucal com IA

O projeto conta com integração com **IA local via Ollama**, que gera **dicas de saúde bucal** de forma dinâmica na página inicial.

#### Funcionalidades:

* Ao acessar a tela inicial (`/home`), o sistema exibe automaticamente uma dica gerada por IA.
* O usuário pode clicar no botão **"Gerar outra dica!"** para solicitar uma nova dica instantaneamente, sem recarregar a página.
* A dica é exibida no **idioma da interface atual**, podendo ser **Português** ou **Inglês**, conforme o idioma selecionado.
* A troca de idioma é feita através de botões na parte superior da página, permitindo que o usuário altere o idioma da interface e das dicas geradas.
* Pode ter um delay de alguns segundos para gerar a dica, dependendo do modelo utilizado e da carga do servidor.

#### Tecnologias utilizadas:

* [Ollama](https://ollama.com) rodando localmente com o modelo `tinyllama` (ou outro de sua escolha)
* [LangChain4j](https://github.com/langchain4j/langchain4j) para integração com modelos de linguagem
* Spring Boot + Thymeleaf
* Suporte a internacionalização com `LocaleResolver`

#### Como usar:

1. Certifique-se de que o serviço `ollama` está em execução e que o modelo (`tinyllama`, `mistral`, etc.) foi carregado:

   ```bash
   docker exec -it ollama ollama pull tinyllama
   ```

2. Acesse `/home` para ver a dica inicial.

3. Use os botões no topo da página para trocar entre os idiomas:

   ```
   /lang?lang=pt
   /lang?lang=en
   ```

4. Clique em **"Gerar outra dica!"** para receber uma nova dica da IA no idioma selecionado.

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
   O banco de dados para teste é o H2, que é um banco de dados em memória.

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


### Como executar o projeto WEB via docker
#### Passo 1: Clonar o Repositório
Clone o repositório do projeto para sua máquina local:
```sh
git clone https://github.com/Mauricio-Pereira/FraudWatch.git
cd FraudWatch
```

#### Passo 2: Construir o container Docker
```sh
docker-compose up --build
```

#### Passo 3: Acessar a Aplicação
A aplicação estará disponível em `http://localhost:8080`.

#### OBS.: O idioma é dependente do navegador, caso o navegador esteja em inglês, a aplicação estará em inglês, caso esteja em português, a aplicação estará em português.
#### Caso queira rodar localmente sem o docker, é necessário uma intância do RabbitMQ rodando na porta 5672 para que a aplicação funcione corretamente.
Quando rodar localmente, a aplicação estará disponível em `http://localhost:8080` e nesse caso o idioma da aplicação será definido pelos botões de idioma disponíveis na aplicação.

 




