package com.es.mongocomp.repository.impl

import com.es.mongocomp.model.Usuario
import com.es.mongocomp.repository.UsuarioCustomRepository
import com.mongodb.client.model.Filters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class UsuarioCustomRepositoryImpl: UsuarioCustomRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun findByCiudad(ciudad: String): List<Usuario> {
        val database = mongoTemplate.db
        val collection = database.getCollection("collUsuarios", Usuario::class.java)

        val filtroCiudad = Filters.eq("direccion.ciudad", ciudad)
        val usuarios = collection.find(filtroCiudad).toList()

        return usuarios
    }

    override fun findByMunicipio(municipio: String): List<Usuario> {
        val database = mongoTemplate.db
        val collection = database.getCollection("collUsuarios", Usuario::class.java)

        val filtroMunicipio = Filters.eq("direccion.municipio", municipio)
        val usuarios = collection.find(filtroMunicipio).toList()

        return usuarios
    }

    override fun findByActivity(estado: Boolean): List<String> {
        val database = mongoTemplate.db
        val collection = database.getCollection("collUsuarios", Usuario::class.java)

        val filtroActividad = Filters.eq("estado", estado)
        val usuarios = collection.find(filtroActividad).toList()

        val usuarioBaneado = mutableListOf<String>()

        usuarios.forEach {
            if (!it.estado) {
                usuarioBaneado.add(it.nick)
            }
        }

        return usuarioBaneado
    }


}