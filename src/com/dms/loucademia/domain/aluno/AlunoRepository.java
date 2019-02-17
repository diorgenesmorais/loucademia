package com.dms.loucademia.domain.aluno;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;

	public void store(Aluno t) {
		em.persist(t);
	}

	public Aluno findById(String id) {
		return em.find(Aluno.class, id);
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

}
