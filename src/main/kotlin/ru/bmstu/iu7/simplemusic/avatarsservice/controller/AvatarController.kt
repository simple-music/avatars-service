package ru.bmstu.iu7.simplemusic.avatarsservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/avatars/{user}")
class AvatarController {
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
