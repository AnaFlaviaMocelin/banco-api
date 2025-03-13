# API de Lançamentos Bancários

## 📌 Descrição
Esta API RESTful permite realizar lançamentos bancários de débito e crédito nas contas dos clientes. Além disso, permite a criação de novas contas e consulta de saldo.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (para testes)
- JUnit e Mockito (para testes automatizados)

## 📂 Estrutura do Projeto
```
/src
  ├── main/java/com/bancoApi
  │   ├── controller (Controladores REST)
  │   ├── service (Regras de negócio)
  │   ├── repository (Acesso a dados)
  │   ├── model (Entidades)
  │   ├── dto (Objetos de transferência de dados)
  ├── test/java/com/bancoApi (Testes automatizados)
```

## 🔧 Configuração do Ambiente
1. **Clone o repositório:**
   ```sh
   git clone https://github.com/AnaFlaviaMocelin/banco-api.git
   cd banco-api
   ```

2. **Compile o projeto:**
   ```sh
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```sh
   mvn spring-boot:run
   ```

A API será executada em `http://localhost:8080`.

📌 Swagger UI
   ```sh
  http://localhost:8080/swagger-ui.html
   ```

## 🔥 Endpoints Disponíveis
### 📌 Criar Conta
**POST** `/api/contas`
```json
{
  "numeroConta": "123456",
  "saldoInicial": 1000
}
```
**Resposta:**
```json
{
  "id": 1,
  "numeroConta": "123456",
  "saldo": 1000
}
```

### 📌 Consultar Saldo
**GET** `/api/contas/{Id}/saldo`
**Resposta:**
```json
"saldo": 1000
```

### 📌 Realizar Lançamento (Débito ou Crédito)
**POST** `/api/contas/lancamentos`
```json
[
   {
      "contaId": 1,
      "tipo": "DEBITO",
      "valor": 100
   },
   {
      "contaId": 1,
      "tipo": "CREDITO",
      "valor": 200
   }
]
```
**Resposta:**
```json
{
  "id": 1,
  "numeroConta": "123456",
  "saldo": 900
}
```

## 🧪 Testes Automatizados
Para rodar os testes:
```sh
mvn test
```
