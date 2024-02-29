# LAB2 NOTES

## 2.1 Stock
- Comecei por criar as classes Java necessárias segundo a modelação apresentada no guião.
- Nos testes fiz o Mock do serviço como pretendido e o SuT seria o portfolio.
- Dado que fiz lookup de 2 tipos de ações diferentes, no verify fiz questão de garantir que seriam feitas apenas 2 chamadas.
- Na alínea b) usei os assertions hamcrest ao invés do junit, enquanto mantive a mesma lógica de testes.

Para testar basta estar no diretório lab2_1stock e correr `mvn test`.

## 2.2 Geocoding
- Comecei por aproveitar o repositório do professor que já tinhas as classes Java implementadas.
- Para testar o AddressResolver#findAddressForLocation, fiz mock do cliente http e o SuT seria o AddressResolver.
- De modo a ter respostas padrão fiz as pesquisas que estavam nos testes diretamente na API e copiei a resposta JSON para o código.
- Tratei do caso de haver ou não resposta JSON válida e verifiquei que era efetuada só 1 chamada.
- Apaguei o diretório `demo` por ser desnecessário após os testes funcionarem.

Para testar basta estar no diretório lab2_2geocoding e correr `mvn test`.

## 2.3 Integration tests (mesmo projeto do 2.2)
- Nos testes de integração a lógica implementada é a mesma, mas sem mocks, já que era usada a API real.
- Ao correr apenas `mvn test` os testes de integração não são corridos.

Para correr os testes de integração basta estar no diretório lab2_2geocoding, ter conexão à internet e correr `mvn install failsafe:integration-test`.