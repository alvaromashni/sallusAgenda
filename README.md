<div align="center">
  <h1>SallusAgenda</h1>
  <p><strong>Sistema de Agendamento de Consultas Médicas</strong></p>
  <p>
    <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/>
    <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
    <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
    <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
    <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT"/>
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger"/>
  </p>
</div>

---

## 📋 Sobre o Projeto

O **SallusAgenda** é uma API REST para gerenciamento completo de agendamentos de consultas médicas. A plataforma conecta pacientes e profissionais de saúde, oferecendo um sistema robusto com controle de acesso baseado em papéis, autenticação JWT, gerenciamento de horários e links de consulta compartilháveis.

O sistema foi projetado para suportar três perfis de usuário distintos — **Paciente**, **Profissional de Saúde** e **Administrador** — cada um com suas responsabilidades e permissões específicas.

---

## ✨ Funcionalidades

### 👤 Gestão de Usuários
- Cadastro e autenticação de **pacientes** e **profissionais de saúde**
- Painel **administrativo** para gerenciamento de todos os usuários
- Soft delete com registro de data/hora de exclusão
- Atualização de dados pessoais

### 🗓️ Agendamentos
- Criação de agendamentos de consultas com data e horário
- Detecção automática de **conflitos de agenda**
- Filtro por data para verificação de disponibilidade
- Categorias de consulta configuráveis (ex: Cardiologia, Odontologia)

### 🔗 Links de Consulta
- Geração de **links compartilháveis** com prazo de expiração
- Validação automática de expiração
- Habilitação/desabilitação de links pelos profissionais

### 🔒 Segurança
- Autenticação via **JWT** (expiração de 30 minutos)
- Senhas protegidas com **BCrypt**
- Controle de acesso baseado em papéis (RBAC): `ADMIN`, `USER`
- Configuração de **CORS** para integração com o frontend

### 📖 Documentação
- Documentação interativa via **Swagger UI** em `/swagger-ui.html`
- Especificação OpenAPI em `/v3/api-docs`

---

## 🛠️ Tecnologias

| Categoria | Tecnologia |
|-----------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.x |
| Segurança | Spring Security + JWT (jjwt 0.11.5) |
| Banco de Dados | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Mapeamento | ModelMapper 3.2.4 |
| Documentação | SpringDoc OpenAPI (Swagger) 2.8.13 |
| Validação | Spring Boot Validation |
| Containerização | Docker + Docker Compose |
| Build | Maven |

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas padrão do Spring Boot:

```
src/main/java/com/salus/agenda/
├── configuration/          # Configurações (CORS, Security, ModelMapper, Admin)
│   └── security/           # Filtros JWT e configuração do Spring Security
├── controllers/            # Endpoints REST
│   └── auth/               # Controllers de autenticação
├── services/               # Lógica de negócio
│   └── auth/               # Serviços de autenticação
├── repositories/           # Acesso ao banco de dados (Spring Data JPA)
├── models/                 # Entidades JPA
├── dtos/                   # Data Transfer Objects
│   ├── request/            # DTOs de entrada
│   └── response/           # DTOs de saída
└── exceptions/             # Tratamento global de exceções
```

---

