# LAB 6 NOTES

## 6.1 Euromillions
Neste projeto havia um Security Hotspot que era a utilização de `Random` para gerar números aleatórios. A solução proposta foi a utilização de `SecureRandom` que é mais seguro.

![SonarQube overview](screenshots/lab6_1.png)

No que toca a manutenção de código, foram encontrados 25 problemas que demorariam 1h e 28m a resolver.

A destacar o Code Smell de alto impacto:

![SonarQube smell](screenshots/lab6_1_smell.png)

Para correr e ir ao dashboard do SonarQube, basta correr o comando `mvn clean verify sonar:sonar   -Dsonar.projectKey=euromillions   -Dsonar.projectName='euromillions'   -Dsonar.host.url=http://127.0.0.1:9000   -Dsonar.token=sqp_39da26c494f39f789dd961328ca57dfabff5a8e5` e aceder a `http://localhost:9000/`.
O token expira em 30 dias.

## 6.2 Cars Service
A technical debt correspondia a 27 minutos - demoraria a um programador cerca de 27 minutos para encontrar e corrigir os problemas encontrados pelas ferramentas de análise.

A análise do SonarQube revelou o seguinte:

![SonarQube cars-service antes](screenshots/lab6_2before.png)

Ambos os Security Hotspots devem-se a ter usado @RequestMapping ao invés de @GetMapping no CarController.java.

- Adicionei teste para o método `saveCar` 
- Tive de modificar a lógica do controlador
- Acrescentei testes para inserção de um carro inválido (que não tinha antes)

Resultados abaixo:

![SonarQube cars-service depois](screenshots/lab6_2after.png)

Para correr e ir ao dashboard do SonarQube, basta correr o comando `mvn clean verify sonar:sonar   -Dsonar.projectKey=carsservice   -Dsonar.projectName='carsservice'   -Dsonar.host.url=http://127.0.0.1:9000   -Dsonar.token=sqp_225c70f3cc776754690324f7437739b58595734d   -DskipITs=false` e aceder a `http://localhost:9000/`.
O token expira em 30 dias.

## 6.3 IES Project

Ao utilizar o projeto de IES _KeyUsageProfiler_ foram encontrados os seguintes issues:

![SonarQube ies-project](screenshots/lab6_3.png)

- Como seria de esperar a code coverage é de 0% (já que não haviam testes no projeto)
- No que toca ao esforço necessário para os corrigir, o Sonar Qube estima que fosse de 1 dia e 5 horas.
- Os issues de reliability estão relacionados com os imports não corresponderem a uma regex ideal, exceções de Null Pointer e alguns prints que restaram (em vez de logs).
- Os issues de alto impacto de maintainability são apenas 1 constante que não está capitalizada e 3 atributos que não são serializáveis (apenas o objeto do qual fazem parte o é).

Nota Adicional: não foi definido nada em grupo, mas poderia ser benéfico uma cobertura superior ao standard de 80% e também manter em 0 os problemas de segurança, visto que era uma aplicação com um caso de uso empresarial (medição de desempenho de cada colaborador na equipa)