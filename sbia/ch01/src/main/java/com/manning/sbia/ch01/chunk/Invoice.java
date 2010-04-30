/**
 * 
 */
package com.manning.sbia.ch01.chunk;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author acogoluegnes
 *
 */
public class Invoice {

	private String id;
	
	private Long customerId;
	
	private String description;
	
	private Date issueDate;
	
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public String toString() {
		return id + "," + customerId + "," + description + "," + amount+"," + issueDate;
	}
	
	
	
}