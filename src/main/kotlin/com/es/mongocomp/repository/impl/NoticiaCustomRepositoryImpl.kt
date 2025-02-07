package com.es.mongocomp.repository.impl

import com.es.mongocomp.model.Noticia
import com.es.mongocomp.repository.NoticiaCustomRepository
import com.mongodb.client.model.Filters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class NoticiaCustomRepositoryImpl : NoticiaCustomRepository {

    // Necesitamos la bd para acceder a la coleccion
    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun getNoticiasBetweenDates(startDate: Date, endDate: Date): List<Noticia> {

        // Obtener la db
        val db = mongoTemplate.db

        // Obtener la coleccion
        val collection = db.getCollection("collNoticias", Noticia::class.java)

        // Poner los filters
        val filter = Filters.and(
            Filters.gte("fechaPub", startDate),
            Filters.gte("fechaPub", endDate),
        )

        // Ejecutar consulta
        val noticias = collection.find(filter).toList()

        return noticias
    }


}