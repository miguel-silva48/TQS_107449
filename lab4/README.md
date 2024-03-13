# LAB 4 NOTES

- Foi necessária a reinstalação do firefox sem ser pelo _snap_, instalei pelo _apt_, mas acabei por instalar também o Chrome que usei para a maioria dos testes.

## 4.1 WebDriver
- Tirei partido das implementações fornecidas no exemplo discutido e no GitHub  associado.
- Ao fazer o _refactor_, os métodos _setup_ e _teardown_ não foram necessários, devido à extensão do _SeleniumJupiter_.

## 4.2 Selenium IDE
- Comecei por instalar a extensão e gravar um exemplo de interação com o site da _blazedemo_. 
- Adicionei o último _assert_ diretamente no IDE, embora a configuração do mesmo não faça sentido, para verificar o valor do _title_ existe um _assert title_ no entanto o valor da _string_ não vai para o campo _value_, mas sim para o campo _target_.
- Exportei o teste para Java e converti para Junit5, apenas tive de mudar algumas anotações e _imports_.
- Para usar a extensão _SeleniumJupiter_ pude remover o _setup_ e _teardown_ do teste e aproveitei por adicionar _asserts_ quando mudava de página.

## 4.3 Page Object
- Comecei por criar 3 ficheiros Java, um para a página inicial, outro para a página de reserva e outro para a página de compra (não criei para a página de confirmação já que não havia interação com a mesma).
- Aproveitei por diferenciar a obtenção dos elementos da página nas várias classes java e adicionar uma verificação do tamanho da lista (aquando escolha do voo, já que aceito um valor inteiro)

## 4.4 Docker Browser
- Decidi fazer com o Chrome e Edge, embora tenha tentado com o Opera sem sucesso (apesar de ser _Chromium-based_ e a imagem estar disponível)
- Este exercício requer permissões de _super user_ para correr (ao contrário dos outros), pode ser que adicionar o docker aos sudoers mude a necessidade de _sudo_.

Para correr os exercícios 4.1 a 4.3, basta correr o comando `mvn clean test` na pasta do projeto.
Para o exercício 4.4, é necessário correr o comando `sudo mvn clean test` na pasta do projeto.
