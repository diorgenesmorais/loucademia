package com.dms.loucademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	private static final long serialVersionUID = -1272181877989468837L;
	private String matricula;
	private Integer rg;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String registrar() {
		return null;
	}
}
