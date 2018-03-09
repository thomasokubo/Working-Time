package com.project.workingtime.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CheckController checkController;

    @Test
    public void checkIfCheckControllerIsCreated() {
        assertThat(checkController).isNotNull();
    }

    @Test
    public void checkIfCheckintReturnsAValidDateTimeMessage() throws Exception {
        this.mockMvc.perform(get("/check-in")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Check in")));
    }

    @Test
    public void checkIfCheckoutReturnsAValidDateTimeMessage() throws Exception {
        this.mockMvc.perform(get("/check-out")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Check out")));
    }

}
