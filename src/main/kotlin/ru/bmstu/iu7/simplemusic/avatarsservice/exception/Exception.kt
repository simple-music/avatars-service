package ru.bmstu.iu7.simplemusic.avatarsservice.exception

open class ServiceException(message: String?) : RuntimeException(message)

class NotFoundException(message: String?) : ServiceException(message)
