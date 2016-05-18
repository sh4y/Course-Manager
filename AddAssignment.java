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
import DataTypes.Grade;
import DataTypes.OverWeightException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AddAssignment extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblGrade;
	private JLabel lblWeight;
	private JTextField textField_2;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(int c) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAssignment frame = new AddAssignment(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int index;

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public AddAssignment(int c) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("Add Assignment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setBounds(100, 100, 251, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.darkGray);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Assignment Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 11, 136, 17);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(136, 11, 91, 17);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(55, 39, 50, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblGrade = new JLabel("Grade:");
		lblGrade.setForeground(Color.LIGHT_GRAY);
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrade.setBounds(10, 39, 56, 17);
		contentPane.add(lblGrade);
		
		lblWeight = new JLabel("Weight:");
		lblWeight.setForeground(Color.LIGHT_GRAY);
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeight.setBounds(112, 39, 56, 17);
		contentPane.add(lblWeight);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(169, 39, 58, 20);
		contentPane.add(textField_2);
		
		btnNewButton = new SlickerButton("Add");
		btnNewButton.setBounds(79, 70, 89, 23);
		btnNewButton.setActionCommand("add");
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		this.index = c;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String aName = textField.getText();
		double grade = Integer.parseInt(textField_1.getText());
		double weight = Integer.parseInt(textField_2.getText());
		
		String e = arg0.getActionCommand();
		
		if (e.equals("add")) {
			double diff = 100.0 - Main.Main.courses.get(index).getTotalWeightings();
			if (diff < weight) {
				weight = diff;
			}
			System.out.println(aName + ": " + grade + ", " + weight);
			try {
				System.out.println(Main.Main.courses.get(index).getCourseName());
				Main.Main.courses.get(index).addTermMark(grade, weight, aName);
			} catch (OverWeightException e1) {
				JOptionPane.showMessageDialog(this, "Weight exceeded 100%. Difference set to 100%.");
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ManageCourse.main(Main.Main.courses.get(index));
			this.dispose();
			
		}
	}
}
