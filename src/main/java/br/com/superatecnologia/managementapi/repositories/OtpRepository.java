package br.com.superatecnologia.managementapi.repositories;


import br.com.superatecnologia.managementapi.entities.OtpEntity;
import org.springframework.data.repository.CrudRepository;

public interface OtpRepository extends CrudRepository<OtpEntity, Long> {

    OtpEntity findByEmail(String email);
}