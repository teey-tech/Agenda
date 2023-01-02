package br.com.app.agenda.ws.controller.v1;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.agenda.mapper.AgendaMapper;
import br.com.app.agenda.model.request.AgendaRequest;
import br.com.app.agenda.model.response.AgendaResponse;
import br.com.app.agenda.persistence.entity.Agenda;
import br.com.app.agenda.service.AgendaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/agenda")
@CrossOrigin(allowedHeaders = "*", maxAge = 3500)
@AllArgsConstructor
public class AgendaController {

  private final AgendaService service;
  private final AgendaMapper mapper;

  @PostMapping
  public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest request) {
    Agenda agenda = mapper.toAgenda(request);
    Agenda agendaSalva = service.salvar(agenda);
    AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
    return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);

  }

  @GetMapping
  public ResponseEntity<List<AgendaResponse>> listarTodos() {
    List<Agenda> agendas = service.listarTodos();
    List<AgendaResponse> agendaResponse = mapper.toAgendaResponseList(agendas);
    return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
    Optional<Agenda> buscarPorId = service.buscarPorId(id);

    if (buscarPorId.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id não foi encontrado");
    }

    AgendaResponse agendaResponse = mapper.toAgendaResponse(buscarPorId.get());
    return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AgendaResponse> alterar(@Valid @PathVariable("id") Long id,
      @RequestBody AgendaRequest request) {
    Agenda agenda = mapper.toAgenda(request);
    Agenda agendaSalva = service.alterar(id, agenda);
    AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
    return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    service.deletar(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
