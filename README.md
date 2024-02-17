
# Documentação de Instalação e Execução do Projeto App-Task-Manager

Este projeto requer algumas dependências e configurações específicas para ser executado corretamente. Siga os passos abaixo para configurar o ambiente e iniciar o projeto




## Stack utilizada

**Front-end:** Node, JS, Html, CSS

**Back-end:** Java, Spring, Maven, Postgres, Docker


## Pré-requisitos:

Certifique-se de ter: Java 21, PostgreSQL, Maven, Docker e Docker Compose.
## Instalação LINUX

Clone o projeto.

Instale o PostgreSQL e verifique se o serviço está em execução. Use o comando abaixo para verificar o status:


```bash
  sudo systemctl status postgresql
```

Se estiver inativo, inicie o serviço com:

```bash
  sudo systemctl start postgresql
```

Entre na partição:  `cd /backend`

E utilize o comando Maven:   `mvn clean`

Após, realize o comando Maven:  `mvn package`

Agora vá para raiz do projeto:  `cd ..`

Você precisa liberar a porta 5432 para que seja utilizada pelo container, então utilize o comando: `sudo systemctl stop postgresql`

Neste momento você pode executar o comando: `docker-compose up --build`


Você obterá no console da aplicação, os endereços dedicados para o frontend e backend:

`
Available on:

http://127.0.0.1:8080

http://172.23.0.4:8080
`


## Aplicação

![App Screenshot](https://i.ibb.co/yWM7w4L/Screenshot-from-2024-01-25-17-14-11.png)


![App Screenshot](https://i.ibb.co/prfxsxh/Screenshot-from-2024-01-25-17-15-45.png)
## Autores

- [@alibiohenrique](https://github.com/alibiohenrique)

- [@LinconDC](https://github.com/LinconDC)
