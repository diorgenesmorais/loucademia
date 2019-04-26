package com.dms.loucademia.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dms.loucademia.application.util.StringUtils;
import com.dms.loucademia.application.util.ValidationException;
import com.dms.loucademia.domain.acesso.Acesso;
import com.dms.loucademia.domain.acesso.AcessoRepository;
import com.dms.loucademia.domain.acesso.TipoAcesso;
import com.dms.loucademia.domain.aluno.Aluno;
import com.dms.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	@EJB
	private AlunoRepository alunoRepository;

	public TipoAcesso registrarAcesso(String matricula, Integer rg) {
		if (StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("É preciso fornecer a matricula ou o RG do aluno");
		}

		Aluno aluno;
		if (StringUtils.isEmpty(matricula)) {
			aluno = this.alunoRepository.findByRG(rg);
		} else {
			aluno = this.alunoRepository.findById(matricula);
		}

		if (aluno == null) {
			throw new ValidationException("O aluno não foi encontrado");
		}

		Acesso acesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		if(acesso == null || acesso.isEntradaSaidaPreenchidas()) {
			acesso = new Acesso();
			acesso.setAluno(aluno);
			tipoAcesso = acesso.registrarAcesso();
			acessoRepository.store(acesso);
		} else {
			tipoAcesso = acesso.registrarAcesso();
		}

		return tipoAcesso;
	}

	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		if (StringUtils.isEmpty(matricula) && dataInicial == null && dataFinal == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido");
		}

		return acessoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}
}
