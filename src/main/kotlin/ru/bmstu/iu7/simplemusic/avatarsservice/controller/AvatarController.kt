package ru.bmstu.iu7.simplemusic.avatarsservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bmstu.iu7.simplemusic.avatarsservice.service.AvatarService

@RestController
@RequestMapping("/avatars/{user}")
class AvatarController(@Autowired private val avatarService: AvatarService) {
    @PostMapping
    fun addAvatar(@PathVariable("user") user: String,
                  @RequestBody data: ByteArray) : ResponseEntity<Any> {
        TODO("not implemented")
    }

    @GetMapping
    fun getAvatar(@PathVariable("user") user: String) : ResponseEntity<ByteArray> {
        TODO("not implemented")
    }

    @DeleteMapping
    fun deleteAvatar(@PathVariable("user") user: String) : ResponseEntity<Any> {
        TODO("not implemented")
    }
}
