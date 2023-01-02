package br.com.app.agenda.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.agenda.persistence.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

  Optional<Paciente> findByCpf(String cpf);

  Optional<Paciente> findByEmail(String email);
}
