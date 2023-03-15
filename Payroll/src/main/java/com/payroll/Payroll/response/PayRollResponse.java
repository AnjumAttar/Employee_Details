package com.payroll.Payroll.response;

public class PayRollResponse {
	
	private int	 payRollid; 
	private int	 employee_id;
	private String registeredBank;
	private int	 bankAccountNumber;  
	private double	 monthlySalary;


	public PayRollResponse() {
		
	}


	public PayRollResponse(int payRollid, int employee_id, String registeredBank, int bankAccountNumber, int monthlySalary) {
		super();
		this.payRollid = payRollid;
		this.employee_id = employee_id;
		this.registeredBank = registeredBank;
		this.bankAccountNumber = bankAccountNumber;
		this.monthlySalary = monthlySalary;
	}


	public int getPayRollid() {
		return payRollid;
	}


	public void setPayRollid(int payRollid) {
		this.payRollid = payRollid;
	}





	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}


	public String getRegisteredBank() {
		return registeredBank;
	}


	public void setRegisteredBank(String registeredBank) {
		this.registeredBank = registeredBank;
	}


	public int getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	public double getMonthlySalary() {
		return monthlySalary;
	}


	public void setMonthlySalary(double monthlySalary2) {
		this.monthlySalary = monthlySalary2;
	}


	@Override
	public String toString() {
		return "Payroll [payRollid=" + payRollid + ", employeeId=" + employee_id + ", registeredBank=" + registeredBank
				+ ", bankAccountNumber=" + bankAccountNumber + ", monthlySalary=" + monthlySalary + "]";
	}





}
