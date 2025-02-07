package com.es.mongocomp.exceptions.handler

import com.es.mongocomp.domain.Log
import com.es.mongocomp.exceptions.errors.NotFoundException
import com.es.mongocomp.exceptions.errors.ValidationException
import com.es.mongocomp.utils.LogUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class APIExceptionHandler {


    @ExceptionHandler(
        IllegalArgumentException::class
        , NumberFormatException::class
        , ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequest(request: HttpServletRequest, e: Exception) : ErrorRespuesta {
        LogUtils.writeLog(Log(request.method, request.requestURI, false, HttpStatus.BAD_REQUEST))
        return ErrorRespuesta(e.message!!, request.requestURI)
    }


    @ExceptionHandler(NotFoundException::class) // Las "clases" (excepciones) que se quieren controlar
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFound(request: HttpServletRequest, e: Exception) : ErrorRespuesta {
        LogUtils.writeLog(Log(request.method, request.requestURI, false, HttpStatus.NOT_FOUND))
        return ErrorRespuesta(e.message!!, request.requestURI)
    }

    @ExceptionHandler(Exception::class, NullPointerException::class) // Las "clases" (excepciones) que se quieren controlar
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleGeneric(request: HttpServletRequest, e: Exception) : ErrorRespuesta {
        LogUtils.writeLog(Log(request.method, request.requestURI, false, HttpStatus.INTERNAL_SERVER_ERROR))
        return ErrorRespuesta(e.message!!, request.requestURI)
    }


}