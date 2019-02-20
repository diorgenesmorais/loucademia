package com.dms.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dms.loucademia.application.service.AlunoService;
import com.dms.loucademia.application.util.StringUtils;
import com.dms.loucademia.domain.aluno.Aluno;

@Named
@RequestScoped
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 958319261516788915L;

	@EJB
	private AlunoService alunoService;
	private Aluno aluno = new Aluno();
	private String matricula;
	private String titulo = "Novo aluno";
	@Inject
	private FacesContext facesContext;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String gravar() {
		alunoService.createOrUpdate(aluno);
		facesContext.addMessage(null, new FacesMessage("Dados gravados com sucesso!"));
		return null;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void carregar() {
		if(!StringUtils.isEmpty(matricula)) {
			this.aluno = this.alunoService.findByMatricula(matricula);
		}
		if(!StringUtils.isEmpty(this.aluno.getMatricula())) {
			this.titulo = "Alterar aluno";
		}
	}

	public String getTitulo() {
		return titulo;
	}
	
}
