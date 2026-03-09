# API de Gerenciamento de Treinos

API REST para registro, acompanhamento e análise de evolução de treinos. Desenvolvida com foco em regras de negócio, boas práticas de engenharia de software e arquitetura limpa.

---

## Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- JPA / Hibernate
- PostgreSQL
- Maven

---

## 📋 Requisitos Funcionais

### Usuário
- Cadastrar conta com email e senha
- Autenticar via JWT
- Informar dados pessoais:
  - Nome (obrigatório)
  - Email (obrigatório)
  - Peso em kg (opcional)
  - Altura em cm (opcional)
  - Idade (opcional)
  - Gênero (opcional)
- Atualizar dados pessoais
- Atualizar email e senha
- Deletar conta -- ao deletar, todos os dados do usuário devem ser removidos junto (treinos, execuções, séries)
- Consultar histórico de peso e IMC ao longo do tempo

### Exercícios
- Cadastrar exercícios com nome e grupos musculares
- Um exercício pode pertencer a mais de um grupo muscular
- Grupos musculares disponíveis: peito, costas, perna, ombro, braço, core, glúteo

### Treinos
- Escolher treinos predefinidos pelo sistema como ponto de partida
- Criar treinos personalizados do zero
- Editar e deletar treinos próprios
- Treinos predefinidos do sistema não podem ser editados ou deletados
- Adicionar e remover exercícios de um treino
- Definir a ordem dos exercícios no treino

### Execução de Treino
- Registrar a execução de um treino com:
  - Data
  - Duração em minutos
  - Observações (opcional)
- Para cada exercício executado, registrar séries individualmente:
  - Ordem da série (1ª, 2ª, 3ª...)
  - Peso utilizado em kg
  - Número de repetições

### Histórico e Evolução
- Consultar histórico de execuções por exercício
- Comparar evolução de carga e repetições ao longo do tempo
- Consultar frequência semanal de treinos
- Endpoints de relatório para alimentar gráficos de linha, barra e gauge no frontend

---

## Regras de Negócio

- IMC é calculado automaticamente pelo sistema quando peso e altura estiverem preenchidos — o usuário não informa o IMC diretamente
- Usuário só acessa os próprios dados — isolamento por autenticação JWT
- Treinos predefinidos são do sistema e não podem ser modificados ou removidos
- Peso e repetições não podem ser negativos ou zero
- Data de execução não pode ser no futuro
- Email deve ser único no sistema

---

## 🗄️ Modelagem do Banco de Dados

```
usuario
  id, nome, email, senha, peso, altura, idade, genero, data_cadastro

grupo_muscular
  id, nome

exercicio
  id, nome

exercicio_grupo_muscular
  exercicio_id, grupo_muscular_id

treino_template
  id, nome, descricao

treino
  id, nome, usuario_id, template_id (null se criado do zero), data_criacao

treino_exercicio
  id, treino_id, exercicio_id, ordem

execucao_treino
  id, treino_id, usuario_id, data, duracao_minutos, observacao

serie
  id, execucao_treino_id, exercicio_id, ordem, peso_kg, repeticoes
```

---

## 🔐 Autenticação

- Endpoints públicos: `POST /auth/cadastro` e `POST /auth/login`
- Todos os demais endpoints exigem token JWT no header:
  ```
  Authorization: Bearer {token}
  ```

---

## Endpoints Planejados

### Auth
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /auth/cadastro | Cadastrar novo usuário |
| POST | /auth/login | Autenticar e receber token JWT |

### Usuário
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /usuario/perfil | Consultar perfil |
| PUT | /usuario/perfil | Atualizar dados pessoais |
| GET | /usuario/historico/imc | Histórico de peso e IMC |

### Exercícios
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /exercicios | Listar exercícios |
| POST | /exercicios | Cadastrar exercício |
| GET | /exercicios/{id} | Buscar por ID |

### Treinos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /treinos/templates | Listar treinos predefinidos |
| GET | /treinos | Listar treinos do usuário |
| POST | /treinos | Criar treino |
| PUT | /treinos/{id} | Editar treino |
| DELETE | /treinos/{id} | Deletar treino |

### Execução
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /execucoes | Registrar execução de treino |
| GET | /execucoes | Listar execuções do usuário |
| GET | /execucoes/{id} | Detalhar execução |

### Relatórios
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /relatorios/exercicio/{id}/evolucao | Evolução de carga por exercício |
| GET | /relatorios/frequencia | Frequência semanal de treinos |

---

## Como executar

> Em desenvolvimento

---

## 📌 Status do Projeto

🔨 Em desenvolvimento
