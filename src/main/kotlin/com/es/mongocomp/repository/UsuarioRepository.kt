package com.es.mongocomp.repository

import com.es.mongocomp.model.Usuario
import org.springframework.data.mongodb.repository.MongoRepository

interface UsuarioRepository : MongoRepository<Usuario, String>, UsuarioCustomRepository{
}