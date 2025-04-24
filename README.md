# 💱 Conversor de Moedas com API em Java

Este é um projeto desenvolvido com o objetivo de criar um **Conversor de Moedas** interativo via console utilizando a linguagem **Java**. O diferencial do projeto está na obtenção **dinâmica das taxas de câmbio** por meio de uma **API externa**, garantindo dados atualizados em tempo real.

## 🎯 Objetivo

Desenvolver uma aplicação Java que:

- Interaja com o usuário através do terminal;
- Ofereça um menu com **pelo menos 6 opções de conversão de moedas**;
- Realize o **consumo de uma API de câmbio em tempo real**;
- Analise a **resposta JSON** retornada;
- Filtre e apresente as informações relevantes de forma clara;
- Exiba os resultados de forma amigável e objetiva.

## 🧰 Tecnologias e Ferramentas Utilizadas

- **Java 17** (ou outra versão compatível)
- **IDE IntelliJ IDEA**
- **HTTP Client** (Java `HttpURLConnection` ou bibliotecas como `HttpClient`)
- **API de Câmbio** (ex: [ExchangeRate API](https://www.exchangerate-api.com/)
- **JSON Parsing** com bibliotecas como `org.json` ou `Gson`

## 🚀 Funcionalidades

- Menu com as seguintes opções de conversão:
  - Real (BRL) → Dólar (USD)
  - Real (BRL) → Euro (EUR)
  - Dólar (USD) → Real (BRL)
  - Euro (EUR) → Real (BRL)
  - Dólar (USD) → Euro (EUR)
  - Euro (EUR) → Dólar (USD)
- Leitura e interpretação de dados da API em tempo real;
- Tratamento de erros, como:
  - Falha na conexão com a API;
  - Moeda inválida;
  - Entrada não numérica;
- Apresentação clara e intuitiva dos resultados.

## 🏗️ Etapas de Desenvolvimento

1. **Configuração do Ambiente Java**: instalação do JDK e configuração da IDE;
2. **Criação da estrutura do projeto Java**;
3. **Implementação do consumo da API externa de câmbio**;
4. **Análise e extração das informações do JSON retornado**;
5. **Montagem do menu de seleção no console**;
6. **Interação com o usuário e exibição de resultados**.

