package com.dms.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dms.loucademia.application.service.AcessoService;
import com.dms.loucademia.application.util.StringUtils;
import com.dms.loucademia.application.util.ValidationException;
import com.dms.loucademia.domain.acesso.Acesso;

@Named("resb")
@RequestScoped
public class RelatorioEntradaSaidaBean implements Serializable {

	private static final long serialVersionUID = 3384719760937513685L;
	private String matricula;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	private List<Acesso> acessos;
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

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public String gerarRelatorio() {
		try {
			this.acessos = acessoService.listAcessosAlunos(matricula, dataInicial, dataFinal);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public void carregarRelatorio() {
		if(!StringUtils.isEmpty(this.matricula)) {
			gerarRelatorio();
		}
	}
}
