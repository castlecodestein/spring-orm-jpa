package com.castlecodestein.orm.student;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentRepository {

	@PersistenceContext
	private EntityManager em;

	public Student findOne(long id) {
		return em.find(Student.class, id);
	}

	public Collection<Student> findByLastName(String lastName) {
		return em
				.createQuery("from Student where lastName = ?1", Student.class)
				.setParameter(1, lastName).getResultList();
	}
	
	public Collection<Student> findAll() {
		return em.createQuery("from Student", Student.class).getResultList();
	}

	@Transactional
	public Student save(Student student) {
		return em.merge(student);
	}

	@Transactional
	public void delete(Student student) {
		em.remove(student);
	}

}
