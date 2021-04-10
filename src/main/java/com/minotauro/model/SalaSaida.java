package com.minotauro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SalaSaida {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_sala", nullable = false)
	private Sala sala;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_saida", nullable = false)
	private Saida saida;

	public SalaSaida(long id, long id_saida, Long id_sala) {
		this.id = id;
		this.sala = new Sala(id_sala);
		this.saida = new Saida(id_saida);
	}

	public SalaSaida() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
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
		SalaSaida other = (SalaSaida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
