package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Ponto;
import br.gov.sp.fatec.model.Usuario;

public interface PontoService {
	
	public Ponto salvar(Ponto ponto);
	
	public List<Ponto> todos();
	
	public List<Ponto> buscarPorUsuario(Usuario usuario);
	
	public Ponto buscarPorId(Long idPonto);

	List<Ponto> buscarPorIdUsuario(Long idUsuario);

}
