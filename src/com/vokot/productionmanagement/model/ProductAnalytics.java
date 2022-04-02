package com.vokot.productionmanagement.model;

import java.util.HashMap;
import java.util.List;

/**
 * This is the ProductAnalytics model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductAnalytics {
	private int countProducts;
	private int countNotices;
	private int countInspections;
	
	private HashMap<String, Integer> countCollection;
	
	private HashMap<String, Integer> noticeApproval;
	
	public ProductAnalytics() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductAnalytics(int countProducts, int countNotices, int countInspections,
			HashMap<String, Integer> countCollection, HashMap<String, Integer> noticeApproval) {
		super();
		this.countProducts = countProducts;
		this.countNotices = countNotices;
		this.countInspections = countInspections;
		this.setCountCollection(countCollection);
		this.noticeApproval = noticeApproval;
	}

	public ProductAnalytics(int countProducts, int countNotices, int countInspections) {
		super();
		this.countProducts = countProducts;
		this.countNotices = countNotices;
		this.countInspections = countInspections;
	}

	public int getCountProducts() {
		return countProducts;
	}

	public void setCountProducts(int countProducts) {
		this.countProducts = countProducts;
	}

	public int getCountNotices() {
		return countNotices;
	}

	public void setCountNotices(int countNotices) {
		this.countNotices = countNotices;
	}

	public int getCountInspections() {
		return countInspections;
	}

	public void setCountInspections(int countInspections) {
		this.countInspections = countInspections;
	}

	public HashMap<String, Integer> getNoticeApproval() {
		return noticeApproval;
	}

	public void setNoticeApproval(HashMap<String, Integer> noticeApproval) {
		this.noticeApproval = noticeApproval;
	}

	public HashMap<String, Integer> getCountCollection() {
		return countCollection;
	}

	public void setCountCollection(HashMap<String, Integer> countCollection) {
		this.countCollection = countCollection;
	}
}
