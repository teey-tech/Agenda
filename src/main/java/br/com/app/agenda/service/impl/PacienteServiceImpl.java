package br.com.app.agenda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.agenda.exception.BusinessException;
import br.com.app.agenda.persistence.entity.Paciente;
import br.com.app.agenda.persistence.repository.PacienteRepository;
import br.com.app.agenda.service.PacienteService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

  private final PacienteRepository repository;

  @Override
  public Paciente salvar(Paciente paciente) {
    boolean existeCPF = false;
    boolean existeEmail = false;

    Optional<Paciente> findByCpf = repository.findByCpf(paciente.getCpf());
    Optional<Paciente> findByEmail = repository.findByEmail(paciente.getEmail());

    if (findByCpf.isPresent()) {
      if (!findByCpf.get().getId().equals(paciente.getId())) {
        existeCPF = true;
      }
    }

    if (existeCPF) {
      throw new BusinessException("CPF JÁ EXISTE");
    }

    if (findByEmail.isPresent()) {
      if (!findByEmail.get().getId().equals(paciente.getId())) {
        existeEmail = true;
      }
    }

    if (existeEmail) {
      throw new BusinessException("Email Já cadastrado.");
    }

    return repository.save(paciente);
  }

  @Override
  public List<Paciente> listarTodos() {
    return repository.findAll();
  }

  @Override
  public Optional<Paciente> buscarPorId(Long id) {
    return repository.findById(id);
  }

  @Override
  public Paciente alterar(Long id, Paciente paciente) {
    Optional<Paciente> buscarPorId = this.buscarPorId(id);
    if (buscarPorId.isEmpty()) {
      throw new BusinessException("Paciente não Cadastrado");
    }
    paciente.setId(id);
    return salvar(paciente);
  }

  @Override
  public void deletar(Long id) {
    repository.deleteById(id);
  }

}
