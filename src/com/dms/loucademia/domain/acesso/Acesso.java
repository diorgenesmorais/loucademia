package com.dms.loucademia.domain.acesso;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dms.loucademia.application.util.Validation;
import com.dms.loucademia.domain.aluno.Aluno;

@Entity
@Table(name = "entradas_saidas")
public class Acesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;
	@Column(nullable = false)
	private LocalDateTime entrada;
	private LocalDateTime saida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
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
		Acesso other = (Acesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isEntradaSaidaPreenchidas() {
		return (this.entrada != null && this.saida != null);
	}

	public TipoAcesso registrarAcesso() {
		TipoAcesso tipoAcesso = null;

		if (this.entrada == null) {
			this.entrada = LocalDateTime.now();
			tipoAcesso = TipoAcesso.ENTRADA;
		} else if (this.saida == null) {
			this.saida = LocalDateTime.now();
			tipoAcesso = TipoAcesso.SAIDA;
		}

		Validation.assertNotEmpty(tipoAcesso);
		return tipoAcesso;
	}

	public String getCalculoDeDuracao() {
		if (entrada == null || saida == null) {
			return null;
		}

		Duration duration = Duration.between(entrada, saida);

		return String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
	}
}
