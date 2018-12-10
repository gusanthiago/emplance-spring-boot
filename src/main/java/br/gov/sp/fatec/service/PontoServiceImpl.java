package br.gov.sp.fatec.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Ponto;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.PontoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("anotacaoService")
@Transactional
public class PontoServiceImpl implements PontoService {

	@Autowired
	private PontoRepository pontoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void setPontoRepo(PontoRepository anotacaoRepo) {
		this.pontoRepo = anotacaoRepo;
	}
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
	public Ponto salvar(Ponto ponto) {
		if(ponto.getUsuario() != null) {
			Usuario usuario = ponto.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		ponto.setDataAtualizacao(new Date());
		return pontoRepo.save(ponto);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Ponto> todos() {
		List<Ponto> retorno = new ArrayList<Ponto>();
		for(Ponto ponto: pontoRepo.findAll()) {
			retorno.add(ponto);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Ponto> buscarPorUsuario(Usuario usuario) {
		List<Ponto> retorno = new ArrayList<Ponto>();
		for(Ponto ponto: pontoRepo.findByUsuario(usuario)) {
			retorno.add(ponto);
		}
		return retorno;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Ponto> buscarPorIdUsuario(Long idUsuario) {
		Usuario usuario = usuarioRepo.findById(idUsuario).get();
		List<Ponto> retorno = new ArrayList<Ponto>();
		for(Ponto ponto: pontoRepo.findByUsuario(usuario)) {
			retorno.add(ponto);
		}
		return retorno;
	}
	
	

	@Override
	@PreAuthorize("isAuthenticated()")
	public Ponto buscarPorId(Long idPonto) {
		Optional<Ponto> ponto = pontoRepo.findById(idPonto);
		if(ponto.isPresent()) {
			return ponto.get();
		}
		return null;
	}

}
