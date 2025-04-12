package com.example.mikroserwis.Controller;

import com.example.mikroserwis.Model.Part;
import com.example.mikroserwis.Service.PartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parts")
@Slf4j
public class PartController {

    private final PartService partService;


    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<Part> getAllParts() {
        log.info("Getting all parts");
        return partService.getAllParts();
    }

    @GetMapping("/{serialNumber}")
    public Part getPartBySerialNumber(@PathVariable int serialNumber) {
        log.info("Getting part with serial number: {}", serialNumber);
        return partService.getPartBySerialNumber(serialNumber).orElse(null);
    }

    @PostMapping
    public Part addPart(@RequestBody Part part) {
        log.info("Adding part: {}", part);
        return partService.saveOrIncrementPart(part);
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Part> takePart(@PathVariable int serialNumber) {
        log.info("Taking part with serial number: {}", serialNumber);
        Optional<Part> part = partService.getPartBySerialNumber(serialNumber);

        if (part.isEmpty()) {
            log.error("Part with serial number {} not found", serialNumber);
            return ResponseEntity.notFound().build();
        }

        partService.takePart(serialNumber);
        return ResponseEntity.ok(part.get());
    }
}
