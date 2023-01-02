package br.com.app.agenda.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.app.agenda.model.request.AgendaRequest;
import br.com.app.agenda.model.response.AgendaResponse;
import br.com.app.agenda.persistence.entity.Agenda;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendaMapper {
  private final ModelMapper mapper;

  public Agenda toAgenda(AgendaRequest request) {
    return mapper.map(request, Agenda.class);
  }

  public AgendaResponse toAgendaResponse(Agenda request) {
    return mapper.map(request, AgendaResponse.class);
  }

  public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendas) {
    return agendas.stream()
        .map(this::toAgendaResponse)
        .collect(Collectors.toList());
  }
}
