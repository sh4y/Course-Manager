package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fluxicon.slickerbox.components.SlickerButton;

import DataTypes.Course;
import Main.Main;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

public class SetupCourse extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(int args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupCourse frame = new SetupCourse(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void deleteAllFiles() {
		File f = new File("coursemanager");
		File[] fs = f.listFiles();
		for (File f1 : fs) {
			f1.delete();
		}
	}

	public void dispose() {
		deleteAllFiles();
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
	public SetupCourse(int n) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		setTitle("Add a course");
		this.index = n;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setBounds(100, 100, 229, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.darkGray);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Course Name:");
		lblNewLabel.setForeground(Color.lightGray);
		lblNewLabel.setBounds(10, 11, 99, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Course Code:");
		lblNewLabel_1.setForeground(Color.lightGray);
		lblNewLabel_1.setBounds(10, 41, 71, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(88, 12, 86, 20);
		textField.setBackground(Color.lightGray);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(88, 38, 86, 20);
		textField_1.setBackground(Color.lightGray);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnNewButton = new SlickerButton("Save");
		btnNewButton.setBounds(62, 69, 86, 21);
		btnNewButton.setActionCommand("save");
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String e = arg0.getActionCommand();
		if (e.equals("save")) {
			String n = Main.courses.get(this.index).getCourseName() + ".crs";
			System.out.println("Trying to delete file" + n);
			File f = new File("coursemanager/" + this.index + ". " + n);
			if (f.exists()) {
				System.out.println("deleting file " + f.getAbsolutePath());
				System.out.println("deleted:" + String.valueOf(f.delete()));
			}
			Main.courses.get(this.index).setArgs(textField.getText(), textField_1.getText());
			MainFrame.main(null);
			this.dispose();
		}

	}
}
