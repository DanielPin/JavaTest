package br.com.teste.cd2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.cd2.model.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

}
