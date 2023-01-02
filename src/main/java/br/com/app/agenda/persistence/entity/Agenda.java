package br.com.app.agenda.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "agenda")
@Getter
@Setter
public class Agenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "decricao")
  private String descricao;

  @Column(name = "data_hora")
  private LocalDateTime horario;

  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @ManyToOne
  @JoinColumn(name = "paciente_id")
  @JsonIgnoreProperties("agenda")
  private Paciente paciente;

}
