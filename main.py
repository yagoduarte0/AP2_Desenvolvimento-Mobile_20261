from fastapi import FastAPI
from database import engine, Base
from routers import usuarios, resultados

Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="TechQuiz API",
    description="API para o app de perfil tech",
    version="1.0.0"
)

app.include_router(usuarios.router)
app.include_router(resultados.router)

@app.get("/")
def root():
    return {"message": "TechQuiz API funcionando!"}