package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DataTypes.*;
import Frames.MainFrame;

public class Main {
	public static ArrayList<Course> courses;
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		File f = new File("coursemanager");
		if (!f.exists()) 
			f.mkdirs();
		courses = Course.deserialize();
		MainFrame.main(null);
	}
	
	public static int getCourseIndex(Course c) {
		int i = 0;
		for (Course t:courses) {
			if (t.equals(c))
					return i;
			i++;
		}
		System.out.println(i);
		return -1;
	}
}
