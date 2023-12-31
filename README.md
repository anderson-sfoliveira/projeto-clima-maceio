<h1 align="center">
  Projeto API Clima Maceió-Alagoas
</h1>

## 💻 Sobre o projeto

O projeto consiste no desenvolvimento de uma API Java utilizando o framework Spring Boot, com o 
objetivo de fornecer informações atualizadas sobre o clima da cidade de Maceió, Alagoas. 
A API utiliza a plataforma HGBrasil como fonte de dados meteorológicos.

- [x] **API de Clima:** A API de clima utilizada para buscar informações meteorológicas é a [HGBrasil](https://hgbrasil.com/).
- [x] **Job Agendado para Consulta do Clima:** O projeto inclui um job agendado que consulta periodicamente 
a API de clima da HGBrasil. Esse job é executado em intervalos de tempo configuráveis no arquivo application.yaml.
- [x] **Armazenamento dos Dados Climáticos:** Os dados climáticos obtidos da API da HGBrasil são armazenados em um banco de dados PostgreSQL.
- [x] **Endpoint REST para Consulta de Registros de Temperatura:** A API oferece um endpoint seguro para que os usuários possam consultar os registros de temperatura armazenados no banco de dados. O acesso ao endpoint é protegido por autenticação com token JWT.
- [x] **Testes Automatizados:** O projeto inclui testes automatizados para garantir a qualidade e integridade do software. Utilizando o framework JUnit e o Mockito.
- [x] **Documentação com SpringDoc OpenAPI UI:** A API é documentada utilizando o SpringDoc OpenAPI UI, fornecendo uma documentação dos endpoints.

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina o Java 17 e o [Maven](https://maven.apache.org/).

Além disto é bom ter um editor de código, como o: [Intellij](https://www.jetbrains.com/pt-br/idea/). E também o
[Insomnia REST](https://insomnia.rest/) para testar os endpoints.

Crie um banco de dados PostgreSQL e configure as credenciais de acesso no arquivo application.yaml localizado
na pasta src/main/resources.

O projeto conta com o arquivo docker-compose.yml, o qual permite configurar e subir um banco de dados PostgreSQL utilizando o Docker.

Para montar a "cron expression" no arquivo application.yaml e agendar o job que consulta 
periodicamente a API de clima da HGBrasil, você pode utilizar o site [Oracle Docs](https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm). Este site facilita a criação e compreensão das expressões cron.

Como o sistema conta com autenticação OAuth2 e JWT, será criado automaticamente um usuário com 
o nome de usuário "oliveira" e senha "123456" ao iniciar o aplicativo. Você pode utilizar essas
informações para solicitar o token de acesso através do fluxo de autenticação.

Para obter o token de acesso, você pode realizar uma requisição POST para o endpoint de 
autenticação (/auth/login), enviando as credenciais do usuário "oliveira" e senha "123456" no corpo da requisição.
O servidor irá autenticar o usuário e, caso as credenciais sejam válidas, retornará o token de acesso que poderá ser utilizado 
nas requisições subsequentes para acessar os endpoints protegidos da API.

#### 🎲 Rodando a API

```bash

# Clone este repositório
$ git clone https://github.com/anderson-sfoliveira/projeto-clima-maceio

# Importe o projeto para dentro do Intellij.

# Inicie a aplicação.

# O servidor inciará na porta:8080

# http://localhost:8080/swagger-ui/index.html

```


## 🦸🏾 Autor

<a href="https://www.linkedin.com/in/anderson-sfoliveira/">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/2175235?s=400&u=432d3456eb62f2df111abdccd667976321f6f74a&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Anderson Oliveira</b></sub></a> <a href="https://www.linkedin.com/in/anderson-sfoliveira/" title="Anderson Oliveira"></a>
 <br />

[![Linkedin Badge](https://img.shields.io/badge/-Anderson-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/anderson-sfoliveira/)](https://www.linkedin.com/in/anderson-sfoliveira/)
[![Gmail Badge](https://img.shields.io/badge/-anderson.sfoliveira@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:anderson.sfoliveira@gmail.com)](mailto:anderson.sfoliveira@gmail.com)
