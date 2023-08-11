package com.mul.db.forDatabase2.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder 
public class User {

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String firstname;
	String lastname;
	
}
