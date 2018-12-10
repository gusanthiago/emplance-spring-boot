package br.gov.sp.fatec.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "PONTO")
public class Ponto implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PONTO_ID")
	@JsonView(View.Ponto.class)
	private Long id;
	  
    @Column(name = "PONTO_DATA_INICIO", nullable = false)
    @JsonView(View.Ponto.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dataInicio;
	  
    @Column(name = "PONTO_DATA_FIM", nullable = true)
    @JsonView(View.Ponto.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dataFim;
	  
    @Column(name = "PONTO_DATA_ATUALIZACAO", nullable = false)
    @JsonView(View.Ponto.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_CRIACAO_ID")
    @JsonView(View.Ponto.class)
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
    
}
