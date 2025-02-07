package com.es.mongocomp.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("collUsuarios")
data class Usuario(
    val _id: String,
    val nombre: String,
    val nick: String,
    val estado: Boolean,
    val telefonos: List<String?>,
    val direccion: Direccion
)