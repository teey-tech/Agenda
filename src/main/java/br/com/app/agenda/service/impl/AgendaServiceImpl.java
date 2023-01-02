package br.com.app.agenda.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.app.agenda.exception.BusinessException;
import br.com.app.agenda.persistence.entity.Agenda;
import br.com.app.agenda.persistence.entity.Paciente;
import br.com.app.agenda.persistence.repository.AgendaRepository;
import br.com.app.agenda.service.AgendaService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {

  private final AgendaRepository repository;
  private final PacienteServiceImpl pacienteService;

  @Override
  public Agenda salvar(Agenda agenda) {
    Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());
    Optional<Agenda> findByHorario = repository.findByHorario(agenda.getHorario());

    if (optPaciente.isEmpty()) {
      throw new BusinessException("Paciente não encontrado");
    }

    if (findByHorario.isPresent()) {
      throw new BusinessException("Ja existe agendamento para este horario");
    }
    agenda.setPaciente(optPaciente.get());
    agenda.setDataCriacao(LocalDateTime.now());
    return repository.save(agenda);
  }

  @Override
  public List<Agenda> listarTodos() {
    return repository.findAll();
  }

  @Override
  public Optional<Agenda> buscarPorId(Long id) {
    return repository.findById(id);
  }

  @Override
  public Agenda alterar(Long id, Agenda agenda) {
    Optional<Agenda> buscarPorId = this.buscarPorId(id);
    if (buscarPorId.isEmpty()) {
      throw new BusinessException("Agenda não cadastrada");
    }
    agenda.setId(id);
    return salvar(agenda);
  }

  @Override
  public void deletar(Long id) {
    repository.deleteById(id);
  }
}
