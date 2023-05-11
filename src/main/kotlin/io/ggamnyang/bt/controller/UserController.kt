package io.ggamnyang.bt.controller

import io.ggamnyang.bt.domain.entity.User
import io.ggamnyang.bt.dto.common.UserDto
import io.ggamnyang.bt.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun signUp(
        @RequestBody userDto: UserDto
    ): ResponseEntity<User> {
        return ResponseEntity(userService.save(userDto), HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userDto: UserDto
    ): ResponseEntity<String> {
        return ResponseEntity(userService.login(userDto), HttpStatus.OK)
    }
}
