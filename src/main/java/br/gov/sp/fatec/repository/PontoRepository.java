package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Ponto;
import br.gov.sp.fatec.model.Usuario;

public interface PontoRepository extends CrudRepository<Ponto, Long> {
	
	public List<Ponto> findByUsuarioNome(String nome);
	
	public List<Ponto> findByUsuario(Usuario usuario);

	
}
