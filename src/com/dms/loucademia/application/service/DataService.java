package com.dms.loucademia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dms.loucademia.domain.aluno.Estado;
import com.dms.loucademia.domain.aluno.EstadoRepository;
import com.dms.loucademia.domain.aluno.Aluno.Sexo;
import com.dms.loucademia.domain.aluno.Aluno.Situacao;

@Stateless
public class DataService {

	@EJB
	private EstadoRepository estadoRepository;

	public List<Estado> listarEstados() {
		return estadoRepository.listarEstados();
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}
}
