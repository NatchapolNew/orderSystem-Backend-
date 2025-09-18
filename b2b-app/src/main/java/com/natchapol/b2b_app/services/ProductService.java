package com.natchapol.b2b_app.services;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDTO createProduct(CreateProductDTO product, MultipartFile file);

    ProductResponseDTO updateProduct(Long id, UpdateProductDTO updateProductDTO,MultipartFile file);

    boolean deleteProduct(Long id);

    String uploadFile(MultipartFile file);

    boolean deleteImgFile(String filename);

    List<ProductResponseDTO> readAllProduct();

    List<ProductResponseDTO> readProductByName(String name);
}
