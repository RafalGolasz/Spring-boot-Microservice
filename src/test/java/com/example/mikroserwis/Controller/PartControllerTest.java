package com.example.mikroserwis.Controller;

import com.example.mikroserwis.Model.Part;
import com.example.mikroserwis.Service.PartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PartController.class)
public class PartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartService partService;

    @Test
    public void testGetAllParts() throws Exception {
        Part part = new Part(128, 10, 111);
        when(partService.getPartBySerialNumber(128)).thenReturn(Optional.of(part));

        mockMvc.perform(get("/parts/128"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.materialNumber").value(128))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(partService).getPartBySerialNumber(128);

    }

    @Test
    public void testGetPartBySerialNumber() throws Exception {
        Part part = new Part(5,128, 10, 111);
        when(partService.getPartBySerialNumber(5)).thenReturn(Optional.of(part));

        mockMvc.perform(get("/parts/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(5))
                .andExpect(jsonPath("$.supplierNumber").value(128))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.materialNumber").value(111));

        verify(partService).getPartBySerialNumber(5);

    }

    @Test
    public void testTakePart() throws Exception {
        Part part = new Part(9,128, 10, 111);
        when(partService.getPartBySerialNumber(9)).thenReturn(Optional.of(new Part(9,128,9,111)));

        mockMvc.perform(delete("/parts/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(9))
                .andExpect(jsonPath("$.supplierNumber").value(128))
                .andExpect(jsonPath("$.quantity").value(9))
                .andExpect(jsonPath("$.materialNumber").value(111));

        verify(partService).takePart(9);

    }

    @Test
    public void testAddPart() throws Exception {
        Part partAdd = new Part(19, 6, 278);
        when(partService.saveOrIncrementPart(any(Part.class))).thenReturn(partAdd);

        mockMvc.perform(post("/parts")
                        .contentType("application/json")
                        .content("{\"materialNumber\": 19, \"quantity\": 1, \"supplierNumber\": 278}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(6))
                .andExpect(jsonPath("$.materialNumber").value(19))
                .andExpect(jsonPath("$.supplierNumber").value(278));
    }


}
