package DataTypes;

import java.io.Serializable;

public class Tuple  implements Serializable{
	
	private double left;
	private double right;
	
	public Tuple(double n, double r) {
		this.left = n;
		this.right = r;
	}
	
	public double getWeight() {
		return this.right;
	}
	
	public void setWeight(double n) {
		this.right = n;
	}
	
	public double getMark() {
		return this.left;
	}

}
