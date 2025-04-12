package com.example.mikroserwis.Service;

import com.example.mikroserwis.Model.Part;
import com.example.mikroserwis.Repository.PartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PartServiceTest {

    @InjectMocks
    private PartService partService;
    @Mock
    private PartRepository partRepository;


    @Test
    public void testGetAllParts() {
        Part part1 = new Part(123,5,110);
        Part part2 = new Part(124,3,134);
        when(partService.getAllParts()).thenReturn(Arrays.asList(part1, part2));
        assertEquals(partService.getAllParts().size(), 2);
        assertEquals(partService.getAllParts().get(0).getMaterialNumber(), 123);
        assertEquals(partService.getAllParts().get(1).getMaterialNumber(), 124);
        assertEquals(partService.getAllParts().get(0).getQuantity(), 5);
        assertEquals(partService.getAllParts().get(1).getQuantity(), 3);
        assertEquals(partService.getAllParts().get(0).getSupplierNumber(), 110);
        assertEquals(partService.getAllParts().get(1).getSupplierNumber(), 134);
    }

    @Test
    public void testGetPartBySerialNumber() {
        Part part = new Part(1,123,5,110);
        when(partService.getPartBySerialNumber(1)).thenReturn(java.util.Optional.of(part));
        assertEquals(partService.getPartBySerialNumber(1).get().getSerialNumber(), 1);
        assertEquals(partService.getPartBySerialNumber(1).get().getMaterialNumber(), 110);
        assertEquals(partService.getPartBySerialNumber(1).get().getQuantity(), 5);
        assertEquals(partService.getPartBySerialNumber(1).get().getSupplierNumber(), 123);
    }

    @Test
    public void testSaveOrIncrementPart() {
        Part part = new Part(123,5,110);  //Inkrementacja istniejącego elementu
        when(partRepository.findByMaterialNumberAndSupplierNumber(part.getMaterialNumber(), part.getSupplierNumber()))
                .thenReturn(Optional.of(part));

        when(partRepository.save(part)).thenReturn(part);

        Part savedPart = partService.saveOrIncrementPart(part);

        assertEquals(savedPart.getQuantity(),6);

        Part part2 = new Part(234,345);  // Dodanie nowego elementu
        when(partRepository.findByMaterialNumberAndSupplierNumber(part2.getMaterialNumber(), part2.getSupplierNumber()))
                .thenReturn(Optional.empty());

        when(partRepository.save(part2)).thenReturn(part2);

        Part savedPart2 = partService.saveOrIncrementPart(part2);

        assertEquals(savedPart2.getQuantity(),1);

    }

    @Test
    public void testTakePart() {
        Part part = new Part(1,123,5,110);
        when(partRepository.findById(1)).thenReturn(Optional.of(part));
        partService.takePart(1);
        assertEquals(part.getQuantity(),4);
        verify(partRepository).save(part);
    }
}
