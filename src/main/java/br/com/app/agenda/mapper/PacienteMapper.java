package br.com.app.agenda.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.app.agenda.model.request.PacienteRequest;
import br.com.app.agenda.model.response.PacienteResponse;
import br.com.app.agenda.persistence.entity.Paciente;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

  private final ModelMapper mapper;

  public Paciente toPaciente(PacienteRequest request) {
    return mapper.map(request, Paciente.class);
  }

  public PacienteResponse toPatienteResponse(Paciente request) {
    return mapper.map(request, PacienteResponse.class);
  }

  public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
    return pacientes.stream()
        .map(this::toPatienteResponse)
        .collect(Collectors.toList());
  }

}
