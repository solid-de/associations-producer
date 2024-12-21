package edu.cnam.nfe101.associations.producer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cnam.nfe101.associations.producer.service.AssociationManager;

@RestController
@RequestMapping("/associations")
public class AssociationController {
    
    private final AssociationManager associationManager;

    public AssociationController(AssociationManager associationManager) {
        this.associationManager = associationManager;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestParam String file) {
        try {
            associationManager.transfer(file);
            return ResponseEntity.ok("File imported successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }

    }
}
