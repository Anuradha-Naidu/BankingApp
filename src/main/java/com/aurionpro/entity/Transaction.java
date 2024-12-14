package com.aurionpro.entity;

import java.sql.Timestamp;

public class Transaction {
   
        private int senderAccountNumber;
        
	    public int getSenderAccountNumber() {
			return senderAccountNumber;
		}

		public void setSenderAccountNumber(int senderAccountNumber) {
			this.senderAccountNumber = senderAccountNumber;
		}

		private Long customerId;
	    private String customerName;
	    private String transactionType;
	    private Double amount;
	    private Integer toAccountNumber;
	    private Timestamp transactionDate;

	  
	    public Long getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(Long customerId) {
	        this.customerId = customerId;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public String getTransactionType() {
	        return transactionType;
	    }

	    public void setTransactionType(String transactionType) {
	        this.transactionType = transactionType;
	    }

	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public Integer getToAccountNumber() {
	        return toAccountNumber;
	    }

	    public void setToAccountNumber(Integer toAccountNumber) {
	        this.toAccountNumber = toAccountNumber;
	    }

	    public Timestamp getTransactionDate() {
	        return transactionDate;
	    }

	    public void setTransactionDate(Timestamp transactionDate) {
	        this.transactionDate = transactionDate;
	    }
	}


