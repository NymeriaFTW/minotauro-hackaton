package com.minotauro.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	private boolean chegada;
	
	@Transient
	private String visaoAtual;
	
	@Transient
	private List<String> saidas;
	
	@Transient
	private Long tamanho;
	
	public Sala(long numero, String nome) {
		this.id = numero;
		this.nome = nome;
		this.chegada = false;
	}

	public Sala() {
		super();
	}

	public Sala(Long id_sala) {
		this.id = id_sala;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isChegada() {
		return chegada;
	}
	
	public void setChegada(boolean chegada) {
		this.chegada = chegada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getVisaoAtual() {
		return visaoAtual;
	}

	public void setVisaoAtual(String visaoAtual) {
		this.visaoAtual = visaoAtual;
	}

	public List<String> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<String> saidas) {
		this.saidas = saidas;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}
	
}
