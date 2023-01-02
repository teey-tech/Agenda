package br.com.app.agenda.model.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

  @NotBlank(message = "Nome do Paciente é obrigatorio")
  private String nome;

  @NotBlank(message = "Sobrenome do Paciente é obrigatorio")
  private String sobrenome;

  private String email;

  @NotBlank(message = "CPF do Paciente é obrigatorio")
  private String cpf;
}
