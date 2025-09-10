package com.natchapol.b2b_app.services;
import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponseDTO createProduct(CreateProductDTO product);
}
