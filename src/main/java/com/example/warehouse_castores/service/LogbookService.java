package com.example.warehouse_castores.service;

import com.example.warehouse_castores.dto.auth.UserResponseDTO;
import com.example.warehouse_castores.model.Logbook;
import com.example.warehouse_castores.model.Product;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.repository.LogbookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogbookService {
    @Autowired
    LogbookRepository logbookRepository;

    @Autowired
    UserService userService;

    public List<Logbook> getGeneralLogbook(String movementType) {
        return (movementType != null && !movementType.isEmpty()) ?
                logbookRepository.findByMovementType(movementType) :
                logbookRepository.findAll();
    }

    @Transactional
    public Logbook insertLogbook(String movementType, Product product, Integer quantity) {
        User currentUser = userService.getAuthenticatedUserEntity();
        Logbook lg = new Logbook();
        lg.setMovementType(movementType);
        lg.setProduct(product);
        lg.setUser(currentUser);
        lg.setQuantity(quantity);
        lg.setDoneAt(LocalDateTime.now());
        logbookRepository.save(lg);

        return lg;
    }
}
