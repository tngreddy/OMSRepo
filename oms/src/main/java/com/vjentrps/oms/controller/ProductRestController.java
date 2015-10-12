package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.service.ProductService;

@RestController
@RequestMapping(value = "/service/product")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getProducts() {

		List<Product> products = new ArrayList<Product>();

		products = productService.getAllproducts();
		return products;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addproduct(Product product) {

		product.setProductName("Wireless123");
		product.setUnitBasicRate(5);
		product.setUnitOfMeasure(8);
		//product.setCategoryId(2);
		product.setStock(50);
		productService.addProduct(product);
		return "Success";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateProduct(Product product) {

		productService.updateProduct(product);
		return "Success";
	}

	@RequestMapping(value = "/{productid}", method = RequestMethod.DELETE)
	public String deleteProduct(@PathVariable int productId) {

		productService.deleteProduct(productId);

		return "Success";
	}
	
	
	@RequestMapping(value = "/{productid}/stock/{stock}", method = RequestMethod.DELETE)
	public String updateStock(@PathVariable long productId, @PathVariable long stock) {

		productService.updateStock(productId, stock);

		return "Success";
	}

}