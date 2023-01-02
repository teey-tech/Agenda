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

import br.com.app.agenda.mapper.PacienteMapper;
import br.com.app.agenda.model.request.PacienteRequest;
import br.com.app.agenda.model.response.PacienteResponse;
import br.com.app.agenda.persistence.entity.Paciente;
import br.com.app.agenda.service.impl.PacienteServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/paciente")
@CrossOrigin(allowedHeaders = "*", maxAge = 3500)
@AllArgsConstructor
public class PacienteController {

  private final PacienteServiceImpl service;
  private final PacienteMapper mapper;

  @PostMapping
  public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest pacienteRequest) {
    Paciente paciente = mapper.toPaciente(pacienteRequest);
    Paciente pacienteSalvo = service.salvar(paciente);
    PacienteResponse patienteResponse = mapper.toPatienteResponse(pacienteSalvo);
    return ResponseEntity.status(HttpStatus.CREATED).body(patienteResponse);
  }

  @GetMapping
  public ResponseEntity<List<PacienteResponse>> procurarTodos() {
    List<Paciente> listarTodos = service.listarTodos();
    List<PacienteResponse> pacienteResponses = mapper.toPacienteResponseList(listarTodos);
    return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarPorId(@PathVariable("id") Long id) {
    Optional<Paciente> buscarPorId = service.buscarPorId(id);
    if (buscarPorId.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id não foi encontrado");
    }
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toPatienteResponse(buscarPorId.get()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PacienteResponse> alterar(@Valid @PathVariable("id") Long id,
      @RequestBody PacienteRequest request) {
    Paciente paciente = mapper.toPaciente(request);
    Paciente pacienteSalvo = service.alterar(id, paciente);
    PacienteResponse response = mapper.toPatienteResponse(pacienteSalvo);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    service.deletar(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
