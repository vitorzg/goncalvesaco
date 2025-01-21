package br.com.goncalvesaco.ecommerce.infra.persistence.repositories;

import br.com.goncalvesaco.ecommerce.infra.persistence.models.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<ProductEntity, String> {
}
