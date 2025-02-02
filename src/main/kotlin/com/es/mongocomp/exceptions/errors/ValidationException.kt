package com.es.mongocomp.exceptions.errors

class ValidationException(message: String) : Exception("Validation error (400) $message") {
}