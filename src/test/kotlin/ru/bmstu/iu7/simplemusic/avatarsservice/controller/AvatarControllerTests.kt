package ru.bmstu.iu7.simplemusic.avatarsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.bmstu.iu7.simplemusic.avatarsservice.exception.NotFoundException
import ru.bmstu.iu7.simplemusic.avatarsservice.model.Error
import ru.bmstu.iu7.simplemusic.avatarsservice.service.AvatarService
import java.util.*
import kotlin.random.Random

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [AvatarController::class], secure = false)
class AvatarControllerTests {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    private val mockService: AvatarService? = null

    @Test
    fun performCRUD() {
        val user = this.generateUser()
        val avatarBytes = this.generateAvatarBytes()

        Mockito
                .doNothing().`when`(this.mockService)
                ?.addAvatar(Mockito.anyString(), this.any())

        for (i in 0..1) {
            this.mockMvc!!
                    .perform(MockMvcRequestBuilders
                            .post("/avatars/$user")
                            .contentType(MediaType.IMAGE_JPEG)
                            .content(avatarBytes))
                    .andExpect(MockMvcResultMatchers
                            .status().isOk)
        }

        this.mockMvc!!
                .perform(MockMvcRequestBuilders
                        .post("/avatars/default")
                        .contentType(MediaType.IMAGE_JPEG)
                        .content(avatarBytes))
                .andExpect(MockMvcResultMatchers
                        .status().isForbidden)

        Mockito
                .`when`(this.mockService!!.getAvatar(Mockito.anyString()))
                .thenReturn(avatarBytes)

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/avatars/$user"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk)
                .andExpect(MockMvcResultMatchers
                        .content().bytes(avatarBytes))

        Mockito
                .`when`(this.mockService.getDefaultAvatar())
                .thenReturn(avatarBytes)

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/avatars/default"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk)
                .andExpect(MockMvcResultMatchers
                        .content().bytes(avatarBytes))

        Mockito
                .doNothing().`when`(this.mockService)
                ?.deleteAvatar(Mockito.anyString())

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/avatars/$user"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk)

        val errorMessage = "avatar not found"

        val error = Error(errorMessage)
        val errorStr = this.mapObject(error)

        Mockito
                .`when`(this.mockService.getAvatar(Mockito.anyString()))
                .thenThrow(NotFoundException(errorMessage))

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/avatars/$user"))
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound)
                .andExpect(MockMvcResultMatchers
                        .content().string(errorStr))

        Mockito
                .doThrow(NotFoundException(errorMessage)).`when`(this.mockService)
                ?.deleteAvatar(Mockito.anyString())

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/avatars/$user"))
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound)

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/avatars/default"))
                .andExpect(MockMvcResultMatchers
                        .status().isForbidden)
    }

    private fun generateUser(): String {
        return UUID.randomUUID().toString()
    }

    private fun generateAvatarBytes(): ByteArray {
        val numBytes = 10
        val avatarBytes = ByteArray(numBytes)
        Random.nextBytes(avatarBytes)
        return avatarBytes
    }

    private fun mapObject(obj: Any): String {
        return ObjectMapper().writeValueAsString(obj)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}
