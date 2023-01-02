package com.example.demo.entity;



import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaymentSuccess {

	@Id
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email =email;
	}
}