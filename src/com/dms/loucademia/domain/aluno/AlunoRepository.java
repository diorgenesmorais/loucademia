package com.dms.loucademia.domain.aluno;

import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dms.loucademia.application.util.StringUtils;

@Stateless
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;

	public void store(Aluno t) {
		em.persist(t);
	}

	/**
	 * Find by matricula
	 * 
	 * @param id é a representação de matricula no modelo.
	 * @return um {@code Aluno}
	 */
	public Aluno findById(String id) {
		return em.find(Aluno.class, id);
	}

	public Aluno findByRG(Integer rg) {
		return em.createQuery("select a from Aluno where a.rg = :rg", Aluno.class)
				.setParameter("rg", rg)
				.getSingleResult();
	}

	public Aluno update(Aluno t) {
		return em.merge(t);
	}

	public void delete(String id) {
		Aluno aluno = findById(id);

		if (aluno != null) {
			em.remove(aluno);
		}
	}

	public String getMaxMatriculaAno() {
		return em.createQuery("select max(a.matricula) from Aluno a where a.matricula like :ano", String.class)
				.setParameter("ano", Year.now() + "%").getSingleResult();
	}

	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone) {
		StringBuilder jpql = new StringBuilder("select a from Aluno a where ");

		if (!StringUtils.isEmpty(matricula)) {
			jpql.append("a.matricula = :matricula and ");
		}

		if (!StringUtils.isEmpty(nome)) {
			jpql.append("a.nome like :nome and ");
		}

		if (rg != null) {
			jpql.append("a.rg = :rg and ");
		}

		if (telefone != null) {
			jpql.append("(a.telefone.numeroCelular like :celular or a.telefone.numeroFixo like :fixo) and ");
		}

		jpql.append("1 = 1");
		TypedQuery<Aluno> query = em.createQuery(jpql.toString(), Aluno.class);

		if (!StringUtils.isEmpty(matricula)) {
			query.setParameter("matricula", matricula);
		}

		if (!StringUtils.isEmpty(nome)) {
			query.setParameter("nome", String.format("%%%s%%", nome));
		}

		if (rg != null) {
			query.setParameter("rg", rg);
		}

		if (telefone != null) {
			query.setParameter("celular", telefone);
			query.setParameter("fixo", telefone);
		}
		return query.getResultList();
	}
}
