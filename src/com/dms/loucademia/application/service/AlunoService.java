package com.dms.loucademia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dms.loucademia.application.util.StringUtils;
import com.dms.loucademia.application.util.Validation;
import com.dms.loucademia.application.util.ValidationException;
import com.dms.loucademia.domain.aluno.Aluno;
import com.dms.loucademia.domain.aluno.Aluno.Situacao;
import com.dms.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;

	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		} else {
			update(aluno);
		}
	}

	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}

	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		alunoRepository.update(aluno);
	}

	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findById(matricula);
	}

	public List<Aluno> listAluno(String matricula, String nome, Integer rg, Integer telefone) {
		if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido");
		}

		return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	}

	public void delete(String matricula) {
		this.alunoRepository.delete(matricula);
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao){
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacoesAlunos(situcao);
	}
}
