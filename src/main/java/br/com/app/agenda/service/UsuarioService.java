package br.com.app.agenda.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.app.agenda.persistence.entity.Usuario;

public interface UsuarioService extends UserDetailsService {

  Usuario salvar(Usuario usuario);

  List<Usuario> listarTodos();

}
