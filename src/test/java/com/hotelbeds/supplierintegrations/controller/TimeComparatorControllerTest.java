package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.service.TimeComparatorService;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TimeComparatorController.class)
@ContextConfiguration(classes = {
        TimeComparatorController.class
})
public class TimeComparatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeComparatorService timeComparatorService;

    @Test
    public void testTimeComparatorDeserializer() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(post("/api/comparator/time-comparator")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"time1\":\"Thu, 21 Dec 2000 16:00:00 +0200\",\"time2\":\"Thu, 21 Dec 2020 16:41:59 +0900\"}"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertEquals("0", mvcResult.getResponse().getContentAsString());
    }
}
