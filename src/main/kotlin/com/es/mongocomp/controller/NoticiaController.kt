package com.es.mongocomp.controller

import com.es.mongocomp.exceptions.errors.NotFoundException
import com.es.mongocomp.domain.Log
import com.es.mongocomp.model.Noticia
import com.es.mongocomp.dto.NoticiaDTO
import com.es.mongocomp.dto.NoticiaInsertDTO
import com.es.mongocomp.repository.NoticiaRepository
import com.es.mongocomp.utils.LogUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/noticias")
class NoticiaController {

    @Autowired
    private lateinit var noticiaRepository: NoticiaRepository

    @GetMapping("/")
    fun getAll(httpRequest: HttpServletRequest) : ResponseEntity<List<Noticia>> {

        val noticias: List<Noticia>  = noticiaRepository.findAll()
        LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.OK))

        return ResponseEntity(noticias, HttpStatus.OK)
    }

    @PostMapping("/")
    fun insert(
        httpRequest: HttpServletRequest,
        @RequestBody noticiaInsertDTO: NoticiaInsertDTO
    ) : ResponseEntity<NoticiaDTO> {


        // Mapear los campos del DTO al de Entity
        val noticiaEntity: Noticia = Noticia(
            null,
            noticiaInsertDTO.titulo,
            noticiaInsertDTO.cuerpo,
            Date.from(Instant.now()),
            listOf(),
            noticiaInsertDTO.usuario
        )

        val noticiaInsertada = noticiaRepository.insert(noticiaEntity)

        val noticiaResponseDTO = NoticiaDTO(
            noticiaInsertada.titulo,
            noticiaInsertada.cuerpo,
            noticiaInsertada.fechaPub,
            noticiaInsertada.tags,
            noticiaInsertada.usuario
        )

        LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.CREATED))

        return ResponseEntity(noticiaResponseDTO, HttpStatus.CREATED)
    }

    @GetMapping("/{titulo}")
    fun getOne(
        @PathVariable titulo: String,
        httpRequest: HttpServletRequest
    ) : ResponseEntity<Noticia> {

        val noticia = noticiaRepository.findByTitulo(titulo)?.firstOrNull()

        if (noticia != null) {
            LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.OK))
            return ResponseEntity(noticia, HttpStatus.OK)
        } else {
            throw NotFoundException("Noticia no encontrada")
        }


    }

    @DeleteMapping("/{titulo}")
    fun deleteOne(
        @PathVariable titulo: String,
        httpRequest: HttpServletRequest
    ) : ResponseEntity<Noticia> {

        val noticia = noticiaRepository.findByTitulo(titulo)?.firstOrNull()


        if (noticia != null) {
            noticiaRepository.delete(noticia)
            LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.OK))
            return ResponseEntity(noticia, HttpStatus.OK)
        } else {
            throw NotFoundException("Noticia no encontrada")
        }


    }

    @PutMapping("/{id}")
    fun updateOne(
        @PathVariable id: String,
        @RequestBody noticiaToUpdate: Noticia,
        httpRequest: HttpServletRequest
    ) : ResponseEntity<Noticia>? {

        val noticiaExistente = noticiaRepository.findById(id).orElseThrow{
            NotFoundException("No se ha encontrado la noticia")
        }

        val nuevaNoticia = noticiaExistente.copy(
            titulo = noticiaToUpdate.titulo ?: noticiaExistente.titulo,
            cuerpo = noticiaToUpdate.cuerpo ?: noticiaExistente.cuerpo,
            fechaPub = noticiaToUpdate.fechaPub ?: noticiaExistente.fechaPub,
            tags = noticiaToUpdate.tags ?: noticiaExistente.tags,
            usuario = noticiaToUpdate.usuario ?: noticiaExistente.usuario,
        )

        val noticiaActualizada = noticiaRepository.save(nuevaNoticia)
        LogUtils.writeLog(Log(httpRequest.method, httpRequest.requestURI, true, HttpStatus.OK))

        return ResponseEntity(noticiaActualizada, HttpStatus.OK)


    }




}