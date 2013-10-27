package com.jswiente.phd.prototype.domain;

// Generated 06.03.2012 20:55:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Customerproduct generated by hbm2java
 */
@Entity
@Table(name = "customerproduct", catalog = "billdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CustomerId", "ProductSeq" }))
public class Customerproduct implements Record, java.io.Serializable {

	private long customerProductId;
	private Customer customer;
	private Product product;
	private long productSeq;
	private Date startDate;
	private Date endDate;
	private Long accountNum;
	private Set<Eventsource> eventsources = new HashSet<Eventsource>(0);
	private Set<Customerproducttariff> customerproducttariffs = new HashSet<Customerproducttariff>(
			0);

	public Customerproduct() {
	}

	public Customerproduct(long customerProductId, Customer customer,
			long productSeq, Date startDate) {
		this.customerProductId = customerProductId;
		this.customer = customer;
		this.productSeq = productSeq;
		this.startDate = startDate;
	}

	public Customerproduct(long customerProductId, Customer customer,
			Product product, long productSeq, Date startDate, Date endDate,
			Long accountNum, Set<Eventsource> eventsources,
			Set<Customerproducttariff> customerproducttariffs) {
		this.customerProductId = customerProductId;
		this.customer = customer;
		this.product = product;
		this.productSeq = productSeq;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountNum = accountNum;
		this.eventsources = eventsources;
		this.customerproducttariffs = customerproducttariffs;
	}

	@Id
	@Column(name = "CustomerProductId", unique = true, nullable = false)
	public long getCustomerProductId() {
		return this.customerProductId;
	}

	public void setCustomerProductId(long customerProductId) {
		this.customerProductId = customerProductId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerId", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "ProductSeq", nullable = false)
	public long getProductSeq() {
		return this.productSeq;
	}

	public void setProductSeq(long productSeq) {
		this.productSeq = productSeq;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "accountNum")
	public Long getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(Long accountNum) {
		this.accountNum = accountNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerproduct")
	public Set<Eventsource> getEventsources() {
		return this.eventsources;
	}

	public void setEventsources(Set<Eventsource> eventsources) {
		this.eventsources = eventsources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerproduct")
	public Set<Customerproducttariff> getCustomerproducttariffs() {
		return this.customerproducttariffs;
	}

	public void setCustomerproducttariffs(
			Set<Customerproducttariff> customerproducttariffs) {
		this.customerproducttariffs = customerproducttariffs;
	}

	@Override
	public long getId() {
		return customerProductId;
	}

}
