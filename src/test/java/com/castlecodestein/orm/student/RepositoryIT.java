package com.castlecodestein.orm.student;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.castlecodestein.orm.SpringConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RepositoryIT {

	@Inject
	StudentRepository repository;
	
	@Test
	public void entitySave() throws Exception {
		Student entity = Student.of("John", "Lennon");
		
		repository.save(entity);
		
		Student retrieved = repository.findOne(1l);
		
		assertNotNull(retrieved.getId());
		assertEquals("John", retrieved.getFirstName());
		assertEquals("Lennon", retrieved.getLastName());
		assertEquals(1, repository.findAll().size());
	}
	
	@Test
	public void multiEntitySave() throws Exception {
		Student firstEntity = Student.of("John", "Lennon");
		repository.save(firstEntity);
		
		Student secondEntity = Student.of("Ringo", "Starr");
		repository.save(secondEntity);

		Collection<Student> students = repository.findAll();
		
		assertEquals(2, students.size());
	}
	
}
