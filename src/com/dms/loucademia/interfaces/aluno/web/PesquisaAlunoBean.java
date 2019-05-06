package com.dms.loucademia.interfaces.aluno.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dms.loucademia.application.service.AlunoService;
import com.dms.loucademia.application.util.ValidationException;
import com.dms.loucademia.domain.aluno.Aluno;

@Named
@SessionScoped
public class PesquisaAlunoBean implements Serializable {

	private static final long serialVersionUID = -9152995216634301135L;

	@EJB
	private AlunoService alunoService;

	@Inject
	private FacesContext facesContext;

	private String matricula;
	private String nome;
	private Integer rg;
	private Integer telefone;
	private List<Aluno> alunos;

	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String pesquisar() {
		try {
			alunos = alunoService.listAluno(this.matricula, this.nome, this.rg, this.telefone);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String excluir(String matricula) {
		alunoService.delete(matricula);
		return pesquisar();
	}

	public void check() {
		String clear = requestParamsMap.get("clear");

		if (clear != null && Boolean.valueOf(clear)) {
			this.matricula = null;
			this.nome = null;
			this.rg = null;
			this.telefone = null;
			this.alunos = null;
		}
	}
}
