# 🚀 LogisticApp API – Backend (Spring Boot)

Este repositório contém o backend do **LogisticApp**, um sistema de logística construído com **Spring Boot**, responsável por fornecer uma API RESTful para gerenciamento de usuários, veículos, ordens de entrega e coletas.

---

## 🧠 1. Visão Geral

O backend do LogisticApp é o núcleo da aplicação, lidando com:

- Lógica de negócio
- Persistência de dados
- Autenticação
- Exposição de endpoints RESTful para o frontend (mobile ou web)

---

## 🛠️ 2. Tecnologias Utilizadas

- **Java 17** – Linguagem principal
- **Spring Boot 3** – Framework para construção da API
- **Spring Data JPA** – Acesso e persistência de dados
- **Hibernate** – Implementação JPA
- **H2 Database** – Banco de dados leve (modo arquivo)
- **Lombok** – Redução de boilerplate
- **Maven** – Gerenciador de dependências e builds

---

## 📁 3. Estrutura do Projeto

```plaintext
src/main/java/com/example/logisticapi/
├── config/
│   └── WebConfig.java
├── controllers/
│   ├── ColetaController.java
│   ├── LoginController.java
│   ├── OrdemController.java
│   ├── UsuarioController.java
│   └── VeiculoController.java
├── dtos/
│   ├── LoginRequest.java
│   └── LoginResponse.java
├── models/
│   ├── Coleta.java
│   ├── Ordem.java
│   ├── Usuario.java
│   └── Veiculo.java
├── repositories/
│   ├── ColetaRepository.java
│   ├── OrdemRepository.java
│   ├── UsuarioRepository.java
│   └── VeiculoRepository.java
├── services/
│   ├── ColetaService.java
│   ├── LoginService.java
│   ├── OrdemService.java
│   ├── UsuarioService.java
│   └── VeiculoService.java
└── LogisticapiApplication.java
🗄️ 4. Banco de Dados (H2)
O banco de dados é persistido em arquivo e configurado no application.properties:

properties
Copiar
Editar
spring.datasource.url=jdbc:h2:file:./data/logisticdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
Acesso ao console H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/logisticdb

Usuário: sa

Senha: (deixe em branco)

🌐 5. Endpoints da API RESTful
Todos os endpoints têm como prefixo: /api

5.1. 🔐 Autenticação – /api/login
POST /api/login

json

// LoginRequest
{
  "email": "usuario@example.com",
  "senha": "123"
}
Respostas:
200 OK

json

{
  "message": "Login bem-sucedido!",
  "token": "fake-jwt-token-123",
  "userId": 1,
  "nome": "Nome do Usuário"
}
401 Unauthorized

json

{
  "message": "Email ou senha inválidos."
}
5.2. 👤 Usuários – /api/usuarios
POST – Criação

json

{
  "nome": "Maria",
  "email": "maria@example.com",
  "senha": "123",
  "tipoUsuario": "ADMIN" // ou MOTORISTA, OPERADOR
}
GET – Listar todos

GET /api/usuarios/{id} – Buscar por ID

PUT /api/usuarios/{id} – Atualizar

DELETE /api/usuarios/{id} – Excluir

5.3. 🚗 Veículos – /api/veiculos
POST

json

{
  "placa": "ABC1234",
  "modelo": "Sprinter",
  "anoFabricacao": 2023,
  "capacidadeCargaKg": 1000
}
GET – Listar todos

GET /api/veiculos/{id} – Buscar por ID

PUT /api/veiculos/{id} – Atualizar

DELETE /api/veiculos/{id} – Excluir

5.4. 📦 Ordens de Entrega – /api/ordens
POST

json


{
  "descricao": "Entrega de documentos",
  "enderecoOrigem": "Rua A",
  "enderecoDestino": "Rua B",
  "status": "PENDENTE",
  "veiculo": { "id": 1 },
  "motorista": { "id": 1 }
}
GET – Listar todas

GET /api/ordens/{id} – Buscar por ID

PUT /api/ordens/{id} – Atualizar (status ou info)

DELETE /api/ordens/{id} – Excluir

5.5. 📍 Coletas – /api/coletas
POST

json

{
  "descricao": "Coleta de amostras",
  "enderecoColeta": "Rua Coleta",
  "dataColetaAgendada": "2025-06-17T09:00:00",
  "status": "AGENDADA",
  "ordem": { "id": 1 }
}
GET – Listar todas

GET /api/coletas/{id} – Buscar por ID

PUT /api/coletas/{id} – Atualizar

DELETE /api/coletas/{id} – Excluir

🔄 6. Configuração CORS – WebConfig.java
Classe WebConfig.java define as regras de Cross-Origin:

java

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:19000", "http://10.0.2.2:8081") // inclua IPs locais
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
}
⚠️ Importante: Para rodar em outras máquinas ou celulares, adicione o IP da máquina no allowedOrigins.

⚠️ 7. Tratamento de Erros
Uso de status HTTP apropriados:
200 OK, 201 Created, 204 No Content, 400 Bad Request, 401 Unauthorized, 404 Not Found, 409 Conflict.

Uso de Optional nos serviços para evitar NullPointerException.

Validações de unicidade (e-mail e placa) → retornam 409 Conflict.

Respostas com mensagens claras para login inválido ou entidades inexistentes.

💾 8. Persistência e Relações
Mapeamento via JPA (Hibernate)

Relacionamentos:

Ordem possui @ManyToOne com Motorista e Veiculo

Coleta possui @ManyToOne com Ordem

Uso de @PrePersist para preencher automaticamente:

java

@PrePersist
protected void onCreate() {
    this.dataCadastro = LocalDateTime.now();
}

## Nome: Rodrigo Vinzent Arinez Viscarra RM: 559192
## Nome: Erick Santos Santana RM: 559206
## Nome: Gabriel Borges Gonçalvez Silva RM: 558861
## Nome: Tarik Omar Mazloum RM:554933
