# 🏦 Sistema Bancário API

API desenvolvida em **Spring Boot** para gerenciamento de contas bancárias, incluindo funcionalidades como cadastro de contas, saques, depósitos, transferências via PIX e encerramento de contas.

---
## 📌 **Tecnologias Utilizadas**
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database (Banco de Dados em Memória)**
- **Lombok**
- **Maven**
- **Insomnia/Postman (para testes de API)**

---
## 🚀 **Configuração do Projeto**

### 🔹 **1️⃣ Requisitos**
Antes de iniciar o projeto, certifique-se de ter instalado:
- **Java JDK 17**
- **Maven**
- **Git**
- **Visual Studio Code** ou **IntelliJ IDEA**

### 🔹 **2️⃣ Clonar o Repositório**
```sh
git clone https://github.com/seu-usuario/bank-api.git
cd bank-api
```

### 🔹 **3️⃣ Configurar as Variáveis de Ambiente**
Se precisar configurar o banco de dados, edite o arquivo `application.properties`.

### 🔹 **4️⃣ Rodar a Aplicação**
```sh
./mvnw spring-boot:run
```
Ou no Windows:
```sh
mvnw.cmd spring-boot:run
```
A API ficará disponível em: `http://localhost:8080`

---
### Criar Conta 1 (Corrente)
- **Método:** POST
- **URL:** `http://localhost:8080/contas`
- **Body:**
  ```json
  {
    "numero": "10001",
    "agencia": "001",
    "nomeTitular": "João da Silva",
    "cpfTitular": "12345678901",
    "dataAbertura": "18-03-2025",
    "saldo": 1000.00,
    "ativa": true,
    "tipo": "CORRENTE"
  }
  ```
---
### Criar Conta 2 (Poupança)
- **Método:** POST
- **URL:** `http://localhost:8080/contas`
- **Body:**
  ```json
  {
    "numero": "20002",
    "agencia": "002",
    "nomeTitular": "Maria Oliveira",
    "cpfTitular": "98765432109",
    "dataAbertura": "18-03-2025",
    "saldo": 1500.00,
    "ativa": true,
    "tipo": "POUPANCA"
  }
  ```

### Criar Conta 3 (Salário)
- **Método:** POST
- **URL:** `http://localhost:8080/contas`
- **Body:**
  ```json
  {
    "numero": "30003",
    "agencia": "003",
    "nomeTitular": "Carlos Souza",
    "cpfTitular": "11223344556",
    "dataAbertura": "18-03-2025",
    "saldo": 2000.00,
    "ativa": true,
    "tipo": "SALARIO"
  }

```

### 🔹 **Listar Todas as Contas**
`GET /contas`
#### **Response**
```json
[
    {
        "id": 1,
        "numero": "10001",
        "agencia": "001",
        "nomeTitular": "João da Silva",
        "saldo": 1000.00,
        "ativa": true,
        "tipo": "CORRENTE"
    }
]

```

### 🔹 **Buscar Conta por ID**
`GET /contas/{id}`
#### **Exemplo:**
```sh
GET /contas/1
```
#### **Response:**
```json
{
    "id": 1,
    "nomeTitular": "João da Silva",
    "saldo": 1000.00,
    "ativa": true,
    "tipo": "CORRENTE"
}
```

### 🔹 **Realizar Depósito**
`PUT /contas/deposito`
#### **Request Body (JSON)**
```json
{
    "id": 1,
    "valor": 500.00
}
```
#### **Response:**
```json
{
    "id": 1,
    "saldo": 1500.00
}
```

### 🔹 **Realizar Saque**
`PUT /contas/saque`
#### **Request Body (JSON)**
```json
{
    "id": 1,
    "valor": 200.00
}
```
#### **Response:**
```json
{
    "id": 1,
    "saldo": 1300.00
}
```

### 🔹 **Realizar PIX**
`PUT /contas/pix`
#### **Request Body (JSON)**
```json
{
    "origemId": 1,
    "destinoId": 2,
    "valor": 100.00
}
```
#### **Response:**
```json
{
    "mensagem": "PIX realizado com sucesso!",
    "origemSaldo": 1200.00,
    "destinoSaldo": 1100.00
}
```

### 🔹 **Encerrar Conta**
`PUT /contas/encerrar`
#### **Request Body (JSON)**
```json
{
    "id": 1
}
```
#### **Response:**
```json
{
    "id": 1,
    "ativa": false
}
```

### 🔹 **Deletar Conta**
`DELETE /contas/{id}`
#### **Exemplo:**
```sh
DELETE /contas/1
```
#### **Response:**
```json
{
    "mensagem": "Conta deletada com sucesso!"
}
```

---
## 📌 **Como Contribuir?**
1. **Faça um Fork** deste repositório.
2. Crie um **Branch** para sua feature:
   ```sh
   git checkout -b minha-feature
   ```
3. Faça **commit** das suas alterações:
   ```sh
   git commit -m "Adicionando nova funcionalidade X"
   ```
4. Faça um **Push** para o seu branch:
   ```sh
   git push origin minha-feature
   ```
5. **Abra um Pull Request** 🚀

---
## 📌 **Licença**
Este projeto está sob a **Licença MIT**. Sinta-se livre para usá-lo e melhorá-lo!

Desenvolvido por **Rafael e Lucas** ✨

