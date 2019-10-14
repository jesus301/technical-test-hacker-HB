package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.service.HackerDetectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HackerDetectorController.class)
@ContextConfiguration(classes = {
        HackerDetectorController.class
})
public class HackerDetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HackerDetectorService hackerDetectorService;

    @Test
    public void testProcessLineErrorDeserialization() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(post("/api/hacker/detector/process-line")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("80.238.9.125,133612947,SIGNIN_FAILURE,Will.Smith")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertNull(mvcResult.getResponse().getContentType());
    }
}
