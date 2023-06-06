# Mail Mover
  O objetivo da nossa plataforma é criar um sistema de pick up points que diferentes negócios possam usar para fazer a entrega dos seus produtos, facilitando a vida aos seus clientes. 


## Roles: 
  - Team Coordinator: Pedro Coelho
  - Product owner: Luca Pereira
  - QA Engineer: Diogo Silva
  - DevOps master: Manuel Diaz
  - Developer: Pedro Coelho, Luca Pereira, Diogo Silva, Manuel Diaz

## Project bookmarks: 
  - Jira: https://tqsproject.atlassian.net/jira/software/c/projects/TP/boards/1?atlOrigin=eyJpIjoiMmVjNWExMzRjYWRmNGUxY2I2Y2Y5N2Y1NzRlYTJhYWIiLCJwIjoiaiJ9

  - Sonarcloud dashboard: https://sonarcloud.io/summary/overall?id=PedroC55_TQS_Project

## Como executar:
  - docker-compose up

## Login para a store:
  - Utilizador: user
  - Password: user

## Login para a página de admin:
  - Utilizador: admin
  - Password: admin

## Mudanças pós-apresentação:
  - Decidimos dar @Disabled nos testes efetuados com o Selenium para o frontend da store, pois apenas estão a funcionar numa máquina, de modo a que o professor consiga correr tudo sem erros;
  - O teste funcional do frontend da pagina admin em cypress/e2e/... mudou de nome para algo mais apropriado e algumas linhas foram comentadas visto apenas funcionarem para certos casos, fazendo com que o teste falhe na maior parte dos casos;
  - No Login do admin foi acrecentada uma credencial hardcoded visto não haver uma página para criar uma conta.