## 🚀 Como Executar

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven 3.8+](https://maven.apache.org/) *(ou use o wrapper incluso)*
- [Docker](https://www.docker.com/) *(opcional)*

### Configuração do Ambiente

Crie um arquivo `.env` dentro da pasta `agenda/` com as seguintes variáveis:

```env
DB_URL=jdbc:postgresql://localhost:5432/sallusagenda
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

### Opção 1 — Maven Wrapper

```bash
cd agenda

# Build
./mvnw clean package

# Executar
./mvnw spring-boot:run
```

> No Windows, use `mvnw.cmd` no lugar de `./mvnw`.

### Opção 2 — Docker Compose

```bash
cd agenda
docker-compose up --build
```

A aplicação ficará disponível em: **http://localhost:8080**

---

## 📡 Endpoints da API

### Autenticação

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/auth/patient/login` | Login de paciente | Público |
| `POST` | `/auth/professional/login` | Login de profissional | Público |
| `POST` | `/auth/admin/login` | Login de administrador | Público |

### Pacientes

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/patient/register` | Cadastrar novo paciente | Público |
| `PUT` | `/patient/update/{id}` | Atualizar dados do paciente | Autenticado |
| `DELETE` | `/patient/soft-delete/{id}` | Desativar conta do paciente | Autenticado |
| `GET` | `/patient/findPatientData/{id}` | Buscar dados do paciente | Autenticado |

### Profissionais

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/professional/register` | Cadastrar profissional | Público |
| `PUT` | `/professional/update/{id}` | Atualizar dados do profissional | `USER` |
| `DELETE` | `/professional/soft-delete/{id}` | Desativar conta do profissional | `USER` |
| `GET` | `/professional/findAll` | Listar todos os profissionais | Público |
| `GET` | `/professional/findProfessionalData/{id}` | Buscar dados do profissional | Público |
| `PATCH` | `/professional/updateHours/{id}` | Atualizar horários de atendimento | `USER` |
| `DELETE` | `/professional/deleteHours/{id}` | Remover horário de atendimento | `USER` |
| `GET` | `/professional/findAllHours/{id}` | Listar horários de atendimento | Público |

### Agendamentos

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/schedule/register` | Criar agendamento | Público |
| `DELETE` | `/schedule/delete/{id}` | Excluir agendamento | Autenticado |
| `PUT` | `/schedule/delete/softDelete/{id}` | Cancelar agendamento (soft delete) | Autenticado |
| `GET` | `/schedule/isScheduled/{id}?day=DATE` | Verificar disponibilidade do profissional | Público |
| `GET` | `/schedule/findSchedule/{id}?date=DATE` | Listar agendamentos por data | Autenticado |

### Links de Consulta

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/consultationLink/generate` | Gerar link de consulta | `USER` |
| `GET` | `/consultationLink/validate/{id}` | Validar expiração do link | Público |
| `PATCH` | `/consultationLink/disable/{id}` | Desabilitar link | `USER` |

### Categorias de Consulta

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `POST` | `/category/registerCategory` | Criar categoria | `ADMIN` |
| `DELETE` | `/category/deleteCategory/{id}` | Excluir categoria | `ADMIN` |
| `GET` | `/category/findAll` | Listar todas as categorias | Público |

### Administração

| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| `GET` | `/admin/listAllProfessional` | Listar todos os profissionais | `ADMIN` |
| `GET` | `/admin/listAllPatients` | Listar todos os pacientes | `ADMIN` |
| `DELETE` | `/admin/deletePatient/{id}` | Excluir paciente | `ADMIN` |
| `DELETE` | `/admin/deleteProfessional/{id}` | Excluir profissional | `ADMIN` |

---

## 🗄️ Modelo de Dados

```
Patient ──────────────── Schedule ──────── ProfessionalUser
  │                          │                     │
  │                   ConsultationCategory    ConsultationLink
  │
PersonalData (embedded)                    Hours (embedded)
  ├── name
  ├── cpf
  ├── email
  ├── birthDate
  ├── gender
  ├── phoneNumber
  └── password (BCrypt)
```

### Principais Entidades

| Entidade | Descrição |
|----------|-----------|
| `Patient` | Paciente que realiza o agendamento |
| `ProfessionalUser` | Profissional de saúde com CRM, especialidade e horários |
| `Schedule` | Consulta agendada (data, hora, categoria, paciente, profissional) |
| `ConsultationCategory` | Tipos de consulta (ex: Cardiologia, Odontologia) |
| `ConsultationLink` | Link compartilhável com expiração para agendamento |
| `Admin` | Administrador do sistema |

---

## 🔐 Segurança

O sistema utiliza **JWT (JSON Web Token)** para autenticação stateless:

1. O cliente realiza login e recebe um token JWT
2. O token deve ser enviado no header `Authorization: Bearer <token>` em todas as requisições protegidas
3. Os tokens expiram em **30 minutos**
4. As senhas são armazenadas com hash **BCrypt**

### Papéis de Acesso (RBAC)

| Papel | Descrição |
|-------|-----------|
| `ADMIN` | Acesso total ao sistema, gerenciamento de usuários |
| `USER` | Profissional de saúde autenticado |
| Público | Rotas abertas para registro, login e consulta de dados públicos |

---

## 📖 Documentação Interativa

Com a aplicação em execução, acesse:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI Spec:** http://localhost:8080/v3/api-docs

---

## 🌐 Frontend

O backend está integrado com o frontend hospedado na Vercel:

| Ambiente | URL |
|----------|-----|
| Produção | https://salusagenda.vercel.app |
| Alternativo / Staging | https://salusagenda22.vercel.app |

---

## 📁 Estrutura do Projeto

```
sallusAgenda/
└── agenda/                     # Aplicação Spring Boot
    ├── src/
    │   ├── main/
    │   │   ├── java/           # Código-fonte Java
    │   │   └── resources/
    │   │       └── application.properties
    │   └── test/               # Testes unitários
    ├── Dockerfile
    ├── docker-compose.yml
    └── pom.xml
```

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b feature/minha-feature`
3. Commit suas alterações: `git commit -m 'feat: adiciona minha feature'`
4. Push para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

---

<div align="center">
  <p>Desenvolvido com ❤️ para modernizar o agendamento de consultas médicas</p>
</div>
