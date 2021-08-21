package net.dezang.restdocopenapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@WebMvcTest(UserController.class)
class UserApiDoc {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;

    @Test
    void list() throws Exception {
        //given
        given(userService.list())
            .willReturn(Collections.singletonList(
                User.builder()
                    .username("dezang")
                    .age(10)
                    .build()
            ));

        //when
        ResultActions resultActions = mockMvc.perform(get("/users"));

        //then
        resultActions
            .andExpect(status().isOk())
            .andDo(document("list-user",
                responseFields(
                    fieldWithPath("[].username").description("사용자 아이디"),
                    fieldWithPath("[].age").description("사용자 나이")
                )))
            .andDo(print());
    }
}