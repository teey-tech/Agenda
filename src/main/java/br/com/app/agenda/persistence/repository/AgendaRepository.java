package br.com.app.agenda.persistence.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.agenda.persistence.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

  Optional<Agenda> findByHorario(LocalDateTime horario);

}
