# TechQuiz

Aplicativo Android que realiza um quiz interativo para descobrir o perfil tech do usuário, com histórico de resultados persistido em banco de dados.

## Descrição da Proposta

O TechQuiz coleta informações do usuário através de 5 perguntas sobre preferências e interesses na área de tecnologia, processa as respostas e gera um perfil personalizado entre quatro opções: **Backend Developer**, **Frontend Developer**, **DevOps Engineer** ou **Data Analyst**. Os resultados são armazenados via API REST e podem ser consultados no histórico.

## Tecnologias Utilizadas

### Backend
- Python 3.13
- FastAPI
- SQLAlchemy (ORM)
- SQLite
- Uvicorn
- Pydantic

### Android
- Kotlin
- Retrofit2
- Coroutines
- RecyclerView
- Fragment

## Estrutura do Projeto

```
TechQuiz/
├── backend/
│   ├── main.py
│   ├── database.py
│   ├── models.py
│   ├── schemas.py
│   ├── requirements.txt
│   └── routers/
│       ├── usuarios.py
│       └── resultados.py
└── android/
    └── app/src/main/java/com/example/ap2_devmobile/
        ├── model/
        ├── network/
        └── ui/
```

## Instruções de Execução

### Backend

```bash
# 1. Entre na pasta do backend
cd backend

# 2. Crie e ative o ambiente virtual
python -m venv .venv
.venv\Scripts\Activate.ps1

# 3. Instale as dependências
pip install -r requirements.txt

# 4. Rode o servidor
uvicorn main:app --reload --host 0.0.0.0
```

### Android

1. Abra o projeto no Android Studio
2. Certifique-se que o backend está rodando
3. Execute o app no emulador ou dispositivo físico

> O emulador acessa o backend via `http://10.0.2.2:8000/`

## Descrição da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /usuarios/ | Cadastra novo usuário |
| GET | /usuarios/{id} | Busca usuário por ID |
| PUT | /usuarios/{id} | Atualiza dados do usuário |
| DELETE | /usuarios/{id} | Remove usuário |
| POST | /resultados/ | Salva resultado do quiz |
| GET | /resultados/{usuario_id} | Busca histórico do usuário |
| DELETE | /resultados/{id} | Remove resultado |

## Swagger

```
http://127.0.0.1:8000/docs
```

## Screenshots

### Tela de Cadastro
![Cadastro](screenshots/Screenshot%202026-06-14%20183855.png)

### Quiz — Pergunta 1
![Quiz 1](screenshots/Screenshot%202026-06-14%20183923.png)

### Quiz — Pergunta 2
![Quiz 2](screenshots/Screenshot%202026-06-14%20183931.png)

### Quiz — Pergunta 3
![Quiz 3](screenshots/Screenshot%202026-06-14%20183938.png)

### Quiz — Pergunta 4
![Quiz 4](screenshots/Screenshot%202026-06-14%20183945.png)

### Quiz — Pergunta 5
![Quiz 5](screenshots/Screenshot%202026-06-14%20183953.png)

### Resultado
![Resultado](screenshots/Screenshot%202026-06-14%20184002.png)

### Histórico
![Histórico](screenshots/Screenshot%202026-06-14%20184014.png)

### Meu Perfil
![Perfil](screenshots/Screenshot%202026-06-14%20184023.png)

### Compartilhar Resultado
![Compartilhar](screenshots/Screenshot%202026-06-14%20184035.png)
