package com.dms.loucademia.interfaces.aluno.web;

import java.util.List;

import javax.ejb.EJB;

import com.dms.loucademia.application.service.AlunoService;
import com.dms.loucademia.domain.aluno.Aluno;

public class PesquisaAlunoBean {

	@EJB
	private AlunoService alunoService;

	private String matricula;
	private String nome;
	private Integer rg;
	private Integer telefone;
	private List<Aluno> alunos;

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
		this.alunos = alunoService.listAluno(matricula);
		return "";
	}
}
