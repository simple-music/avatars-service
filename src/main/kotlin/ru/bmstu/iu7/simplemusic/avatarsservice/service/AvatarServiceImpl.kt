package ru.bmstu.iu7.simplemusic.avatarsservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class AvatarServiceImpl(
        @Value("\${uploads.directory}")
        private val rootPath: String) : AvatarService {

    private val uploadsDir: File = File(rootPath)

    init {
        assert(this.uploadsDir.exists() || this.uploadsDir.mkdirs())
    }

    override fun addAvatar(user: String, data: ByteArray) {
        TODO("not implemented")
    }

    override fun getAvatar(user: String): ByteArray {
        println(rootPath)
        TODO("not implemented")
    }

    override fun deleteAvatar(user: String) {
        TODO("not implemented")
    }
}
