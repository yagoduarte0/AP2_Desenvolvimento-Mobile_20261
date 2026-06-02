from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from database import get_db
from models import Resultado
from schemas import ResultadoCreate, ResultadoResponse
from typing import List

router = APIRouter(prefix="/resultados", tags=["Resultados"])

@router.post("/", response_model=ResultadoResponse)
def salvar_resultado(resultado: ResultadoCreate, db: Session = Depends(get_db)):
    novo_resultado = Resultado(
        usuario_id=resultado.usuario_id,
        perfil=resultado.perfil,
        pontuacao=resultado.pontuacao
    )
    db.add(novo_resultado)
    db.commit()
    db.refresh(novo_resultado)
    return novo_resultado

@router.get("/{usuario_id}", response_model=List[ResultadoResponse])
def buscar_resultados(usuario_id: int, db: Session = Depends(get_db)):
    resultados = db.query(Resultado).filter(Resultado.usuario_id == usuario_id).all()
    if not resultados:
        raise HTTPException(status_code=404, detail="Nenhum resultado encontrado.")
    return resultados
