package com.example.mikroserwis.Service;

import com.example.mikroserwis.Model.Part;
import com.example.mikroserwis.Repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Optional<Part> getPartBySerialNumber(int serialNumber) {
        return partRepository.findById(serialNumber);
    }

    public Part saveOrIncrementPart(Part part) {
        Optional<Part> existingPart = partRepository.findByMaterialNumberAndSupplierNumber(part.getMaterialNumber(), part.getSupplierNumber());

        if (existingPart.isPresent()) {
            Part existing = existingPart.get();
            existing.setQuantity(existing.getQuantity() + 1);
            return partRepository.save(existing);
        } else {
            part.setQuantity(1);
            return partRepository.save(part);
        }
    }

    public void takePart(int serialNumber) {
        Optional<Part> part = partRepository.findById(serialNumber);
        if (part.isPresent()) {
            Part existingPart = part.get();
            if (existingPart.getQuantity() > 1) {
                existingPart.setQuantity(existingPart.getQuantity() - 1);
                partRepository.save(existingPart);
            }
            else {
                partRepository.delete(existingPart);
            }
        }
    }
}
