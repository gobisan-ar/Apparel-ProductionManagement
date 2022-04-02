package com.vokot.productionmanagement.model;

import java.util.HashMap;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * This is the ProductReport model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class ProductReport {
	private int mensTshrit;
	private int mensShirt;
	private int mensHoodie;
	private int womensTshrit;
	private int womensShirt;
	private int womensHoodie;
	
	public ProductReport() {
		// TODO Auto-generated constructor stub
	}

	public ProductReport(int mensTshrit, int mensShirt, int mensHoodie, int womensTshrit, int womensShirt,
			int womensHoodie) {
		super();
		this.mensTshrit = mensTshrit;
		this.mensShirt = mensShirt;
		this.mensHoodie = mensHoodie;
		this.womensTshrit = womensTshrit;
		this.womensShirt = womensShirt;
		this.womensHoodie = womensHoodie;
	}

	public int getMensTshrit() {
		return mensTshrit;
	}

	public void setMensTshrit(int mensTshrit) {
		this.mensTshrit = mensTshrit;
	}

	public int getMensShirt() {
		return mensShirt;
	}

	public void setMensShirt(int mensShirt) {
		this.mensShirt = mensShirt;
	}

	public int getMensHoodie() {
		return mensHoodie;
	}

	public void setMensHoodie(int mensHoodie) {
		this.mensHoodie = mensHoodie;
	}

	public int getWomensTshrit() {
		return womensTshrit;
	}

	public void setWomensTshrit(int womensTshrit) {
		this.womensTshrit = womensTshrit;
	}

	public int getWomensShirt() {
		return womensShirt;
	}

	public void setWomensShirt(int womensShirt) {
		this.womensShirt = womensShirt;
	}

	public int getWomensHoodie() {
		return womensHoodie;
	}

	public void setWomensHoodie(int womensHoodie) {
		this.womensHoodie = womensHoodie;
	}
	
	public int getTotMensCollection() {
		return (mensTshrit + mensShirt + mensHoodie);
	}
	
	public int getTotWomensCollection() {
		return (womensTshrit + womensShirt + womensHoodie);
	}
}
