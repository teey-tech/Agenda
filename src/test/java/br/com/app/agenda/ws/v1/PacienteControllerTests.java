package br.com.app.agenda.ws.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.app.agenda.model.request.PacienteRequest;
import br.com.app.agenda.persistence.entity.Paciente;
import br.com.app.agenda.persistence.repository.PacienteRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteControllerTests {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  PacienteRepository repository;

  @BeforeEach
  void up() {
    Paciente paciente = new Paciente();
    paciente.setNome("Maria");
    paciente.setSobrenome("Leopoldina");
    paciente.setCpf("123");
    paciente.setEmail("maria@email.com");
    repository.save(paciente);

  }

  @AfterEach
  void down() {
    repository.deleteAll();
  }

  @Test
  @DisplayName("listar todos os pacientes")
  void listarPacientes() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/paciente"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

}
