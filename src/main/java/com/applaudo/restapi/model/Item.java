package com.applaudo.restapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6290668663481962143L;

	@Id
    @Column(name = "itemId", unique = true, nullable = false)
	private int itemId;
	
	@Column(name = "itemName", nullable = false)
	private String itemName;
	
	@Column(name = "itemEnteredByUser", nullable = false)
	private String itemEnteredByUser;
	
	@Column(name = "itemEnteredDate", nullable = false)
	private Timestamp itemEnteredDate;
	
	@Column(name = "itemBuyingPrice", precision = 10, scale = 1)
	private Double itemBuyingPrice;
	
	@Column(name = "itemSellingPrice", precision = 10, scale = 2)
	private Double itemSellingPrice;
	
	@Column(name = "itemLastModifiedDate")
	private Timestamp itemLastModifiedDate;
	
	@Column(name = "itemLastModifiedByUser")
	private String itemLastModifiedByUser;

	@Enumerated(EnumType.STRING)
	private ItemStatusEnum status;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemEnteredByUser() {
		return itemEnteredByUser;
	}

	public void setItemEnteredByUser(String itemEnteredByUser) {
		this.itemEnteredByUser = itemEnteredByUser;
	}

	public Timestamp getItemEnteredDate() {
		return itemEnteredDate;
	}

	public void setItemEnteredDate(Timestamp itemEnteredDate) {
		this.itemEnteredDate = itemEnteredDate;
	}

	public Double getItemBuyingPrice() {
		return itemBuyingPrice;
	}

	public void setItemBuyingPrice(Double itemBuyingPrice) {
		this.itemBuyingPrice = itemBuyingPrice;
	}

	public Double getItemSellingPrice() {
		return itemSellingPrice;
	}

	public void setItemSellingPrice(Double itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}

	public Timestamp getItemLastModifiedDate() {
		return itemLastModifiedDate;
	}

	public void setItemLastModifiedDate(Timestamp itemLastModifiedDate) {
		this.itemLastModifiedDate = itemLastModifiedDate;
	}

	public String getItemLastModifiedByUser() {
		return itemLastModifiedByUser;
	}

	public void setItemLastModifiedByUser(String itemLastModifiedByUser) {
		this.itemLastModifiedByUser = itemLastModifiedByUser;
	}

	public ItemStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ItemStatusEnum status) {
		this.status = status;
	}


	
	

}
