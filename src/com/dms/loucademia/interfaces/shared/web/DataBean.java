package com.dms.loucademia.interfaces.shared.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.dms.loucademia.application.service.DataService;
import com.dms.loucademia.domain.aluno.Aluno.Sexo;
import com.dms.loucademia.domain.aluno.Aluno.Situacao;
import com.dms.loucademia.domain.aluno.Estado;

@Named
@ApplicationScoped
public class DataBean {

	@EJB
	private DataService dataService;

	public Sexo[] getSexos() {
		return dataService.getSexos();
	}

	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}

	public List<Estado> getEstados() {
		return dataService.listarEstados();
	}

	public String formatTelefone(Integer ddd, Integer numero) {
		return (ddd == null || numero == null) ? "" : String.format("(%d) %d", ddd, numero);
	}
}
