package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.fluxicon.slickerbox.components.SlickerButton;

import DataTypes.Course;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Main.*;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void createCourses() {
		if (Main.courses.size() < 6) {
			for (int i = 1; i < 7; i++) {
				Course c = new Course();
				c.setArgs("Course " + i, "Empty");
				Main.courses.add(c);
			}
		}
	}
	
	public static void deleteAllFiles() {
		File f = new File("coursemanager");
		File[] fs = f.listFiles();
		for (File f1:fs) {
			f1.delete();
		}
	}

	public void dispose(boolean save) {
		deleteAllFiles();
		if (save) {
			int counter = 0;
			for (Course c : Main.courses) {
				try {
					c.serialize(counter);
					counter++;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			super.dispose();
		}
	}
	
	public static void save() {
		int counter = 0;
		for (Course c : Main.courses) {
			try {
				c.serialize(counter);
				counter++;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void dispose() {
		save();
		super.dispose();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public MainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		setTitle("Course Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setBounds(100, 100, 340, 196);
		contentPane = new JPanel();
		contentPane.setBackground(Color.darkGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createCourses();

		JButton btnNewButton = new SlickerButton(Main.courses.get(0).getCourseCode());
		btnNewButton.setBounds(10, 42, 148, 25);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("1");
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Course Manager");
		lblNewLabel.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.lightGray);
		lblNewLabel.setBounds(96, 11, 183, 20);
		contentPane.add(lblNewLabel);

		SlickerButton slckrbtnCourse = new SlickerButton(Main.courses.get(1).getCourseCode());
		slckrbtnCourse.addActionListener(this);
		slckrbtnCourse.setActionCommand("2");
		slckrbtnCourse.setBounds(168, 42, 148, 25);
		contentPane.add(slckrbtnCourse);

		SlickerButton slckrbtnCourse_1 = new SlickerButton(Main.courses.get(2).getCourseCode());
		slckrbtnCourse_1.setBounds(10, 78, 148, 25);
		slckrbtnCourse_1.addActionListener(this);
		slckrbtnCourse_1.setActionCommand("3");
		contentPane.add(slckrbtnCourse_1);

		SlickerButton slckrbtnCourse_2 = new SlickerButton(Main.courses.get(3).getCourseCode());
		slckrbtnCourse_2.setBounds(168, 78, 148, 25);
		slckrbtnCourse_2.addActionListener(this);
		slckrbtnCourse_2.setActionCommand("4");
		contentPane.add(slckrbtnCourse_2);

		SlickerButton slckrbtnCourse_3 = new SlickerButton(Main.courses.get(4).getCourseCode());
		slckrbtnCourse_3.setBounds(10, 114, 148, 25);
		slckrbtnCourse_3.addActionListener(this);
		slckrbtnCourse_3.setActionCommand("5");
		contentPane.add(slckrbtnCourse_3);

		SlickerButton slckrbtnCourse_4 = new SlickerButton(Main.courses.get(5).getCourseCode());
		slckrbtnCourse_4.setBounds(168, 114, 148, 25);
		slckrbtnCourse_4.addActionListener(this);
		slckrbtnCourse_4.setActionCommand("6");
		;
		contentPane.add(slckrbtnCourse_4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int option = Integer.parseInt(e.getActionCommand()) - 1;
		if (Main.courses.get(option).getCourseCode().equals("Empty")) {
			SetupCourse.main(option);
		} else {
			ManageCourse.main(Main.courses.get(option));
		}
		this.dispose(false);
	}
}
