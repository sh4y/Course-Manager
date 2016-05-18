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
import DataTypes.OverWeightException;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageCourse extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Course carry;

	/**
	 * Launch the application.
	 */
	public static void main(Course c) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCourse frame = new ManageCourse(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void dispose() {
		MainFrame.deleteAllFiles();
		MainFrame.save();
		super.dispose();
	}
	
	JComboBox comboBox;

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public ManageCourse(Course c) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		this.carry = c;
		
		setTitle("Manage Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setBounds(100, 100, 448, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.darkGray);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name: " + c.getCourseName());
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseName.setForeground(Color.LIGHT_GRAY);
		lblCourseName.setBounds(105, 11, 302, 20);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseCode = new JLabel("Course Code: " + c.getCourseCode());
		lblCourseCode.setForeground(Color.LIGHT_GRAY);
		lblCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseCode.setBounds(105, 26, 257, 20);
		contentPane.add(lblCourseCode);
		
		JButton btnNewButton = new SlickerButton("Modify");
		btnNewButton.setBounds(10, 11, 85, 35);
		btnNewButton.setActionCommand("mod");
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(c.getAssignmentList()));
		comboBox.setBounds(20, 63, 387, 20);
		contentPane.add(comboBox);
		
		JButton btnAddAssignment = new SlickerButton("Add Assignment");
		btnAddAssignment.setBounds(49, 94, 156, 23);
		btnAddAssignment.setActionCommand("add");
		btnAddAssignment.addActionListener(this);
		contentPane.add(btnAddAssignment);
		
		JButton btnRemoveAssignment = new SlickerButton("Remove Assignment");
		btnRemoveAssignment.setBounds(215, 94, 156, 23);
		btnRemoveAssignment.addActionListener(this);
		btnRemoveAssignment.setActionCommand("remove");
		contentPane.add(btnRemoveAssignment);
		
		JButton btnCalculate = new SlickerButton("Calculate");
		btnCalculate.setBounds(169, 128, 89, 23);
		btnCalculate.setActionCommand("calc");
		btnCalculate.addActionListener(this);
		contentPane.add(btnCalculate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		int index = Main.Main.getCourseIndex(this.carry);
		if (a.equals("mod")) {
			SetupCourse.main(index);
			this.dispose();
		} else if (a.equals("add")) {
			AddAssignment.main(index);
			this.dispose();
		} else if (a.equals("calc")) {
			Calculations.main(index);
		} else if (a.equals("remove")) {
			int i = comboBox.getSelectedIndex();
			try {
				Main.Main.courses.get(index).removeGrade(i);
			} catch (OverWeightException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ManageCourse.main(carry);
			this.dispose();
		}
	}
}
