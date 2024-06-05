package model.entities;

public class Account {
	private Integer number;
	private String name;
	private Double balance;
		
	public Account(Integer number, String name) {
		this.number = number;
		this.name = name;
		this.balance = 0.0;
	}

	public Account(Integer number, String name, Double balance) {
		this.number = number;
		this.name = name;
		this.balance = balance;
	}

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void withdraw(Double balance) {
		this.balance -= balance + 5;
	}
	
	public void deposit(Double balance) {
		this.balance += balance;
	}
	
	@Override
	public String toString() {
		return "Accout: " + getNumber() +
				", Holder: " + getName() + 
				", Balance: " + String.format("%.2f", getBalance());
	}
}
