package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Payment {
	
	private String card;
	private Integer Amount;
	private String name;
	@Id
	private String card_num;
	private String exp_date;
	private Integer cvv;
	private String pnr;
	private String foodamount;
	
	private String pnrverified;
	
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public Integer getAmount() {
		return Amount;
	}
	public void setAmount(Integer amount) {
		Amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public String getFoodamount() {
		return foodamount;
	}
	public void setFoodamount(String foodamount) {
		this.foodamount = foodamount;
	}
	public String getPnrverified() {
		return pnrverified;
	}
	public void setPnrverified(String pnrverified) {
		this.pnrverified = pnrverified;
	}
}