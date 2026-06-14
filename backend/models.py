from sqlalchemy import Column, Integer, String, DateTime
from sqlalchemy.sql import func
from database import Base

class Usuario(Base):
    __tablename__ = "usuarios"

    id = Column(Integer, primary_key=True, index=True)
    nome = Column(String, index=True)
    email = Column(String, unique=True, nullable=False)
    criado_em = Column(DateTime, server_default=func.now())

class Resultado(Base):
    __tablename__ = "resultados"

    id = Column(Integer, primary_key=True, index=True)
    usuario_id = Column(Integer, nullable=False)
    perfil = Column(String, nullable=False)
    pontuacao = Column(Integer, nullable=False)
    criado_em = Column(DateTime, server_default=func.now())