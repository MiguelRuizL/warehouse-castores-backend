package com.example.warehouse_castores.service;

import com.example.warehouse_castores.dto.ProductDTO;
import com.example.warehouse_castores.model.Product;
import com.example.warehouse_castores.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(Boolean status) {
        return (status != null) ? productRepository.findByStatus(status) : productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto con id no encontrado: " + id)
        );
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long idProduct, ProductDTO productData) {
        Product productToUpdate = this.getProductById(idProduct);
        // nombre
        if(productData.getName() != null)
            productToUpdate.setName(productData.getName());

        // status
        if(productData.getStatus() != null)
            productToUpdate.setStatus(productData.getStatus());

        // cantidad que sobreescribe
        if(productData.getFullNewQuantity() != null)
            productToUpdate.setQuantity(productData.getFullNewQuantity());

        // cantidad para restar/sumar
        if(productData.getToSumQuantity() != null) {
            Integer toSumQuantity = productData.getToSumQuantity();
            Integer currentQuantity = productToUpdate.getQuantity();
            int newQuantity = currentQuantity + toSumQuantity;
            if(newQuantity < 0)
                throw new IllegalArgumentException("Imposible retirar más de lo que se tiene en inventario");
            productToUpdate.setQuantity(newQuantity);
        }

        return productRepository.save(productToUpdate);
    }

    @Transactional
    public Product updateProductQuantity(Long idProduct, ProductDTO productData) {
        Product productToUpdate = this.getProductById(idProduct);
        String type = productData.getType();
        Integer quantityChange = productData.getToSumQuantity();

        if (quantityChange == null || quantityChange == 0) {
            throw new IllegalArgumentException("La cantidad a sumar/retirar no puede ser nula o 0.");
        }

        // Módulo de inventario (entradas)
        if("input".equals(type)) {
            if (quantityChange < 0) {
                throw new IllegalArgumentException("No se puede efectuar la salida de un producto desde este módulo.");
            } else {
                int newQuantity = productToUpdate.getQuantity() + quantityChange;
                productToUpdate.setQuantity(newQuantity);
            }
        } else if ("output".equals(type)) { // Módulo de salidas
            if (quantityChange > 0) {
                throw new IllegalArgumentException("No se puede efectuar la entrada de un producto desde este módulo.");
            } else {
                int newQuantity = productToUpdate.getQuantity() - (Math.abs(quantityChange));
                if(newQuantity < 0) throw new IllegalArgumentException("Imposible retirar más de lo que se tiene en inventario");
                productToUpdate.setQuantity(newQuantity);
            }
        } else {
            throw new IllegalArgumentException("El tipo de movimiento '" + productData.getType() + "' no es válido. Debe ser 'input' o 'output'.");
        }

        return productRepository.save(productToUpdate);
    }
}
