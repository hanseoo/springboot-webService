package com.webService.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class) // 테스트를 진행할 떄 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다, 스프링 부트 테스트와 JUnit 사이에 연결자 역할입니다.
@WebMvcTest(controllers = HelloController.class) // 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션입니다.
class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 테스트시 사용 합니다.

    @Test
    void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                        .param("name", name) // API를 테스트할 때 사용될 요청 파라미터 입니다, String만 사용 가능합니다.
                        .param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name))) // Json 응답값을 필드별로 검증할 수 있는 메소드입니다, $를 기준으로 필드명을 명시합니다.
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(amount)));

    }
}