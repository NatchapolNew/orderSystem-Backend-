package com.natchapol.b2b_app.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.CategoryEntity;
import com.natchapol.b2b_app.entity.ProductsEntity;
import com.natchapol.b2b_app.exception.AlreadyExistProduct;
import com.natchapol.b2b_app.mapper.ProductMapper;
import com.natchapol.b2b_app.repository.CategoryProductRepository;
import com.natchapol.b2b_app.repository.ProductsRepository;
import com.natchapol.b2b_app.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    //inject ค่าจากapplication.properties
    @Value("${aws.s3.bucketname}")
    private String bucketName;
    @Autowired
    private S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) {
        //ประกาศตัวแปรเป็นสตริงจากfile.getOriginalFilename substringหาเครื่องหมายจุดแล้วเอาชื่อไฟล์จากตัวสุดท้าย
        String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //สร้างkey uuidแบบไม่ซ้ำแล้วต่อสตริงเป็นชื่อไฟล์
        String key = UUID.randomUUID().toString() + "." + filenameExtension;
        try {
            //สร้างคำสั่งUploadfile
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    //ให้ทุกคนสามารถเข้าถึงไฟล์ได้ผ่านลิงก์
                    .acl("public-read")
                    //บอกชนิดของไฟล์
                    .contentType(file.getContentType())
                    .build();
            //uploadfileไปยังawsS3 เก็บใส่ตัวแปรresponseที่มีชนิดเป็นPutObjectResponse
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
            }
            //IOException (ย่อมาจาก Input/Output Exception) ในภาษา Java คือข้อผิดพลาด (Exception) ที่เกิดขึ้นเมื่อโปรแกรมมีปัญหาเกี่ยวกับ การอ่าน/เขียนข้อมูล
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while loading the file");
        }
    }

    @Override
    public boolean deleteImgFile(String filename) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
            return true;
        } catch (S3Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ProductResponseDTO createProduct(CreateProductDTO product, MultipartFile file) {
        Optional<ProductsEntity> CheckProduct = productsRepository.findByName(product.getName());
        if (CheckProduct.isPresent()) {
            throw new AlreadyExistProduct("มีสินค้านี้แล้วในระบบ");
        } else {
            ProductsEntity productsEntity = ProductMapper.INSTANCE.toEntity(product);

            //uploadfile method แล้วsetเข้าentity
            String imgUrl = uploadFile(file);
            productsEntity.setImgUrl(imgUrl);
            ProductsEntity products = productsRepository.save(productsEntity);

            ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.toResponseDTO(products);
            return productResponseDTO;
        }
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, UpdateProductDTO updateProductDTO, MultipartFile file) {
        ProductsEntity products = productsRepository.findById(id).orElseThrow(() -> new RuntimeException("ไม่พบสินค้า"));
        CategoryEntity category = categoryProductRepository.findById(updateProductDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("ไม่พบหมวดหมู่สินค้า"));
        if (file != null && !file.isEmpty()) {
            String img = products.getImgUrl();
            String fileName = img.substring(img.lastIndexOf("/") + 1);
            deleteImgFile(fileName);
            String imgUrl = uploadFile(file);
            updateProductDTO.setImgUrl(imgUrl);
        }

        ProductMapper.INSTANCE.updateEntityFromDto(updateProductDTO,products);
        products.setCategory(category);

        ProductsEntity productsEntity = productsRepository.save(products);

        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.toResponseDTO(productsEntity);

        return productResponseDTO;

    }

    @Override
    public boolean deleteProduct(Long id) {
        ProductsEntity checkProduct = productsRepository.findById(id).orElseThrow((() -> new RuntimeException("ไม่พบสินค้า")));
        String imgUrl = checkProduct.getImgUrl();
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        deleteImgFile(fileName);
        productsRepository.deleteById(id);
        return true;
    }
}
