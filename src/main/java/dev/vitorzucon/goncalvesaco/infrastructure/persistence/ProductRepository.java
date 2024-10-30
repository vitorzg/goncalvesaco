package dev.vitorzucon.goncalvesaco.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
