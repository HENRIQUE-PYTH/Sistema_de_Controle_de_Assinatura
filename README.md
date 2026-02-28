Sistema de Controle de Assinaturas

📖 Sobre o Projeto

Sistema backend para gerenciamento de assinaturas, permitindo:

- Cadastro de usuários
- Criação de planos
- Controle do ciclo de vida da assinatura
- Renovação, cancelamento e reativação
- Histórico de assinaturas
- Estrutura preparada para integração com pagamentos
- 
O sistema simula o funcionamento de plataformas de assinatura como Netflix e Spotify, focando principalmente na regra de negócio e no ciclo de cobrança.

(NO MOMENTO TENHO AS ENTIDADES DE PLANO, USUARIO E ASSINATURA ESTÃO FEITAS, O MESMO CABE A SERVICE DAS 3 JÁ FEITAS, FALTA AS CONTROLLERS MAS AINDA NÃO VOU FAZER, POIS VOU CRIAR UMA NOVA ENTIDADE, SERVICE E CONTROLLER QUE SERIA DO PAGAMENTO. PELO FATO DO PROJETO NÃO TA 100%, ESSE É O MOTIVO DO POR QUE AINDA NÃO SUBI AQUI NO GITHUB, QUANDO TIVER TUDO FEITO, ATUALIZO O README DESSE REPOSITORIO E JA LANÇO TUDO AQUI)

------------------------------------------------------------------------------------------------------------------------------------------------------------

🏗️ Arquitetura

O projeto segue arquitetura em camadas:

- Controller
- Service
- Repository
- Entity
- DTOs
- Exception Handler

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
- statusPagamento (Pagamento)

------------------------------------------------------------------------------------------------------------------------------------------------------------

📊 Status da Assinatura

Enum StatusAssinatura:

- ATIVA
- CANCELADA
- EXPIRADA
- SUSPENSA (planejado)

------------------------------------------------------------------------------------------------------------------------------------------------------------

💰 Status do Pagamento

Enum StatusPagamento:

- APROVADO
- RECUSADO
- PENDENTE (planejado)

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

📌 Próximas Evoluções Planejadas

- Implementação completa da entidade Pagamento
- Simulação de cobrança automática
- Bloqueio por inadimplência
- Implementação de Status SUSPENSA
- Melhoria de consultas específicas
- Implementação de testes unitários

------------------------------------------------------------------------------------------------------------------------------------------------------------

🎯 Objetivo do Projeto

- Demonstrar domínio em:
- Modelagem de entidades relacionais
- Regras de negócio complexas
- Ciclo de vida de assinaturas
- Organização de arquitetura backend
- Evolução incremental de sistema
