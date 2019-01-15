package ru.bmstu.iu7.simplemusic.avatarsservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bmstu.iu7.simplemusic.avatarsservice.service.AvatarService

@RestController
@RequestMapping("/avatars/{user}")
class AvatarController(@Autowired private val avatarService: AvatarService) {
    @PostMapping(consumes = [MediaType.IMAGE_JPEG_VALUE])
    fun addAvatar(@PathVariable("user") user: String,
                  @RequestBody data: ByteArray): ResponseEntity<Any> {
        this.avatarService.addAvatar(user, data)
        return ResponseEntity.ok().build()
    }

    @GetMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getAvatar(@PathVariable("user") user: String): ResponseEntity<ByteArray> {
        val data = this.avatarService.getAvatar(user)
        return ResponseEntity.ok(data)
    }

    @DeleteMapping
    fun deleteAvatar(@PathVariable("user") user: String): ResponseEntity<Any> {
        this.avatarService.deleteAvatar(user)
        return ResponseEntity.ok().build()
    }
}
