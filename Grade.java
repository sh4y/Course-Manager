package DataTypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Grade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private double totalWeighting;
	//stores grades/weights from different assignments
	private ArrayList<Tuple> grades;
	private ArrayList<String> gradeNames;
	
	public Grade() {
		this.totalWeighting = 0.0;
		this.grades = new ArrayList<Tuple>();
		this.gradeNames = new ArrayList<String>();
	}

	public String[] toStringArray() {
		if (this.grades.size() > 0) {
		String[] str = new String[this.grades.size()];
		for (int i = 0; i < this.grades.size(); i++) {
			String name = this.gradeNames.get(i);
			Tuple t = this.grades.get(i);
			str[i] = (name + ": Grade: " + t.getMark() + "% Weight: " + t.getWeight());
		}
		return str; }
		else {
			String[] s = {""};
			return s;
		}
	}
	
	public void removeGrade(int i) throws OverWeightException {
		this.gradeNames.remove(i);
		this.grades.remove(i);
		setWeightings();
	}
	
	public void addGradeWeighting(double n, double w, String a) throws OverWeightException {
		Tuple t = new Tuple(n,w);
		this.grades.add(t);
		System.out.println("Add Grade Weighting: grade added to tuple");
		this.gradeNames.add(a);
		System.out.println("Added Assignment name");
		setWeightings();
		System.out.println("Weightings set");
	}
	
	public double currentWeightedGrade() {
		double total = 0;
		for (Tuple t: grades) {
			total += t.getMark()/100 * t.getWeight();
			System.out.println("Total mark increased by: " + (t.getMark()/100)*t.getWeight());
		}		
		System.out.println("returning: " + total);
		return total;
	}
	
	private void setWeightings() throws OverWeightException {
		this.totalWeighting = 0;
		for (Tuple t:grades) {
			this.totalWeighting += t.getWeight();
			if (this.totalWeighting > 100) {
				this.totalWeighting = 100.0;
				throw new OverWeightException("Current weighting exceeded 100");
			}
		}
	}
	
	public double getTotalWeightings() {
		return this.totalWeighting;
	}
}
