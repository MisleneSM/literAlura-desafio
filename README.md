<h1 align="center"> LiterAlura - Catálogo de Livros </h1>

<div align="center">

Desenvolvido por:
<br>Mislene Moura - [Linkedin](https://www.linkedin.com/in/mislene-silva-moura-1211531b4//) |
[Github](https://github.com/MisleneSM)
</div>

## Índice

* [1. Resumo do projeto](#1-resumo-do-projeto)
* [2. Objetivos de aprendizagem](#2-objetivos-de-aprendizagem)
* [3. Resultado do Projeto](#3-resultado-do-projeto)
* [4. Instruções de Instalação](#4-instruções-de-instalação)
* [5. Tecnologias Utilizadas](#5-tecnologias-utilizadas)

***

## 1. Resumo do Projeto🤩

Desenvolvido um catálogo de livros tendo como principal objetivo trabalhar com ferramentas do Java, realizando solicitações a uma API de livros, manipulando dados JSON, armazenando em um banco de dados e, por fim, filtrando e mostrando os livros e autores de interesse.

OBJETIVOS DO PROJETO: Desenvolver um Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação:

* Buscar livro pelo título.
* Listar livros registrados.
* Listar autores registrados.
* Listar autores vivos em um determinado ano.
* Listar livros em um determinado idioma.

Os livros serão buscados através de uma API específica chamada [Gutendex](https://gutendex.com/).

Passos completados:

* Configuração do Ambiente Java;
* Criação do Projeto;
* Consumo da API;
* Análise da Resposta JSON;
* Inserção e consulta no banco de dados;
* Exibição de Resultados aos Usuários;

## 2. Objetivos de aprendizagem✅

- Java
- Spring Boot
- Spring Data JPA
- Jackson
- PostgreSQL

## 3. Resultado do Projeto📝

###### Resultado Final
![Gif.gif](src%2Fmain%2Fimg%2FGif.gif)

## 4. Instruções de Instalação🔍

* Clona este repositório.
* Instale o PostgreSQL
* Configure a base de dados do PostgreSQL
* Configuração de arquivo -> SRC - Resources - Application.properties (o mesmo terá armazenado as informações a seguir)
* Altere as informações descritas nos parênteses com seu nome, e uma senha.

```
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username= (Digite seu nome)
spring.datasource.password= (Digite uma senha)
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true`

```
OBS: Caso deseje compartilhar este sistema com outra pessoa, apague as informações que foram alteradas (acrescente novamente os parênteses com as descrições para que outros usuários possam realizar o preenchimento indicando seu próprio nome e senha).

* Atualize as dependências do Maven.
* Executa a aplicação.


## 5. Tecnologias Utilizadas✅

<div>
    <img src="https://img.icons8.com/color/452/java-coffee-cup-logo--v1.png" alt="Java Logo" width="50" height="50">
    <img src="https://img.icons8.com/color/452/intellij-idea.png" alt="IntelliJ Logo" width="50" height="50">
    <img src="https://img.icons8.com/color/452/spring-logo.png" alt="Spring Boot Logo" width="50" height="50">
    <img src="https://img.icons8.com/color/452/postgreesql.png" alt="PostgreSQL Logo" width="50" height="50">
</div>
