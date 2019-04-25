package com.dms.loucademia.interfaces.relatorio.web;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.dms.loucademia.domain.acesso.Acesso;

@Named("resb")
@RequestScoped
public class RelatorioEntradaSaidaBean {

	private String matricula;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	private List<Acesso> acessos;

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
		
		return null;
	}
}
