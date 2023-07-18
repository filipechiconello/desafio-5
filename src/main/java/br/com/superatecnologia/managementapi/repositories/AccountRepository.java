package br.com.superatecnologia.managementapi.repositories;

import br.com.superatecnologia.managementapi.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByEmail(String email);

    AccountEntity findByName(String name);
}