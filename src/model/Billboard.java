package model;

import java.io.Serializable;

public class Billboard implements Serializable{

	private static final long serialVersionUID = 1L;
	private double height;
	private double width;
	private boolean inUse;
	private String brand;
	private static int billboardCounter;
	private int totalBillboards;
	
	private Billboard() {
		this.totalBillboards = ++Billboard.billboardCounter;
	}
	
	public Billboard(double width, double height, boolean inUse, String brand) {
		this();
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public int getTotalBillboards() {
		return totalBillboards;
	}

	public void setTotalBillboards(int totalBillboards) {
		this.totalBillboards = totalBillboards;
	}

	@Override
	public String toString() {
		return getHeight()+"        "+getWidth()+"          "+isInUse()+"              "+getBrand();
	}
	
	public double calculateArea() {
		double area = 0;
		area = getHeight()*getWidth();
		return area;
	}
	
	
	
}
