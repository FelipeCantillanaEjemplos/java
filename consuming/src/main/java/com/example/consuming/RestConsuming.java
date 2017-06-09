package com.example.consuming;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.example.model.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

/**
 * Created by Felipe on 08/06/2017.
 */
@Component
public class RestConsuming implements CommandLineRunner{
    public static void callRestServiceShow(){
        RestTemplate restTemplate= new RestTemplate();
        Product product= restTemplate.getForObject("http://localhost:8080/product/show/1", Product.class);
        System.out.println("producto: "+ product.toString());
    }
    public static void callRestServiceList(){
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/product/list", Product[].class);
        Product[] products = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        for(int i=0; i<products.length; i++){
            System.out.println("Producto:"+products[i].getId());
        }
    }
    public static void postRestServiceList() throws URISyntaxException {
        Product product=new Product();
        product.setId(12);
        product.setDescription("weno");
        product.setImageUrl("sasd");
        product.setPrice(new BigDecimal(12.2));
        product.setProductId("211231");
        product.setVersion(2);

        RequestEntity<Product> request = RequestEntity.post(new URI("http://localhost:8080/product/add")).accept(MediaType.APPLICATION_JSON).body(product);
        System.out.println(request.getBody());
        callRestServiceList();

    }
    public void post(){

        Product product=new Product();
        product.setDescription("weno");
        product.setImageUrl("sasd");
        product.setPrice(new BigDecimal(12.2));
        product.setProductId("211231");
        product.setVersion(2);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8080/product/add",product,String.class);
    }
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("-----------------------------------");
        callRestServiceShow();
        System.out.println("-----------------------------------");
        callRestServiceList();
        System.out.println("-----------------------------------");
        postRestServiceList();
        System.out.println("-----------------------------------");
        post();

    }
}
