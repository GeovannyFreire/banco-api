# Banco API

API RESTful para gerenciamento de contas bancárias de uma cooperativa de crédito, 
desenvolvida como desafio técnico para a vaga de estágio Back-End na Pacto Mais.

---

## 🛠 Tecnologias utilizadas

- Java 17
- Spring Boot 3.4.1
- Spring Data JPA / Hibernate
- Banco de dados H2 (em memória)

---

## ▶️ Como rodar o projeto localmente
> Requer JDK 21 ou superior instalado na máquina.
1. Clone o repositório:
```bash
   git clone https://github.com/GeovannyFreire/banco-api.git
```
2. Abra o projeto no IntelliJ (ou outra IDE de sua preferência).
3. Execute a classe `BancoApiApplication.java`.
4. A aplicação sobe em `http://localhost:8080`.

O banco é criado automaticamente pelo Hibernate a partir das entidades — não é necessário rodar nenhum script SQL manualmente.

### Acessando o console do banco (H2)

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bancodb`
- User: `sa`
- Password: *(em branco)*

---

## 🧱 Modelagem

- **Correntista**: nome, documento (CPF único), email, telefone. Pode ter várias contas.
- **Conta** *(classe abstrata, mapeada com herança `SINGLE_TABLE`)*: número, agência, saldo.
  - **ContaCorrente**: possui limite. Saque permitido até `saldo + limite`.
  - **ContaPoupanca**: saque permitido apenas até o saldo.
- **Transacao**: tipo (`DEPOSITO`, `SAQUE`, `TRANSFERENCIA`), valor, data/hora, conta de origem.

---

## 📡 Endpoints disponíveis

### Correntistas

| Método | Rota | Descrição |
|---|---|---|
| POST | `/correntistas` | Cadastra um correntista |
| GET | `/correntistas` | Lista todos os correntistas |
| GET | `/correntistas/{id}` | Busca um correntista por id |

**Exemplo — cadastrar correntista**
```http
POST /correntistas
Content-Type: application/json

{
  "nome": "Joao Silva",
  "documento": "12345678900",
  "email": "joao@email.com",
  "telefone": "83999999999"
}
```

### Contas

| Método | Rota | Descrição |
|---|---|---|
| POST | `/contas` | Abre uma conta |
| GET | `/contas` | Lista todas as contas |
| GET | `/contas/{id}` | Busca uma conta por id |
| POST | `/contas/{id}/depositar` | Realiza um depósito |
| POST | `/contas/{id}/sacar` | Realiza um saque |
| GET | `/contas/{id}/extrato` | Lista as transações da conta |

**Exemplo — abrir conta**
```http
POST /contas
Content-Type: application/json

{
  "correntistaId": 1,
  "tipo": "CORRENTE",
  "limite": 500
}
```
> `tipo` aceita `"CORRENTE"` ou `"POUPANCA"`. O campo `limite` só é usado quando `tipo` é `CORRENTE`.

**Exemplo — depositar / sacar**
```http
POST /contas/1/depositar
Content-Type: application/json

{ "valor": 100 }
```

---

## ✅ O que foi feito

- Cadastro e consulta de correntistas.
- Abertura e consulta de contas (Conta Corrente com limite, Conta Poupança).
- Depósito e saque com validação de regras de negócio (limite na conta corrente, saldo na poupança).
- Extrato de transações por conta.
- Tratamento de erros padronizado (respostas em JSON com mensagem clara e status HTTP adequado).
- Modelagem com herança JPA (`SINGLE_TABLE`) e polimorfismo — o método `podeSacar()` é implementado de forma diferente em cada tipo de conta.

---

## ⏳ O que ficou de fora e por quê

- **Rendimento mensal (Conta Poupança) e juros sobre saldo negativo (Conta Corrente)** — não implementados por limitação de tempo. A abordagem seria criar métodos `aplicarRendimento(taxa)` e `aplicarJuros(taxa)` no `ContaService`, recebendo a taxa via parâmetro no endpoint, atualizando o saldo e registrando uma `Transacao` correspondente, seguindo o mesmo padrão usado no depósito e no saque.
- **Testes unitários** — não implementados por limitação de tempo. Seriam feitos com JUnit + Mockito, cobrindo principalmente as regras de `podeSacar()` de cada tipo de conta e o fluxo de depósito/saque no `ContaService`.
- **Swagger/OpenAPI** — não implementado por limitação de tempo. Bastaria adicionar a dependência `springdoc-openapi-starter-webmvc-ui` ao `pom.xml` para gerar a documentação automaticamente a partir dos controllers já existentes.

---

## 📝 Observação sobre o processo de aprendizado

Comecei a estudar Java e Spring Boot muito recentemente, motivado pela necessidade de usar essas tecnologias na Fábrica de Software da minha faculdade, onde desenvolvo um projeto fullstack.
Para este desafio, usei IA como ferramenta de apoio para entender conceitos que eu ainda não conhecia (JPA, herança com Spring Data, `@Transactional`, entre outros), mas priorizei compreender e ser capaz de explicar cada decisão tomada, em vez de apenas copiar código pronto.
Tenho consciência de que ainda tenho muito a evoluir e me comprometo a aprofundar esse conhecimento continuamente.

> **Nota sobre a versão do Java:** o desafio especifica Java 8. Desenvolvi o projeto em Java 17 com Spring Boot 4.1.1 — a versão mais recente do framework no momento, que já traz uma reformulação de nomes de dependências (ex: `spring-boot-starter-web` virou `spring-boot-starter-webmvc`). Estou ciente de que o Spring Boot 4 tem como base recomendada o Java 21, e disponível para ajustar a versão ou discutir essa escolha na conversa técnica.