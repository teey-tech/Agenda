package br.com.app.agenda.model.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

  @NotBlank(message = "Descrição é obrigatorio")
  private String descricao;

  @NotNull
  @Future
  @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
  private LocalDateTime horario;

  @NotNull
  private Long pacienteId;
}
