package com.es.mongocomp.dto

import java.util.*

data class NoticiaDTO(
    val titulo: String,
    val cuerpo: String,
    val fechaPub: Date,
    val tags: List<String>?,
    val usuario: String
)
