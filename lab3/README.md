# LAB3 NOTES

## 3.1 Employee manager

### Review questions
Para simplicar, refiro-me aos ficheiros de teste com (letra maiúscula)(*)
- a) Nos testes com maior encadeamento, verifica-se o tamanho da lista de empregados encontrados e se os empregados encontrados são apenas os esperados (visível nos ficheiros de teste A*, B*, D*, E*).
- b) No ficheiro de teste B* recorre-se a um Mock para simular o EmployeeRepository e assim evitar acesso à BD (respostas hardcoded que testam apenas a lógica dos métodos).
- c) 
@Mock (da biblioteca Mockito) é usado para simular o comportamento de um objeto real (classe ou interface), sem o custo de acesso entre camadas (ex: acesso à BD). 
@MockBean (da biblioteca Spring Boot) é usado para simular um Bean (componente) do Spring, para testar a lógica de negócio de um serviço, sem o custo de acesso à BD.
- d) O papel do _application-integrationtest.properties_ é definir as propriedades de teste de integração, como a base de dados a usar, o username e password. Neste caso só será utilizado quando se usa a BD mysql, se usarmos a autoconfiguração irá ser usada a BD H2 e o ficheiro não terá influência.
- e) Estratégias para aceder à API:
    <br>C* - simula as depedências com Mock e testa a lógica dos métodos, foco em unit testing
    <br>D* - testes de integração para a camada de serviço, com acesso a uma BD real sem servidor a correr (objeto MockMvc)
    <br>E* - testes de integração para a full stack da aplicação com uma BD real e um servidor a correr (cliente REST)

    Os testes aproximam-se mais da realidade à medida que se avança na lista, com custo em performance (3.7s, 5s, 5.5s respetivamente com i5-1135G7).

## 3.2 Car Service
Depois de criar o código esqueleto Java, comecei com os testes:

_CarController_WithMockServiceTest.java_ - testes com MockMvc para fazer Mock do _CarManagerService_ (semelhante ao C* do exercício anterior)
- Testes positivos: adição, listagem e consulta de carros
- Testes negativos: consulta de carro inexistente

_CarManagerService_UnitTest.java_ - testes unitários para a lógica de negócio do serviço (semelhante ao B* do exercício anterior)
- Testes positivos: listagem e consulta de carros
- Testes negativos: consulta de carro inexistente

_CarRepositoryTest.java_ - testes aos serviços de acesso de dados (semelhante ao A* do exercício anterior)
- Testes positivos: listagem e consulta de carros
- Testes negativos: consulta de carro inexistente

_CarControllerTemplateIT.java - testes de integração para verificar a API, percebi que não lidava com algumas exceções no _CarController_ (semelhante ao E* do exercício anterior)
- Testes positivos: adição, listagem e consulta de carros
- Testes negativos: consulta de carro inexistente
