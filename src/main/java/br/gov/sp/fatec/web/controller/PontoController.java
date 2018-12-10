package br.gov.sp.fatec.web.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.model.Ponto;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.service.PontoService;
import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping(value = "/workhour")
@CrossOrigin
public class PontoController {
	
	@Autowired
	private PontoService pontoService;

	public void setPontoService(PontoService pontoService) {
		this.pontoService = pontoService;
	}

	@RequestMapping(value = "/getAllByUser", method = RequestMethod.GET)
	@JsonView(View.Ponto.class)
	public ResponseEntity<Collection<Ponto>> getAllByUser(@RequestParam(value="id", defaultValue="1") Long id) {		
		return new ResponseEntity<Collection<Ponto>>(pontoService.buscarPorIdUsuario(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Ponto.class)
	public ResponseEntity<Collection<Ponto>> getAll(ServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String authorization = servletRequest.getHeader("Authorization");
		Usuario usuario = JwtUtils.parseToken(authorization.replaceAll("Bearer ", ""));
		return new ResponseEntity<Collection<Ponto>>(pontoService.buscarPorUsuario(usuario), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Ponto.class)
	public ResponseEntity<Ponto> salvar(@RequestBody Ponto ponto, UriComponentsBuilder uriComponentsBuilder) {
		ponto = pontoService.salvar(ponto);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + ponto.getId()).build().toUri());
		return new ResponseEntity<Ponto>(ponto, responseHeaders, HttpStatus.CREATED);
	}
	
}
