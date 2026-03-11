Sistema de Controle de Assinaturas

📖 Sobre o Projeto

Sistema backend para gerenciamento de assinaturas, permitindo:

- Cadastro de usuários
- Criação de planos
- Controle do ciclo de vida da assinatura
- Renovação, cancelamento e reativação
- Histórico de assinaturas
- Estrutura preparada para integração com pagamentos
  
O sistema simula o funcionamento de plataformas de assinatura como Netflix e Spotify, focando principalmente na regra de negócio e no ciclo de cobrança.

------------------------------------------------------------------------------------------------------------------------------------------------------------

🏗️ Arquitetura

O projeto segue arquitetura em camadas:

- ConfigScheduler (Cobrança automática)
- Controller
- DTOs
- Entity
- Exceptions
- Mappers
- Repository
- Service

Separação clara de responsabilidades e regras de negócio concentradas na camada de Service.

------------------------------------------------------------------------------------------------------------------------------------------------------------

🧩 Entidades Principais
👤 Usuario

- id (Long)
- nome (String)
- email (String)
- data de Cadastro (LocalDate)
- lista de assinaturas (Assinatura)

Relacionamento:

Usuario 1 —— N Assinaturas

------------------------------------------------------------------------------------------------------------------------------------------------------------

📦 Plano

- id (Long)
- tipo do plano (Enum { mensal, anual, premium })
- valor (BigDecimal)
- ativo (boolean)
- data de criacao (LocalDate)
- Lista de assinatura (Assinatura)

Relacionamento:

Plano 1 —— N Assinaturas

------------------------------------------------------------------------------------------------------------------------------------------------------------

🔁 Assinatura

- id (Long)
- dataInicio (LocalDate)
- dataExpiracao (LOcalDate)
- proximaCobranca (LocalDate)
- dataCancelamento (LocalDate)
- statusAssinatura (StatusAssinatura)
- usuario (Usuario)
- plano (Plano)
- pagamentos (Pagamento)

Relacionamentos:

Assinatura N —— 1 Usuario
Assinatura N —— 1 Plano
Assinatura 1 —— N Pagamentos

------------------------------------------------------------------------------------------------------------------------------------------------------------

============================    SISTEMA DE PAGAMENTO SIMPLES EM DESENVOLVIMENTO    ============================

💳 Pagamento (estrutura preparada)

- id (Long)
- assinatura (Assinatura)
- valor (BigDecimal)
- dataPagamento (LocalDate)
- metodoPagamento (Enum {CARTAO, PIX, BOLETO})
- retornoPagamento (Enum {APROVADO, SUSPENSA, PENDENTE, BLOQUEADO, RECUSADO})

------------------------------------------------------------------------------------------------------------------------------------------------------------

📊 Status da Assinatura

Enum StatusAssinatura:

- ATIVA
- CANCELADA
- EXPIRADA
- SUSPENSA

------------------------------------------------------------------------------------------------------------------------------------------------------------

💰 Status do Pagamento

Enum StatusPagamento:

- APROVADO
- SUSPENSA
- PENDENTE
- BLOQUEADO
- RECUSADO

------------------------------------------------------------------------------------------------------------------------------------------------------------

🔄 Regras de Negócio Para serem Implementadas

✔ Criar Assinatura

- Usuário só pode ter UMA assinatura ativa
- Plano deve estar ativo
- Cálculo automático de expiração
- Definição automática de próxima cobrança

✔ Trocar Plano

- Cancela assinatura atual
- Cria nova assinatura
- Mantém histórico
- Usuário não perde dias restantes (extensão acumulativa)

✔ Renovar Assinatura

- Extensão baseada na data de expiração atual
- Mantém consistência do ciclo

✔ Cancelar Assinatura

- Atualiza status
- Registra data de cancelamento

✔ Reativar Assinatura

- Só possível se estiver CANCELADA
- Novo ciclo baseado na regra de extensão

✔ Expiração Automática

- Assinaturas vencidas mudam para EXPIRADA

------------------------------------------------------------------------------------------------------------------------------------------------------------

🧠 Principais Conceitos Aplicados

- Relacionamentos @OneToMany e @ManyToOne
- Regras de negócio na camada Service
- Uso de BigDecimal para valores monetários
- Enums para controle de estado
- Exceções personalizadas
- Controle de histórico de assinaturas
- Validações de integridade de domínio

------------------------------------------------------------------------------------------------------------------------------------------------------------

🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Dbeaver
- Postaman
- Flyway (migrações)
- Maven

------------------------------------------------------------------------------------------------------------------------------------------------------------

👤 Usuários
Método	          Endpoint	                  Descrição

GET	              /usuarios	                  Listar todos os usuários
GET	              /usuarios/{id}	            Buscar usuário por ID

POST	            /usuarios	                  Criar novo usuário

PUT	              /usuarios/{id}	            Atualizar usuário

DELETE	          /usuarios/{id}	            Deletar usuário

Exemplo de criação:

{
  "nome": "João",
  "email": "joao@email.com"
}

------------------------------------------------------------------------------------------------------------------------------------------------------------

📦 Planos
Método	                  Endpoint	                          Descrição

GET	                      /planos                             Listar todos os planos
GET	                      /planos/{id}	                      Buscar plano por ID

POST	                    /planos	                            Criar plano

PUT	                      /planos/{id}	                      Atualizar plano

PATCH	                    /planos/{id}/activate	              Ativar plano
PATCH                     /planos/{id}/deactivate             Desativar plano

Exemplo:

{
  "nome": "Plano Premium",
  "preco": 49.90
}

------------------------------------------------------------------------------------------------------------------------------------------------------------

📑 Assinaturas
Método	                                 Endpoint	                                                      Descrição

GET	                                     /assinaturas                                                   Listar todas as assinaturas
GET	                                     /assinaturas/{assinaturaId}                                    Buscar assinatura por ID
GET	                                     /assinaturas/usuario/{usuarioId}                               Buscar assinaturas de um usuário
GET	                                     /assinaturas/usuario/{usuarioId}/active	                      Buscar assinatura ativa do usuário

POST	                                   /assinaturas/usuario/{usuarioId}/plano/{planoId}	              Criar assinatura
POST                                     /assinaturas/{assinaturaId}/change-plan/{novoPlanoId}          Trocar plano da assinatura

PATCH                                    /assinaturas/{assinaturaId}/renew                              Renovar assinatura
PATCH                                    /assinaturas/{assinaturaId}/cancel                             Cancelar assinatura
PATCH                                    /assinaturas/{id}/reactivate                                   Reativar assinatura


------------------------------------------------------------------------------------------------------------------------------------------------------------


💳 Pagamentos
Método	              Endpoint	                                                      Descrição

GET	                  /pagamento/id/{id}	                                            Buscar pagamento por ID
GET	                  /pagamento/assinaturas/{assinaturaId}	                          Listar pagamentos de uma assinatura

POST	                /pagamento/assinaturas/{assinaturaId}/pagamentos	              Realizar pagamento

Parâmetro obrigatório: metodoPagamento

Exemplo de requisição:

POST /pagamento/assinaturas/1/pagamentos?metodoPagamento=CARTAO

Body:

{
  "valor": 49.90
}

------------------------------------------------------------------------------------------------------------------------------------------------------------


🎯 Objetivo do Projeto

- Demonstrar domínio em:
- Modelagem de entidades relacionais
- Regras de negócio complexas
- Ciclo de vida de assinaturas
- Organização de arquitetura backend
- Evolução incremental de sistema
