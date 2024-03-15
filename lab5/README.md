# LAB 5 NOTES

- Em todos os exercícios troquei as regex pelas expressões recomendadas no enunciado. (ex: `\\d` -> `{int}`)

## 5.1 Calculator
- Tirei partido da implementação fornecido, alterando apenas a escolha de operação para um switch case.
- Adicionei 2 cenários: multiplicação, divisão e operação inválida (divisão por 0).
- Para lidar com este último, decidi detetar o 2º valor a 0 na divisão e acrescentar um Double.NaN na stack, ao encontrar este Double.NaN retorno uma exceção.

## 5.2 Book Search
- Novamente tirando partido da implementação fornecida, adicionei novos métodos de pesquisa: por autor e por título (prefixo e contido).
- Usei _@DataTableType_ para armazenar os dados dos livros (da tabela no _.feature_) e _@ParameterType_ para definir a _iso8601Date_


## 5.3 Web Automation
- Neste exercício tirei partido da implementação fornecida e de um exercício do lab anterior.
- Adicionei a verificação das cidades de origem e destino (que não tinha antes por lapso) e também de persistência na própria página (apenas para continuidade do user storie).

NOTA: apenas criei 1 cenário otimista, já que o site nunca faz tracking do preço certo e a confirmação não tem dados pedidos anteriormente, achei que não valia a pena criar um teste para "verificar que falhava".
