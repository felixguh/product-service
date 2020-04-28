Projeto: product-service

Descrição: projeto para criação de produtos(MarketPlace).

Pré requisitos: 

Java: >=11

Gradle: >=4.4.1

SO: Linux/Unix

Docker: >=19.03.8

Docker compose: =3.3

STS: >=3.9.9

Obs: é necessário serguir os passos para para que tudo funcione perfeitamente.

Passo 1: baixe o repositório em sua máquina.

Passo 2: acesse o repositório product-service

Passo 3: execute o comando: sudo chmod +x gradlew

Passo 4: execute o comando: ./gradlew build

Passo 5: execute o comando: cd devops/

Passo 6: execute o comando para iniciar o banco de dados: sudo docker-compose up -d

Passo 7: importe o projeto no STS e rode o mesmo pressionando o botão direito do mouse em cima do projeto.

Passo 8: Clique na opção Run As.

Passo 9: Clique na opção Spring Boot App.

Ou rode via terminal: acesse a seguinte pasta product-service/build/libs e rode o comando java -jar product-service-0.0.1-SNAPSHOT.jar


Para acessar o Swagger da aplicação o utilize a rota: http://localhost:8080/api/product-service/swagger-ui.html


Se divirta com os testes, lembrando que foi feito com carinho :)


Obs: Repita os passos dos outros repositórios.

order-service = https://github.com/felixguh/order-service

customer-service = https://github.com/felixguh/customer-service

