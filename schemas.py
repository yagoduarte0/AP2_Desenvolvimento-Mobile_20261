from pydantic import BaseModel, EmailStr
from datetime import datetime
from typing import Optional

class UsuarioCreate(BaseModel):
    nome: str
    email: str

class UsuarioResponse(BaseModel):
    id: int
    nome: str
    email: str
    criado_em: datetime

    class Config:
        from_attributes = True

class ResultadoCreate(BaseModel):
    usuario_id: int
    perfil: str
    pontuacao: int

class ResultadoResponse(BaseModel):
    id: int
    usuario_id: int
    perfil: str
    pontuacao: int
    criado_em: datetime

    class Config:
        from_attributes = True