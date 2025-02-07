package com.es.mongocomp.repository

import com.es.mongocomp.model.Noticia
import java.util.Date

interface NoticiaCustomRepository {

    fun getNoticiasBetweenDates(startDate: Date, endDate: Date): List<Noticia>
}