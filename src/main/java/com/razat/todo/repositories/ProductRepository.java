package com.razat.todo.repositories;

import com.razat.todo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //query methods using @Query and JPQL language.
    @Query(value="SELECT p FROM Product p WHERE p.name= :name")
    public List<Product> findByName(@Param("name") String name);

    @Modifying
    @Query(value="UPDATE Product p SET p.price= :price WHERE p.id=:id")
    public void updateProductById(@Param("id")Long id, @Param("price")Double price);
}
