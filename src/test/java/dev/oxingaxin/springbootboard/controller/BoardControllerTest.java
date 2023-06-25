package dev.oxingaxin.springbootboard.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import dev.oxingaxin.springbootboard.entity.Board;
import dev.oxingaxin.springbootboard.service.BoardService;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BoardService boardService;

    @Test
    @Order(1)
    void testGetBoards() throws Exception {
        given(boardService.getBoards())
            .willReturn(
                List.of(new Board(0L, "새로운 보드1", "테스트용 보드1입니다", "까롱이"),
                        new Board(1L, "새로운 보드2", "테스트용 보드2입니다", "까롱이"))
            );

        mockMvc.perform(get("/api"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].title", equalTo("새로운 보드1")));
    }

}
