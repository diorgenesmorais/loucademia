package com.dms.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.inject.Named;

import com.dms.loucademia.application.service.AlunoService;
import com.dms.loucademia.domain.aluno.Aluno;
import com.dms.loucademia.domain.aluno.Aluno.Situacao;

@Named
@SessionScoped
public class RelatorioSituacoesBean implements Serializable {

	private static final long serialVersionUID = 9019308605548516137L;

	private Situacao situacao;

	private List<Aluno> alunos;
	@EJB
	private AlunoService alunoService;

	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String gerarRelatorio() {
		this.alunos = alunoService.listSituacoesAlunos(situacao);
		return null;
	}

	public void check() {
		String clear = requestParamsMap.get("clear");

		if (clear != null && Boolean.valueOf(clear)) {
			this.situacao = null;
			this.alunos = null;
		}
	}
}
