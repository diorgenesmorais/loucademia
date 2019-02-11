package com.dms.loucademia.interfaces.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

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
}
