package com.mul.db.forDatabase1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.mul.db.forDatabase1.Service.ProductService;
import com.mul.db.forDatabase1.entities.Product;
 

@RestController
public class ProductController {

	@Autowired
	public ProductService service;
	
	
	 @PostMapping("/addproduct")
		public Product addProduct(@RequestBody Product con) {
			return service.saveProduct(con);
		}

		@PostMapping("/addcustomers")
		public List<Product> addProduct(@RequestBody List<Product> cons) {
			return service.saveProducts(cons);
		}

		@PutMapping("/update")
		public Product updateProduct(@RequestBody Product con) {
			return service.updateProduct(con);
		}

	/////////////////////////

		@GetMapping("/product")
		public List<Product> findAllProduct() {
			return service.getProducts();
		}

		@GetMapping("/productbyid/{pid}")
		public Product findProductById(@PathVariable int pid) {
			return service.getProductById(pid);
		}

		@DeleteMapping("/delete/{pid}")
		public String deleteProduct(@PathVariable int pid) {
			return service.deleteProduct(pid);
		}
}
