package br.com.goncalvesaco.ecommerce.infra.persistence.repositories;

import br.com.goncalvesaco.ecommerce.infra.persistence.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
