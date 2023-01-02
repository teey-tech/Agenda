package br.com.app.agenda;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.app.agenda.exception.BusinessException;
import br.com.app.agenda.persistence.entity.Agenda;
import br.com.app.agenda.persistence.entity.Paciente;
import br.com.app.agenda.persistence.repository.AgendaRepository;
import br.com.app.agenda.service.impl.AgendaServiceImpl;
import br.com.app.agenda.service.impl.PacienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceImplTests {

  @InjectMocks
  AgendaServiceImpl service;

  @Mock
  PacienteServiceImpl pacienteService;

  @Mock
  AgendaRepository repository;

  @Captor
  ArgumentCaptor<Agenda> agendaCaptor;

  @Test
  @DisplayName("deve salvar agendamento com sucesso")
  void salvarComSucesso() {
    // setup
    // arrange
    LocalDateTime now = LocalDateTime.now();
    Agenda agenda = new Agenda();
    agenda.setDescricao("Descrição do agendamento");
    agenda.setHorario(now);

    Paciente paciente = new Paciente();
    paciente.setId(1L);
    paciente.setNome("Paciente");

    agenda.setPaciente(paciente);

    Mockito.when(pacienteService.buscarPorId(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
    Mockito.when(repository.findByHorario(now)).thenReturn(Optional.empty());
    // action
    service.salvar(agenda);

    // asserctions
    Mockito.verify(pacienteService).buscarPorId(agenda.getPaciente().getId());
    Mockito.verify(repository).findByHorario(now);
    Mockito.verify(repository).save(agendaCaptor.capture());

    Agenda agendaSalva = agendaCaptor.getValue();

    Assertions.assertThat(agendaSalva.getPaciente()).isNotNull();
    Assertions.assertThat(agendaSalva.getDataCriacao()).isNotNull();

  }

  @Test
  @DisplayName("Não deve salvar agendamento sem paciente")
  void salvarErroPacienteNaoEncontrado() {
    LocalDateTime now = LocalDateTime.now();
    Agenda agenda = new Agenda();
    agenda.setDescricao("Descrição do agendamento");
    agenda.setHorario(now);

    Paciente paciente = new Paciente();
    paciente.setId(1L);
    paciente.setNome("Paciente");

    agenda.setPaciente(paciente);

    Mockito.when(pacienteService.buscarPorId(ArgumentMatchers.any())).thenReturn(Optional.empty());

    // action
    BusinessException businessException = assertThrows(BusinessException.class, () -> {
      service.salvar(agenda);
    });

    Assertions.assertThat(businessException.getMessage()).isEqualTo("Paciente não encontrado");
  }
}
