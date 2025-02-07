package com.es.mongocomp.utils

import com.es.mongocomp.dto.UsuarioDTO
import com.es.mongocomp.model.Direccion
import com.es.mongocomp.model.Usuario

object DTOMapper {

    fun userDTOToEntity(usuarioInsertDTO: UsuarioDTO) : Usuario {
        return Usuario(
            usuarioInsertDTO.email,
            usuarioInsertDTO.nombre,
            usuarioInsertDTO.nick,
            true,
            listOf(usuarioInsertDTO.telefono1, usuarioInsertDTO.telefono2),
            Direccion(
                usuarioInsertDTO.calle,
                usuarioInsertDTO.numero,
                usuarioInsertDTO.cp,
                usuarioInsertDTO.ciudad
            )
        )
    }

    fun userEntityToDTO(usuario: Usuario) : UsuarioDTO {

        return UsuarioDTO(
            usuario._id,
            usuario.nombre,
            usuario.nick,
            usuario.telefonos[0] ?: "",
            usuario.telefonos[1] ?: "",
            usuario.direccion.calle,
            usuario.direccion.num,
            usuario.direccion.cp,
            usuario.direccion.ciudad
        )

    }

    fun listOfUserEntitiesToDTO(usuarios: List<Usuario>) : List<UsuarioDTO> {
        return usuarios.map {
            UsuarioDTO(
                it._id,
                it.nombre,
                it.nick,
                it.telefonos[0] ?: "",
                it.telefonos[1] ?: "",
                it.direccion.calle,
                it.direccion.num,
                it.direccion.cp,
                it.direccion.ciudad
            )
        }

    }
}