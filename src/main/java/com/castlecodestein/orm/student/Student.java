package com.castlecodestein.orm.student;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Students")
public class Student {

	@Id
	@Column(name = "s_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_PK_SEQ")
	@SequenceGenerator(name = "STUDENT_PK_SEQ", sequenceName = "STUDENT_PK_SEQ", allocationSize = 1)
	private Long id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created")
	private Date created;

	/* Constructor */
	
	/**
	 * For hibernate only.
	 */
	@Deprecated
	Student() {
	}
	
	Student(Long id, String firstName, String lastName, Date created) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.created = created;
	}

	/* Factory */
	
	public static Student of(String firstName, String lastName) {
		return new Student(null, firstName, lastName, new Date());
	}
	
	/* Read */

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getCreated() {
		return created;
	}
	
}
