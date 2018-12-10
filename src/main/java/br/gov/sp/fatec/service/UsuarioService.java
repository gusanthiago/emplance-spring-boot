package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Usuario;

public interface UsuarioService {
	
	public Usuario buscar(Long id);
	
	public List<Usuario> todos();
	
	public Usuario salvar(Usuario usuario);

}
