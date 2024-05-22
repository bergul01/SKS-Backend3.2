package sks.project.sksbackend.repositoryDataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sks.project.sksbackend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	 @Query("SELECT p FROM Product p WHERE p.productName = :productName")
	Product findByProductName(String productName);
}
