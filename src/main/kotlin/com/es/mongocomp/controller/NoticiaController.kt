package com.es.mongocomp.controller

import com.es.mongocomp.exceptions.errors.ValidationException
import com.es.mongocomp.model.Noticia
import com.es.mongocomp.repository.NoticiaRepository
import com.es.mongocomp.service.NoticiaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*

@RestController
class NoticiaController {

    @Autowired
    private lateinit var noticiaService: NoticiaService

    @Autowired
    private lateinit var noticiaRepository: NoticiaRepository

    @GetMapping("/noticia")
    fun getNoticias(): List<Noticia> {
        return noticiaRepository.findAll()
    }

    @PostMapping("/noticia/alta")
    fun createNoticia(@RequestBody nuevaNoticia: Noticia?): ResponseEntity<Noticia> {

        if (nuevaNoticia == null) throw ValidationException("Tienes que poner una noticia pixa")

        try {
            val noticiaCreada = noticiaService.createNoticia(nuevaNoticia)
            return ResponseEntity(noticiaCreada, HttpStatus.CREATED)
        } catch (e: Exception) {
            throw ValidationException("Error al crear la noticia")
        }

//        val noticia = Noticia(
//            null,
//            "NOTICIA INSERTADA AHORA",
//            "Se cancelan las clases hasta nuevo aviso",
//            Date.from(Instant.now()),
//            listOf("MUNDO", "ACTUALIDAD"),
//            "idClienteDePrueba"
//        )
//
//        noticiaRepository.insert(noticia)

    }

    @GetMapping("/noticia/{titulo}")
    fun getNoticiaByTitulo(@PathVariable titulo: String?): ResponseEntity<Noticia> {

        if (titulo.isNullOrBlank()) throw ValidationException("Tienes que poner el titulo")

        val noticia = noticiaService.getNoticiaByTitulo(titulo)

        if (noticia != null) return ResponseEntity(noticia, HttpStatus.OK)

        return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @PutMapping("/noticia/{titulo}")
    fun updateNoticia(@PathVariable titulo: String?, @RequestBody noticia: Noticia?): ResponseEntity<Noticia> {
        if (titulo.isNullOrBlank()) throw ValidationException("Tienes que poner el titulo")

        if (noticia == null) throw ValidationException("Tienes que poner la noticia pixa")

        noticiaService.getNoticiaById(titulo) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)

        try {
            val noticiaUpdated = noticiaService.updateNoticia(noticia)
            return ResponseEntity(noticiaUpdated, HttpStatus.OK)
        } catch (e: Exception) {
            throw ValidationException("Error al actualizar la noticia")
        }
    }

    @DeleteMapping("/noticia/{titulo}")
    fun deleteNoticia(@PathVariable titulo: String?): ResponseEntity<Noticia?> {

        return ResponseEntity(null, HttpStatus.OK)
    }


}