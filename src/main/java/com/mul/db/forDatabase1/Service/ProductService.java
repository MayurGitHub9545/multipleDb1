package com.mul.db.forDatabase1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mul.db.forDatabase1.Repo.ProductRepository;
import com.mul.db.forDatabase1.entities.Product;

@Service
public class ProductService {

	@Autowired
	public ProductRepository repository;

	// save single product
	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	// save multiple product
	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}

	// get multiple product
	public List<Product> getProducts() {
		return repository.findAll();
	}

	// get single product by its id
	public Product getProductById(int pid) {
		return repository.findById(pid).orElse(null);
	}

	// delete single product
	public String deleteProduct(int pid) {
		repository.deleteById(pid);
		return "product removed !! " + pid;
	}

	// update single product
	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());

		return repository.save(existingProduct);
	}

}
