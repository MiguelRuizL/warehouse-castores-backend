package com.example.warehouse_castores.controller;

import com.example.warehouse_castores.dto.GenericResponseDTO;
import com.example.warehouse_castores.dto.auth.UserResponseDTO;
import com.example.warehouse_castores.model.Logbook;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.service.LogbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logbook")
public class LogbookController {
    @Autowired
    LogbookService logbookService;

    @GetMapping("")
    public List<Logbook> getGeneralLogbook(
            @RequestParam(required = false) String movementType
    ) {
        return logbookService.getGeneralLogbook(movementType);
    }

    // Ya no se usa
    @PostMapping("")
    public ResponseEntity<GenericResponseDTO<Object>> insertIntoLogbook(
            @RequestParam(required = true) String movementType
    ) {
        //logbookService.insertLogbook(movementType);
        return new ResponseEntity<>(new GenericResponseDTO<>(
                true,
                "Inserción en bitácora exitosa",
                null
        ), HttpStatus.CREATED);
    }
}
