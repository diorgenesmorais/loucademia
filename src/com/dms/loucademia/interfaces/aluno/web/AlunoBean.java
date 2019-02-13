package com.dms.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.dms.loucademia.domain.aluno.Aluno;

@Named
@RequestScoped
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 958319261516788915L;
	private Aluno aluno = new Aluno();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String gravar() {
		System.err.println("Aluno >>> " + aluno);
		return null;
	}
}
