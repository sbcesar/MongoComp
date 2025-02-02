package com.es.mongocomp.repository

import com.es.mongocomp.model.Noticia
import org.springframework.data.mongodb.repository.MongoRepository

interface NoticiaRepository : MongoRepository<Noticia, String> {
}