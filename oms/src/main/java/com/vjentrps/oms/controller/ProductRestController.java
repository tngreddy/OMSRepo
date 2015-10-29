package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.ProductService;

@RestController
@RequestMapping(value = "/service/product")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseDTO getProducts() {

		List<Product> products = new ArrayList<Product>();

		products = productService.getAllproducts();
		return new ResponseDTO(products);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable long productId) {

		Product product = productService.getProductById(productId);
		return product;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseDTO addproduct(@RequestBody Product product) {

		productService.addProduct(product);
		return new ResponseDTO();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseDTO updateProduct(@RequestBody Product product) {

		productService.updateProduct(product);
		return new ResponseDTO();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public ResponseDTO deleteProduct(@PathVariable int productId) {

		productService.deleteProduct(productId);

		return new ResponseDTO();
	}
	
	
	@RequestMapping(value = "/{productId}/stock/{stock}", method = RequestMethod.DELETE)
	public ResponseDTO updateStock(@PathVariable long productId, @PathVariable long stock) {

		productService.updateStock(productId, stock);

		return new ResponseDTO();
	}
	
	@RequestMapping(value="/count",method = RequestMethod.GET)
    public int getProductCount() {
    	
    	return productService.getProductCount();
 
           
    }

}