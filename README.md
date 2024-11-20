<h1 style="font-weight: bold; text-align: center;">Memelandia</h1>

<p style="text-align: center;">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"  alt="Java badge"/>
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring badge">
    <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" alt="Docker badge">
    <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Maven badge">
</p>

<p style="text-align: center;">
 <a href="#started">Começando</a> • 
  <a href="#routes">API Endpoints</a>
</p>

<p style="text-align: center;">
  <b>Projeto final do curso de Especialista Back-end Java.</b>
</p>

<h2 id="started">🚀 Começando</h2>

Para rodar este projeto, é necessário que tenha Docker instalado em sua máquina e executar o comando `compose.yaml`,
acessar o devidos endpoints citados mais abaixo e testar.

<h3>Pré-requisitos</h3>

Aqui tem uma lista de dependências necessárias para rodar este projeto

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Spring 3.3.5](https://spring.io)


As dependências do Spring poderão ser encontradas no arquivo `pom.xml` na raíz de cada serviço.

<h3>Clonando</h3>

Para clonar este repositório, será necessário executar este código bash abaixo no terminal:

```bash
git clone https://github.com/cryslefundes/memelandia.git
```

<h3>Rodando o projeto</h3>

Para começar, rode este comando em seu terminal:

```bash
docker compose up -d
``````
A partir dessa execução, você terá acesso a alguns endpoints para monitorar se os serviços estão de pé, tester os endpoints e também necessário para o Zookeeper realizar a descoberta dos serviços, além de tracing route com Zipkin.

<h3>Acessando o banco de dados</h3>

O banco utilizado para o projeto foi um mais simples e de memória, somente para testar a aplicação, sendo necessário em um ambiente real, utilizar de conceitos como consistência eventual, sendo assim, seria mais apropriado utilizar de um banco de dados não relacional como o MongoDB futuramente.

O arquivo `application.properties`, possui o usuário e senha para acesso ao banco de dados, podendo ser alterado facilmente. 


<h2 id="routes">📍 API Endpoints</h2>

Aqui você encontrará os endpoints que serão postos de pé ao iniciar a aplicação, sendo eles:

<h3>Zipkin</h3>

| Rota                      | Descrição                                                                |
|---------------------------|--------------------------------------------------------------------------|
| <kbd>localhost:9411</kbd> | Nesta rota, será possível acessar o painel do Zipkin para tracing route. |

<h3>Acesso as métricas</h3>

| Rota                                 | Descrição                                                         |
|--------------------------------------|-------------------------------------------------------------------|
| <kbd> localhost:8081/actuator </kbd> | Retorna uma página HTML com as métricas do endpoint de usuário.   |
| <kbd> localhost:8082/actuator </kbd> | Retorna uma página HTML com as métricas do endpoint de categoria. |
| <kbd> localhost:8083/actuator </kbd> | Retorna uma página HTML com as métricas do endpoint de memes.     |

<h3>Serviços</h3>

| Rota                                              | Descrição                                                                                    |
|---------------------------------------------------|----------------------------------------------------------------------------------------------|
| <kbd> localhost:8081/swagger-ui/index.html </kbd> | Retorna uma página HTML com o painel do Swagger para realizar testes no serviço de Usuário.  |
| <kbd> localhost:8082/swagger-ui/index.html </kbd> | Retorna uma página HTML com o painel do Swagger para realizar testes no serviço de Categoria. |
| <kbd> localhost:8083/swagger-ui/index.html </kbd> | Retorna uma página HTML com o painel do Swagger para realizar testes no serviço de Meme.     |


<h2 id="contribute">📫 Contribute</h2>

Para contribuir, você poderá utilizar git flow, para isso, você precisará somente executar os seguintes passos:

1. `git clone https://github.com/cryslefundes/memelandia.git`
2. `git flow feature start FEATURE_NAME`
3. Siga os padrões de commit `feat: ` para adicionar features no projeto
4. Finalize a feature com o comando: `git flow feature finish FEATURE_NAME`
5. Abra um Pull Request explicando o problema que está sendo resolvido ou feature feita, caso exista, adicione uma screenshot de modificações visuais e espere pela review!

<h3>Links que podem ajudar</h3>

[📝 Como criar um Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Padrão de commit](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

Obrigado ❤️!