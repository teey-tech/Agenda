package br.com.app.agenda.service;

import java.util.List;
import java.util.Optional;

import br.com.app.agenda.persistence.entity.Paciente;

public interface PacienteService {

  Paciente salvar(Paciente paciente);

  List<Paciente> listarTodos();

  Optional<Paciente> buscarPorId(Long id);

  Paciente alterar(Long id, Paciente paciente);

  void deletar(Long id);
}
