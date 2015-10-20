package org.helianto.order.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.partner.domain.PrivateEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Contract.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@DiscriminatorValue("C")

public class Contract 
	extends Part 
{

	private static final long serialVersionUID = 1L;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="privateEntityId")
	private PrivateEntity privateEntity;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="processDocumentId")
	private ProcessDocument processDocument;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextCheckDate;
	
	private Double currencyRate;
	
	private Double discountRate;
	
	private int createOrder;
	
	private int createOrderDay;
	
	public String getDiscriminator() {
		return "C";
	}

	/**
	 * Default constructor.
	 */
	public Contract() {
		super();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
	public Contract(Entity entity, String docCode) {
		this();
		setEntity(entity);
		setDocCode(docCode);
	}
	
	/**
	+	 * Merger.
	+	 * 
	+	 * @param command
	+	 */
		public Contract merge(Contract command) {
			super.merge(command);
			setNextCheckDate(command.getNextCheckDate());
			setCurrencyRate(command.getCurrencyRate());
			setDiscountRate(command.getDiscountRate());
			setCreateOrder(command.getCreateOrder());
			setCreateOrderDay(command.getCreateOrderDay());
			return this;
		}
		
	
	public PrivateEntity getPrivateEntity() {
		return privateEntity;
	}
	public void setPrivateEntity(PrivateEntity privateEntity) {
		this.privateEntity = privateEntity;
	}
	
	/**
	 * Date of contract expiration.
	 */
	public Date getNextCheckDate() {
		return nextCheckDate;
	}
	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}
	
	/**
	 * Currency rate.
	 */
	public Double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	/**
	 * Discount rate applied to the contract.
	 */
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	
	/**
	 * Number of orders to create automatically.
	 */
	public int getCreateOrder() {
		return createOrder;
	}
	public void setCreateOrder(int createOrder) {
		this.createOrder = createOrder;
	}
	
	public int getCreateOrderDay() {
		return createOrderDay;
	}
	public void setCreateOrderDay(int createOrderDay) {
		this.createOrderDay = createOrderDay;
	}
	
	public ProcessDocument getProcessDocument() {
		return processDocument;
	}
	
	public void setProcessDocument(ProcessDocument processDocument) {
		this.processDocument = processDocument;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Contract) {
			return super.equals(other);
		}
		return false;
	}
	
}
