package br.com.app.agenda.service;

import java.util.List;
import java.util.Optional;

import br.com.app.agenda.persistence.entity.Agenda;

public interface AgendaService {

  Agenda salvar(Agenda agenda);

  List<Agenda> listarTodos();

  Optional<Agenda> buscarPorId(Long id);

  Agenda alterar(Long id, Agenda agenda);

  void deletar(Long id);
}
