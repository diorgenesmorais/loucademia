package com.dms.loucademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dms.loucademia.application.service.AcessoService;
import com.dms.loucademia.application.util.ValidationException;
import com.dms.loucademia.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	private static final long serialVersionUID = -1272181877989468837L;
	private String matricula;
	private Integer rg;
	@EJB
	private AcessoService acessoService;
	@Inject
	private FacesContext facesContext;

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
		TipoAcesso tipoAcesso;
		try {
			tipoAcesso = this.acessoService.registrarAcesso(matricula, rg);
		} catch (ValidationException e) {
			this.facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}

		String message = tipoAcesso.obterMensagem();

		this.facesContext.addMessage(null, new FacesMessage(message));

		return null;
	}
}
