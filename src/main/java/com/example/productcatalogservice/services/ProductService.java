package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
//@Primary
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate
                .getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
            products.add(getProductFromDto(fakeStoreProductDto));
        }
        System.out.println("Product received from server" + products.size());
        return products;
    }
    public Flux<Product> getAllProducts1(){
        //RestTemplate restTemplate = restTemplateBuilder.build();
        // option 1: create webclient with default settings
        WebClient webClient = WebClient.create();
        // option 2:
        WebClient webClient1 = WebClient.create("https://fakestoreapi.com/products");
        Flux<FakeStoreProductDto> response = webClient1.get().retrieve().bodyToFlux(FakeStoreProductDto.class);
        System.out.println(response);
        List<Product> products = new ArrayList<>();
        response.subscribe(fakeStoreProductDto -> {
            System.out.println(fakeStoreProductDto);
            products.add(getProductFromDto(fakeStoreProductDto));
            /*for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
                products.add(getProductFromDto(fakeStoreProductDto));
            }*/
        });

        System.out.println("Product received from server" + products.size());
        /*FakeStoreProductDto[] fakeStoreProductDtos = restTemplate
                .getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

         */

        /*for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
            products.add(getProductFromDto(fakeStoreProductDto));
        }*/
        //return products;
        return null;
    }
    @Override
    public Product getProduct(Long productId){

        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForEntity(
                        "https://fakestoreapi.com/products/{productId}",
                        FakeStoreProductDto.class,
                        productId)
                .getBody();
        return getProductFromDto(fakeStoreProductDto);
    }
    @Override
    public Product createProduct(Product product){
        /*
        how to map all the fields correctly from productdto to fakestoredto
        not all fields are getting mapped
         */
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDtoreq = getFakeStoreDtoFromProduct(product);
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .postForEntity("https://fakestoreapi.com/products",fakeStoreProductDtoreq, FakeStoreProductDto.class)
                .getBody();
        return getProductFromDto(fakeStoreProductDto);
    }

    public Product replaceProduct(Long id, Product product){
        FakeStoreProductDto fakeStoreProductDtoReq = getFakeStoreDtoFromProduct(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = putForEntity(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDtoReq,
                FakeStoreProductDto.class,
                id
        );
        return getProductFromDto(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public String deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                deleteEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class
        );
        if (fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return "Product deleted successfully";
        }
        return "Product not deleted";
    }

    private <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
    private <T> ResponseEntity<T> deleteEntity(String url, Class<T> responseType) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor);
    }

    private Product getProductFromDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());

        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        /*
        why getImageUrl is not working
         */
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreDtoFromProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }
}
