package DataTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

	/**
	 * Serial version for custom file extensions
	 */
	private static final long serialVersionUID = 1L;

	// instance variables
	private String courseName;
	private String courseCode;
	private Grade currentGrade;

	/*
	 * Method to set values after initialization of object.
	 * 
	 * @params: cn: course name, cc: course code
	 */
	public void setArgs(String cn, String cc) {
		this.courseCode = cc;
		this.courseName = cn;
		this.currentGrade = new Grade();
	}

	/*
	 * Returns true if value passed is positive number 0 <= n <= 100
	 */
	private boolean isPositive(double n) {
		return (n >= 0) && (n <= 100);
	}
	
	/*
	 * Returns the total weighting of all the marks.
	 */
	public double getTotalWeightings() {
		return this.currentGrade.getTotalWeightings();
	}
	
	/*
	 * Returns the weighted grade in the course.
	 */
	public double getWeightedGrade() { 
		return this.currentGrade.currentWeightedGrade();
	}

	/*
	 * Throws exception if either parameter is invalid.
	 */
	private void verifyParameters(double n, double w) {
		if (!(isPositive(n) && isPositive(w))) {
			throw new NumberFormatException();
		}
	}

	/*
	 * Adds grade/weighting to list of total grades.
	 */
	public void addTermMark(double n, double w, String s) throws OverWeightException {
		if (this.currentGrade == null) {
			this.currentGrade = new Grade();
		}
		verifyParameters(n, w);
		System.out.println("Parameters verified.");
		System.out.println("adding "+ s +": " + n + ", " + w);
		this.currentGrade.addGradeWeighting(n, w, s);
		System.out.println("Grade added.");
	}
	
	public void removeGrade(int i) throws OverWeightException {
		this.currentGrade.removeGrade(i);
	}

	public static ArrayList<Course> deserialize() throws IOException, ClassNotFoundException {
		File folder = new File("coursemanager");
		File[] listFiles = folder.listFiles();
		FileInputStream f_in = null;
		try {
			ArrayList<Course> temp = new ArrayList<Course>();
			if (listFiles.length > 0) {
				ObjectInputStream obj_in;
				for (File f : listFiles) {
					f_in = new FileInputStream(f);
					obj_in = new ObjectInputStream(f_in);
					Object obj = obj_in.readObject();
					if (obj instanceof Course) {
						temp.add((Course) obj);
					}
				}
			}
			System.gc();
			f_in.close();
			return temp;
		} catch (Exception e) {
			// e.printStackTrace();
			return new ArrayList<Course>();
		}
	}

	public String getCourseNameCode() {
		return this.courseCode + ": " + this.courseName;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String[] getAssignmentList() {
		return this.currentGrade.toStringArray();
	}

	public void serialize(int i) throws FileNotFoundException, IOException {
		System.out.println("writing to file " + this.courseName);
		File f = new File("coursemanager/" + "[0-9]. " + this.courseName + ".crs");
		if (!f.exists()) {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("coursemanager/" + i + ". " + this.courseName + ".crs"));
			oos.writeObject(this);
			oos.close();
		}
	}
}
