package com.es.mongocomp.dto

data class UsuarioDTO(
    val email: String,
    val nombre: String,
    val nick: String,
    val telefono1: String?,
    val telefono2: String?,
    val calle: String,
    val numero: String,
    val cp: String,
    val ciudad: String
)
