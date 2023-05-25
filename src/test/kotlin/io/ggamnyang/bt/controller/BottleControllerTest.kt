package io.ggamnyang.bt.controller

import io.ggamnyang.bt.auth.WithAuthUser
import io.ggamnyang.bt.domain.entity.Bottle
import io.ggamnyang.bt.domain.entity.User
import io.ggamnyang.bt.domain.enum.BottleSource
import io.ggamnyang.bt.service.BottleService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "test")
class BottleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var bottleService: BottleService

    private lateinit var bottle: Bottle

    @BeforeEach
    fun beforeEach() {
        val creator = User("creator", "password")
        val receiver = User("receiver", "password")

        bottle = Bottle(creator, receiver, "test letter")
    }

    @AfterEach
    fun afterEach() {
    }

    @Test
    @DisplayName("GET /api/v1/bottles 테스트 - 성공")
    @WithAuthUser("creator")
    fun `get bottles + bottleSource == CREATED - success`() {
        // FIXME: 더 나은 방식이 있을 것 같다..
        `when`(bottleService.findAll(User("creator", "password"), BottleSource.CREATED)).thenReturn(arrayListOf(bottle))

        mockMvc.get("/api/v1/bottles") {
            contentType = MediaType.APPLICATION_JSON
            param("bottleSource", "CREATED")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
            }
    }
}
