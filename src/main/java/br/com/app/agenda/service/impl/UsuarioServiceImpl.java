package br.com.app.agenda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.app.agenda.persistence.entity.Usuario;
import br.com.app.agenda.persistence.repository.UsuarioRepository;
import br.com.app.agenda.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
    Optional<Usuario> optUsuario = repository.findByUsuario(usuario);

    if (optUsuario.isEmpty()) {
      throw new UsernameNotFoundException("Usuario não encontrado");
    }

    Usuario user = optUsuario.get();

    return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
  }

  @Override
  public Usuario salvar(Usuario usuario) {
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    return repository.save(usuario);
  }

  @Override
  public List<Usuario> listarTodos() {
    return repository.findAll();
  }

}
