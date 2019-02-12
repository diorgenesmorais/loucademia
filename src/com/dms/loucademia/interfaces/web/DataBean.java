package com.dms.loucademia.interfaces.web;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.dms.loucademia.domain.aluno.Estado;
import com.dms.loucademia.domain.aluno.Aluno.Sexo;
import com.dms.loucademia.domain.aluno.Aluno.Situacao;

@Named
@ApplicationScoped
public class DataBean {

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}
	
	public List<Estado> getEstados(){
		Estado estado = new Estado();
		estado.setSigla("PE");
		estado.setNome("Pernambuco");
		return Arrays.asList(estado);
	}
}
