package com.es.mongocomp.exceptions.errors

class NotFoundException(message: String) : Exception("Not found error (404) -> ($message)") {
}