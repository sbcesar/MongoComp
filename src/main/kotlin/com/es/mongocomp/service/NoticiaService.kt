package com.es.mongocomp.service

import com.es.mongocomp.repository.NoticiaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoticiaService {

    @Autowired
    private lateinit var noticiaRepository: NoticiaRepository


}