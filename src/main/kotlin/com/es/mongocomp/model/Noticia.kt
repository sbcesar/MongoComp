package com.es.mongocomp.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "collNoticias")
data class Noticia(
    @Id
    val _id: ObjectId?,
    @Field
    val titulo: String,
    @Field
    val cuerpo: String,
    @Field
    val fechaPub: Date,
    @Field(name = "tag")
    val tags: List<String>,
    @Field(name = "user")
    val usuario: String
)