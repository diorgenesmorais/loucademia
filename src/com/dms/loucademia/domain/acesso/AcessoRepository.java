package com.dms.loucademia.domain.acesso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dms.loucademia.application.util.StringUtils;
import com.dms.loucademia.domain.aluno.Aluno;

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Acesso findUltimoAcesso(Aluno aluno) {
		try {
			return em.createQuery("select a from Acesso a where a.aluno.matricula = :matricula order by a.id desc", Acesso.class)
					.setParameter("matricula", aluno.getMatricula())
					.setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void store(Acesso acesso) {
		em.persist(acesso);
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		StringBuilder jpql = new StringBuilder("select a from Acesso a where ");
		
		if(!StringUtils.isEmpty(matricula)) {
			jpql.append("a.aluno.matricula = :matricula and ");
		}
		
		if(dataInicial != null) {
			jpql.append("a.entrada >= :entradaInicio and ");
		}
		
		if(dataFinal != null) {
			jpql.append("a.saida <= :saidaFim and ");
		}
		
		jpql.append("1 = 1 order by a.entrada");
		
		TypedQuery<Acesso> query = em.createQuery(jpql.toString(), Acesso.class);
		
		if(!StringUtils.isEmpty(matricula)) {
			query.setParameter("matricula", matricula);
		}

		if(dataInicial != null) {
			LocalDateTime entradaInicio = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
			query.setParameter("entradaInicio", entradaInicio);
		}

		if(dataFinal != null) {
			LocalDateTime saidaFim = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			query.setParameter("saidaFim", saidaFim);
		}

		return query.getResultList();
	}
}
