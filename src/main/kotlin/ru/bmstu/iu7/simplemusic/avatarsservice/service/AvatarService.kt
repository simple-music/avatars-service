package ru.bmstu.iu7.simplemusic.avatarsservice.service

interface AvatarService {
    fun addAvatar(user: String, data: ByteArray)
    fun getAvatar(user: String): ByteArray
    fun getDefaultAvatar(): ByteArray
    fun deleteAvatar(user: String)
}
