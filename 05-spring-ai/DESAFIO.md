# Desafio Spring AI - Minha Evolucao

## O que o projeto faz
API que processa comandos de voz sobre financas pessoais. O cliente envia um
audio, que e transcrito para texto (TranscriptionModel); a IA interpreta a
intencao e, via Tool Calling, executa casos de uso reais da aplicacao para
criar (PersistTransactionUseCase) ou consultar transacoes
(ListTransactionsByCategoryUseCase). A resposta pode ser devolvida em audio
(TextToSpeechModel).

## Tecnologias usadas
- Java + Spring Boot
- Spring AI (ChatClient, @Tool / Tool Calling, transcricao e text-to-speech)
- Arquitetura em camadas (domain, application, infrastructure)
- JPA para persistencia
- Gradle

## Melhoria que implementei
Adicionei validacao de regras de negocio na criacao de transacoes, na propria
entidade de dominio Transaction. Agora uma transacao so e criada se tiver
descricao preenchida, valor maior que zero e categoria valida. Coloquei a
validacao no dominio de proposito: assim tanto o fluxo REST quanto o fluxo da
IA (Tool Calling) ficam protegidos contra dados invalidos, sem duplicar codigo.
Tambem criei testes unitarios (TransactionTest) cobrindo os casos validos e
invalidos.

## Arquivos alterados
- `src/main/java/dio/budgeting/domain/Transaction.java` (validacao adicionada)
- `src/test/java/dio/budgeting/domain/TransactionTest.java` (novo teste)

## Como executar
1. Configure sua chave da OpenAI: `export OPENAI_API_KEY="sua_chave"`
2. Rode a aplicacao: `./gradlew bootRun`
3. A API sobe em http://localhost:8080

## Como testar o fluxo principal
- Crie uma transacao valida (por audio ou pelo endpoint REST), ex.:
  "gastei 50 reais em mercado".
- Consulte as transacoes por categoria.
- Teste a validacao enviando valor 0 ou negativo, ou descricao vazia:
  a aplicacao rejeita e retorna uma mensagem de erro clara em vez de salvar.
- Rode os testes de dominio: `./gradlew test --tests "dio.budgeting.domain.TransactionTest"`

## O que aprendi
Entendi como o Spring AI conecta um modelo de linguagem a funcoes reais usando
Tool Calling, como funciona a transcricao de audio e a geracao de voz, e por que
colocar regras de negocio no dominio deixa a aplicacao mais robusta e evita
duplicacao entre os diferentes pontos de entrada.
