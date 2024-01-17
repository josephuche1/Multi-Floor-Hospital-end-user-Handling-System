/**
 * Represents the main window of the Multi-floor Hospital end-user Handling System.
 * This class is responsible for initializing the application, creating the user interface,
 * and orchestrating the interactions between different components of the system.
 * It manages user sessions, including login and account creation, and provides tabs for
 * accessing various functionalities such as patient management, room assignments, staff scheduling,
 * and financial transactions.
 *
 * @author Uche Joseph
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame {
	private JFrame frame;
	private JPanel createAccount;
	private JPanel login;
	private JTabbedPane tabbedPane;
	private boolean isChecked;
	private JPanel activePanel;
	private Hospital hospital;
	private JTable patients;
	private JTable rooms;
	private JTable staff;
	private JTable equipments;
	private JTable medicines;
	private JTable transactions;
	private JTable floors;
	private int expenses = 0;
	private int income = 0;
	private String filename;
	
	/**
	 * Constructor for MainFrame class. It sets up the main application window, initializes the user interface components,
	 * and prepares the application for user interaction. The constructor calls the initialize method to set up the frame
	 * and its contents, including panels for login, account creation, and the main tabbed pane.
	 */
	public MainFrame() {
		initialize();
	}
	
	/**
	 * Initializes the MainFrame by setting up the main window properties, loading the checkbox state,
	 * loading any saved details, creating the account creation form, the login form, and the tabbed pane.
	 * It sets the login panel as the active panel and makes the frame visible.
	 */
	private void initialize() {
		// TODO Auto-generated method stub
		this.frame = new JFrame();
		this.frame.setTitle("Multi-floor Hospital end-user Handling System");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		
		
		
		this.isChecked = this.loadCheckboxState();
		this.loadDetails();
		
		this.createAccount  = this.createAccountForm();
		this.tabbedPane = this.createTabbedPane();
		
		this.login  = this.loginForm();
		this.frame.add(login);
		this.activePanel = this.login;
		
		this.frame.setVisible(true);
	}
	
	/**
	 * Creates and returns the JPanel for the account creation form.
	 * This panel includes input fields for the hospital name and password, a confirmation password field,
	 * and buttons for submitting the form or returning to the login screen.
	 * It also sets up the layout and visual style for the form.
	 *
	 * @return JPanel representing the account creation form.
	 */
	private JPanel createAccountForm() {
		JPanel createAccount;
		
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		createAccount = new JPanel();
		createAccount.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel titleSection = new JPanel();
		titleSection.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel title = new JLabel("Create Account");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		titleSection.add(title);
		
		JPanel hospitalNameField = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hospitalNameField.setToolTipText("Enter the name of your Hospital");
		JLabel hospitalNameLabel = new JLabel("Hospital Name: ");
		hospitalNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		JTextField hospitalNameTextField = new JTextField(20);
		hospitalNameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		hospitalNameField.add(hospitalNameLabel);
		hospitalNameField.add(hospitalNameTextField);
		
		JPanel hospitalPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hospitalPasswordField.setToolTipText("Create your new password. Please make sure to not forget your password.");
		JLabel hospitalPasswordLabel = new JLabel("Password: ");
		hospitalPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		JPasswordField hospitalPassword_PasswordField = new JPasswordField(20);
		hospitalPassword_PasswordField.setEchoChar('*');
		hospitalPassword_PasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
		hospitalPasswordField.add(hospitalPasswordLabel);
		hospitalPasswordField.add(hospitalPassword_PasswordField);
		
		JPanel hospitalConfirmPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hospitalConfirmPasswordField.setToolTipText("Create your new password. Please make sure to not forget your password.");
		JLabel hospitalConfirmPasswordLabel = new JLabel("Confirm Password: ");
		hospitalConfirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		JPasswordField hospitalConfirmPassword_PasswordField = new JPasswordField(20);
		hospitalConfirmPassword_PasswordField.setEchoChar('*');
		hospitalConfirmPassword_PasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
		hospitalConfirmPasswordField.add(hospitalConfirmPasswordLabel);
		hospitalConfirmPasswordField.add(hospitalConfirmPassword_PasswordField);
		
		JPanel submitOrLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Arial", Font.BOLD, 15));
		submit.setBackground(Color.BLUE);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hospitalName = hospitalNameTextField.getText();
				char[] pf = hospitalPassword_PasswordField.getPassword();
				String password = new String(pf);
				char[] cpf = hospitalConfirmPassword_PasswordField.getPassword();
				String confirmPassword = new String(cpf);
				
				if(password.equals(confirmPassword)) {
					hospital = new Hospital(hospitalName, password);
					System.out.println(hospitalName.toString());
					frame.remove(activePanel);
					frame.add(tabbedPane);
					frame.revalidate();
					frame.repaint();
					activePanel = null;
				} else {
					info.setText("Passwords don't match");
				}
				
			}
		});
		
		JButton backToLogin = new JButton("Back to login");
		backToLogin.setFont(new Font("Arial", Font.BOLD, 15));
		backToLogin.setBackground(Color.GRAY);
		backToLogin.setForeground(Color.WHITE);
		backToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanels(activePanel, login);
			}
		});
		
		submitOrLogin.add(submit);
		submitOrLogin.add(backToLogin);
		
		
		
		
		createAccount.add(titleSection, gbc);
		createAccount.add(hospitalNameField, gbc);
		createAccount.add(hospitalPasswordField, gbc);
		createAccount.add(hospitalConfirmPasswordField, gbc);
		createAccount.add(submitOrLogin, gbc);
		createAccount.add(info, gbc);
		
		
		return createAccount;
		
	}
	
	/**
	 * Creates and returns the JPanel for the login form.
	 * This panel includes input fields for the hospital name and password, and buttons for submitting the login credentials
	 * or switching to the account creation form. It also handles the login logic, including validation of the user's input.
	 * Visual layout and styling are set up within this method.
	 *
	 * @return JPanel representing the login form.
	 */
	private JPanel loginForm() {
		JPanel login = new JPanel();
		
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		login.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel titleSection = new JPanel();
		titleSection.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel title = new JLabel("Login");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		titleSection.add(title);
		
		JPanel hospitalNameField = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hospitalNameField.setToolTipText("Enter the name of your Hospital");
		JLabel hospitalNameLabel = new JLabel("Hospital Name: ");
		hospitalNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		JTextField hospitalNameTextField = new JTextField(20);
		hospitalNameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		hospitalNameField.add(hospitalNameLabel);
		hospitalNameField.add(hospitalNameTextField);
		
		JPanel hospitalPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hospitalPasswordField.setToolTipText("Enter your password. Please make sure to not forget your password.");
		JLabel hospitalPasswordLabel = new JLabel("Password: ");
		hospitalPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		JPasswordField hospitalPassword_PasswordField = new JPasswordField(20);
		hospitalPassword_PasswordField.setEchoChar('*');
		hospitalPassword_PasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
		hospitalPasswordField.add(hospitalPasswordLabel);
		hospitalPasswordField.add(hospitalPassword_PasswordField);
		
		JPanel submitOrSignUp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Arial", Font.BOLD, 15));
		submit.setBackground(Color.BLUE);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        if (hospital == null) {
		            info.setText("Your Account does not exist. Please sign up first.");
		        } else {
		            String name = hospitalNameTextField.getText();
		            char[] pf = hospitalPassword_PasswordField.getPassword();
		            String password = new String(pf);

		            if (hospital.getPassword().equals(password) && hospital.getName().equals(name)) {
		                frame.remove(activePanel);
		                frame.add(tabbedPane);
		                frame.revalidate();
		                frame.repaint();
		                activePanel = null;
		            } else {
		                info.setText("Wrong hospital name or password");
		            }
		        }
			}
		});
		
		JButton SignUp = new JButton("Create an account");
		SignUp.setFont(new Font("Arial", Font.BOLD, 15));
		SignUp.setBackground(Color.GRAY);
		SignUp.setForeground(Color.WHITE);
		SignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanels(activePanel, createAccount);
			}
		});
		
		submitOrSignUp.add(submit);
		submitOrSignUp.add(SignUp);
		
		
		login.add(titleSection, gbc);
		login.add(hospitalNameField, gbc);
		login.add(hospitalPasswordField, gbc);
		login.add(submitOrSignUp, gbc);
		login.add(info, gbc);
		
		
		return login;
		
	}
	
	/**
	 * Creates and returns a JTabbedPane which serves as the main navigation structure within the application.
	 * It sets up individual tabs for different sections such as Patients, Staff, Equipment, Rooms, Pharmacy, Finance, and Floors.
	 * Each tab is associated with a panel that is created by calling the respective method for that section.
	 *
	 * @return JTabbedPane with all the tabs and their corresponding panels added to it.
	 */
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel Patients = createPatientsListsPanel();
	    tabbedPane.addTab("Patients", Patients);
	    
	    JPanel Staff = createStaffListPanel();
	    tabbedPane.addTab("Staff", Staff);
		
	    JPanel Equipments = createEquipmentListPanel();
	    tabbedPane.addTab("Equipment", Equipments);
	    
	    JPanel Rooms = createRoomListPanel();
	    tabbedPane.addTab("Rooms", Rooms);
	    
	    JPanel Pharmacy = createMedicineListPanel();
	    tabbedPane.addTab("Pharmacy", Pharmacy);
	    
	    JPanel Finance = createFinancePanel();
	    tabbedPane.addTab("Finance", Finance);
	    
	    JPanel Floors = createFloorListPanel();
	    tabbedPane.addTab("Floor", Floors);
	    
	    JPanel Settings = createSettingsPanel();
	    tabbedPane.addTab("Settings", Settings);
	    
		return tabbedPane;
	}
	
	/**
	 * Creates and returns a JPanel that contains the list of patients.
	 * This panel includes components such as a table to display patient information and buttons for adding or editing patient details.
	 * It sets up the layout for the patient list and integrates the necessary event listeners for interaction.
	 *
	 * @return JPanel representing the patients list section of the application.
	 */
	private JPanel createPatientsListsPanel() {
		JPanel patientsPanel = new JPanel(new BorderLayout(10, 10));
	    patientsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Patient's List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		searchText.setToolTipText("Enter patient Id");
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				int id = 0;
				try {
				    id = Integer.parseInt(text);
				} catch (NumberFormatException i) {
				    System.out.println(text + " cannot be parsed to an int");
				}
				DefaultTableModel model = (DefaultTableModel) patients.getModel();
				int columnIndex = 0;
				
				for (int i = 0; i < model.getRowCount(); i++) {
				    if (model.getValueAt(i, columnIndex).equals(id)) {
				    	patients.setRowSelectionInterval(i, i);
				    	patients.scrollRectToVisible(patients.getCellRect(i, 0, true));
				        break;
				    }
				}
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		JPanel addNewPatients = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Patients");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewPatientsDialog();
		});
		addNewPatients.add(textInfo);
		addNewPatients.add(addButton);
		
	    header.add(titlePanel, BorderLayout.WEST);
	    header.add(searchPanel, BorderLayout.EAST);
	    header.add(addNewPatients, BorderLayout.CENTER);
//		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		JPanel patientsListPanel = new JPanel(new BorderLayout(10, 10));
		patientsListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = patientsTable();
		patientsListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		patientsPanel.add(header, BorderLayout.NORTH);
		patientsPanel.add(patientsListPanel,  BorderLayout.CENTER);
		return patientsPanel;
	}
	
	/**
	 * Creates and displays a dialog for adding a new patient to the system.
	 * The dialog includes form fields for entering patient details such as name, age, diagnosis, etc.
	 * It also handles the logic for validating input data and updating the patient list upon confirmation.
	 *
	 * @return JDialog object representing the 'Add New Patient' dialog window.
	 */
	private JDialog addNewPatientsDialog() {
		JDialog dialog = new JDialog(frame, "Add New Patients", true);
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel patientsName = new JPanel();
		patientsName.add(new JLabel("Patient's Name:"));
		JTextField patientsNameField = new JTextField(10);
		patientsName.add(patientsNameField);
		
		JPanel patientsIllness = new JPanel();
		patientsIllness.add(new JLabel("Patient's Illness(es):"));
		JTextField patientsIllnessField = new JTextField(10);
		patientsIllness.add(patientsIllnessField);
		
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = patientsNameField.getText();
				String illnesses = patientsIllnessField.getText();
				
				int id = hospital.getPatientCount();
				Patient newPatient = new Patient(Integer.toString(id), name, illnesses);
				
				hospital.addPatient(newPatient);
				HospitalRoom room = hospital.assignPatient(newPatient);
				if(room == null) {
					info.setText("You dont't have any available rooms");
				}
				else {
					newPatient.setRoomNumber(room.getRoomNumber());
				}
				
				DefaultTableModel model = (DefaultTableModel) patients.getModel();
				Object[] row = {Integer.parseInt(newPatient.getId()),newPatient.getName(), Integer.parseInt(newPatient.getRoomNumber()), newPatient.getIllnesses() };
				model.addRow(row);
				if(isChecked) {
					hospital.saveDetails();
				}
				dialog.setVisible(false);
			}
		});
		
		form.add(patientsName, gbc);
		form.add(patientsIllness, gbc);
		form.add(submit, gbc);
		form.add(info, gbc);
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Creates and returns a JScrollPane containing a JTable for patient data.
	 * The table is set up with the appropriate column names and model to display patient information.
	 * This method is responsible for populating the table with data from the hospital's patient list.
	 *
	 * @return JScrollPane with a JTable of patient information.
	 */
    private JScrollPane patientsTable() {
    	String[] columnNames = {"ID", "Name", "Room Number", "Illnesses"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable patientsTable = new JTable(model);
    	this.patients = patientsTable;
    	
    	ArrayList<Patient> patientsI = new ArrayList<Patient>();
    	patientsI.addAll(hospital != null ?  hospital.getPatients() : patientsI);
    	
    	for(Patient patient : patientsI) {
    		model.addRow(new Object[] {Integer.parseInt(patient.getId()),patient.getName(), Integer.parseInt(patient.getRoomNumber()), patient.getIllnesses() });
    	}
    	JScrollPane scrollPane = new JScrollPane(patientsTable);
    	
    	return scrollPane;
    }
	
    /**
     * Creates and returns a JPanel that contains the list of staff members.
     * This panel includes a table to display staff information and may include buttons for adding, editing, or removing staff members.
     * It sets up the layout for the staff list and integrates any necessary event listeners for user interactions.
     *
     * @return JPanel representing the staff list section of the application.
     */
    private JPanel createStaffListPanel() {
		JPanel staffPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Staff List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		searchText.setToolTipText("Enter Staff Id");
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				int id = 0;
				try {
				    id = Integer.parseInt(text);
				} catch (NumberFormatException i) {
				    System.out.println(text + " cannot be parsed to an int");
				}
				DefaultTableModel model = (DefaultTableModel) staff.getModel();
				int columnIndex = 0;
				
				for (int i = 0; i < model.getRowCount(); i++) {
				    if (model.getValueAt(i, columnIndex).equals(id)) {
				    	staff.setRowSelectionInterval(i, i);
				    	staff.scrollRectToVisible(staff.getCellRect(i, 0, true));
				        break;
				    }
				}
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		JPanel addNewStaff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Staff");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewStaffDialog();
		});
		addNewStaff.add(textInfo);
		addNewStaff.add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(searchPanel, BorderLayout.EAST);
		header.add(addNewStaff, BorderLayout.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel staffListPanel = new JPanel(new BorderLayout(10,10));
		staffListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = staffTable();
		staffListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		staffPanel.add(header, BorderLayout.NORTH);
		staffPanel.add(staffListPanel, BorderLayout.CENTER);
		return staffPanel;
    }

    /**
     * Constructs and returns a JScrollPane that encapsulates a JTable designed for displaying staff data.
     * The method configures the table's column headers to match the staff information structure and initializes the table model.
     * It also populates the table with existing staff data from the hospital management system.
     *
     * @return JScrollPane containing a JTable with staff details.
     */
	private JScrollPane staffTable() {
    	String[] columnNames = {"ID", "Name", "Position"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable staffTable = new JTable(model);
    	staff = staffTable;
    	
    	ArrayList<HospitalStaff> staffI = new ArrayList<HospitalStaff>();
    	staffI.addAll(hospital != null ?  hospital.getStaff() : staffI);
    	
    	for(HospitalStaff staff : staffI) {
    		model.addRow(new Object[]{Integer.parseInt(staff.getId()), staff.getName(), staff.getPosition()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(staffTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Creates and displays a dialog for adding a new staff member to the hospital system.
	 * The dialog includes input fields for the staff member's details such as name, department, and contact information.
	 * It also contains logic to validate the entered data and to update the staff list with the new member's details upon submission.
	 *
	 * @return JDialog representing the 'Add New Staff' dialog window.
	 */
	private JDialog addNewStaffDialog() {
		JDialog dialog = new JDialog(frame, "Add New Staff", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel StaffName = new JPanel();
		StaffName.add(new JLabel("Name:"));
		JTextField staffNameField  = new JTextField(10);
		StaffName.add(staffNameField);
		
		JPanel staffPosition = new JPanel();
		staffPosition.add(new JLabel("Position:"));
		JRadioButton radioButton1 = new JRadioButton("Doctor");
		JRadioButton radioButton2 = new JRadioButton("Nurse");
		JRadioButton radioButton3 = new JRadioButton("Cleaner");
		JRadioButton radioButton4 = new JRadioButton("Other");
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		buttons.add(radioButton1);
		buttons.add(radioButton2);
		buttons.add(radioButton3);
		buttons.add(radioButton4);
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);
		staffPosition.add(radioButton1);
		staffPosition.add(radioButton2);
		staffPosition.add(radioButton3);
		staffPosition.add(radioButton4);
		
		JButton submit = new JButton("submit");
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = staffNameField.getText();
				String id = String.valueOf(hospital.getStaffCount());
				HospitalStaff newStaff;
				JRadioButton selectedButton = null;
				for(JRadioButton button : buttons) {
					if(button.isSelected()) {
						selectedButton = button;
						break;
					}
				}
				
				
				if(selectedButton != null) {
					if(selectedButton.getText().equals(radioButton1.getText())) {
						newStaff = new Doctor(id, name);
					}
					else if(selectedButton.getText().equals(radioButton2.getText())) {
						newStaff = new Nurse(id, name);
					}
					else if(selectedButton.getText().equals(radioButton3.getText())) {
						newStaff = new Cleaner(id, name);
					}
					else {
						newStaff = new Other(id, name);
					}
				}
				else {
					newStaff = new Other(id, name);
				}
				
				hospital.addStaff(newStaff);
				DefaultTableModel model = (DefaultTableModel) staff.getModel();
				Object[] row = {Integer.parseInt(newStaff.getId()), newStaff.getName(), newStaff.getPosition()};
				model.addRow(row);
				if(isChecked) {
					hospital.saveDetails();
				}
				dialog.setVisible(false);
			}
		});
		
		form.add(StaffName, gbc);
		form.add(staffPosition, gbc);
		form.add(submit, gbc);
        dialog.add(form);
        
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Creates and returns a JPanel that displays the list of medical equipment.
	 * This panel includes a table for equipment details and may include functionality for adding, editing, or removing equipment entries.
	 * It arranges the layout for the equipment list and sets up the necessary event handling for user interactions.
	 *
	 * @return JPanel representing the equipment list section of the application.
	 */
	private JPanel createEquipmentListPanel() {
		JPanel equipmentsPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Equipments List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		searchText.setToolTipText("Enter equipment name");
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
                
				DefaultTableModel model = (DefaultTableModel) equipments.getModel();
				int columnIndex = 1;
				
				for (int i = 0; i < model.getRowCount(); i++) {
				    if (((String) model.getValueAt(i, columnIndex)).equalsIgnoreCase(text)) {
				    	equipments.setRowSelectionInterval(i, i);
				    	equipments.scrollRectToVisible(equipments.getCellRect(i, 0, true));
				        break;
				    }
				}
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		JPanel addNewEquipment = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Equipment");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewEquipmentDialog();
		});
		addNewEquipment.add(textInfo);
		addNewEquipment.add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(searchPanel, BorderLayout.EAST);
		header.add(addNewEquipment, BorderLayout.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel equipmentListPanel = new JPanel(new BorderLayout(10,10));
		equipmentListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = equipmentTable();
		equipmentListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		equipmentsPanel.add(header, BorderLayout.NORTH);
		equipmentsPanel.add(equipmentListPanel, BorderLayout.CENTER);
		return equipmentsPanel;
    }
	
	/**
	 * Creates and returns a JScrollPane containing a JTable to display equipment data.
	 * The table is initialized with column headers that correspond to the equipment data fields,
	 * and the table model is filled with the current equipment inventory from the system.
	 * This method ensures the equipment data is presented in a user-friendly tabular format.
	 *
	 * @return JScrollPane with a JTable showcasing the equipment inventory.
	 */
	private JScrollPane equipmentTable() {
    	String[] columnNames = {"ID", "Name", "Type", "Price"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable equipmentTable = new JTable(model);
    	equipments = equipmentTable;
    	
    	ArrayList<HospitalEquipment> equipmentsI = new ArrayList<HospitalEquipment>();
    	equipmentsI.addAll(hospital != null ?  hospital.getEquipments() :equipmentsI);
    	
    	for(HospitalEquipment equipment : equipmentsI) {
    		model.addRow(new Object[]{Integer.parseInt(equipment.getId()), equipment.getName(), equipment.getEquipmentType(), equipment.getPrice()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(equipmentTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Creates and presents a dialog for inputting details of new equipment to be added to the hospital's inventory.
	 * The dialog includes fields for the equipment's name, type, quantity, and other relevant details.
	 * It also handles the validation of the input and updates the equipment list with the new entry upon user confirmation.
	 *
	 * @return JDialog that facilitates the addition of new equipment to the inventory.
	 */
	private JDialog addNewEquipmentDialog() {
		JDialog dialog = new JDialog(frame, "Add New Staff", true);
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel equipmentName = new JPanel();
		equipmentName.add(new JLabel("Name:"));
		JTextField equipmentNameField = new JTextField(10);
		equipmentName.add(equipmentNameField);
		
		JPanel equipmentPrice = new JPanel();
		equipmentPrice.add(new JLabel("Price:"));
		JTextField equipmentPriceField =new JTextField(10);
		equipmentPrice.add(equipmentPriceField);
		
		JPanel equipmenttype = new JPanel();
		equipmenttype.add(new JLabel("Type:"));
		JRadioButton radioButton1 = new JRadioButton("Surgical");
		JRadioButton radioButton2 = new JRadioButton("Diagnotstic");
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		buttons.add(radioButton1);
		buttons.add(radioButton2);
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		equipmenttype.add(radioButton1);
		equipmenttype.add(radioButton2);
		
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = equipmentNameField.getText();
				double price = Double.parseDouble(equipmentPriceField.getText());
				HospitalEquipment newEquipment;
				int id = hospital.getEquipmentCount();
				
				JRadioButton selectedButton = null;
				for (JRadioButton button : buttons) {
				    if (button.isSelected()) {
				        selectedButton = button;
				        break;
				    }
				}
				if (selectedButton != null) {
				    if(selectedButton.getText().equals(radioButton1.getText())) {
				    	newEquipment = new SurgicalEquipment(String.valueOf(id), name, price);
				    }
				    else {
				    	newEquipment = new DiagnosticEquipment(String.valueOf(id), name, price);
				    }
				} else {
					newEquipment = new DiagnosticEquipment(String.valueOf(id), name, price);
				}
				hospital.addEquipment(newEquipment);
				if(isChecked) {
					hospital.saveDetails();
				}
				
				Object[] row = {Integer.parseInt(newEquipment.getId()), newEquipment.getName(), newEquipment.getEquipmentType(), newEquipment.getPrice()};
				DefaultTableModel model = (DefaultTableModel) equipments.getModel();
				model.addRow(row);
				dialog.setVisible(false);
			}
		});
		
		form.add(equipmentName, gbc);
		form.add(equipmentPrice, gbc);
		form.add(equipmenttype, gbc);
		form.add(submit, gbc);
		form.add(info, gbc);
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Constructs and returns a JPanel that showcases the list of rooms in the hospital.
	 * This panel features a table to display room information and typically includes options for adding, editing, or deleting room records.
	 * It establishes the layout for the room list and incorporates necessary event handlers for interactions such as room selection or modification.
	 *
	 * @return JPanel representing the room list interface of the application.
	 */
	private JPanel createRoomListPanel() {
		JPanel roomsPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Room List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				int id = 0;
				try {
				    id = Integer.parseInt(text);
				} catch (NumberFormatException i) {
				    System.out.println(text + " cannot be parsed to an int");
				}
				DefaultTableModel model = (DefaultTableModel) rooms.getModel();
				int columnIndex = 0;
				
				for (int i = 0; i < model.getRowCount(); i++) {
				    if (model.getValueAt(i, columnIndex).equals(id)) {
				    	rooms.setRowSelectionInterval(i, i);
				    	rooms.scrollRectToVisible(rooms.getCellRect(i, 0, true));
				        break;
				    }
				}
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		JPanel addNewRoom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Room");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewRoomDialog();
		});
		addNewRoom .add(textInfo);
		addNewRoom .add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(searchPanel, BorderLayout.EAST);
		header.add(addNewRoom, BorderLayout.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel roomListPanel = new JPanel(new BorderLayout(10,10));
		roomListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = roomTable();
		roomListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		roomsPanel.add(header, BorderLayout.NORTH);
		roomsPanel.add(roomListPanel, BorderLayout.CENTER);
		return roomsPanel;
    }
	
	/**
	 * Generates and returns a JScrollPane that contains a JTable for displaying room information.
	 * The table is set up with column headers that reflect the room attributes, and the table model is populated with data representing the current state of room occupancy and details.
	 * This method is responsible for the visual representation of room data in a structured table format.
	 *
	 * @return JScrollPane with a JTable that provides an overview of the hospital's rooms.
	 */
	private JScrollPane roomTable() {
    	String[] columnNames = {"Room Number", "Floor Number", "Room Type", "Availability"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	
    	JTable roomTable = new JTable(model);
    	rooms = roomTable;
    	
    	ArrayList<HospitalRoom> roomsI = new ArrayList<HospitalRoom>();
    	roomsI.addAll(hospital != null ?  hospital.getRooms() : roomsI);
    	int count = 0;
    	
    	for(HospitalRoom room : roomsI) {
    		model.addRow(new Object[]{Integer.parseInt(room.getRoomNumber()), Integer.parseInt(room.getFloorNumber()), room.getRoomType(), room.getAvailability()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(roomTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Creates and displays a dialog for adding a new room to the hospital's database.
	 * This dialog includes form fields for the room's details such as room number, type, and status.
	 * It also contains logic to validate the entered information and to update the room list with the new room's details upon user confirmation.
	 *
	 * @return JDialog object that allows the user to add a new room to the system.
	 */
	private JDialog addNewRoomDialog() {
		JDialog dialog = new JDialog(frame, "Add New Room", true);
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel roomNumber = new JPanel();
		roomNumber.add(new JLabel("Room Number:"));
		JTextField roomNumberField = new JTextField(10);
		roomNumber.add(roomNumberField);
		
		JPanel floorNumber = new JPanel();
		floorNumber.add(new JLabel("Floor Number:"));
		JTextField floorNumberField = new JTextField(10);
		floorNumber.add(floorNumberField);
		
		JPanel isAvailable = new JPanel();
		isAvailable.add(new JLabel("Is available:"));
		JRadioButton radioButton1 = new JRadioButton("True");
		JRadioButton radioButton2 = new JRadioButton("False");
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		buttons.add(radioButton1);
		buttons.add(radioButton2);
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		isAvailable.add(radioButton1);
		isAvailable.add(radioButton2);
		
		JPanel roomType = new JPanel();
		roomType.add(new JLabel("Is available:"));
		JRadioButton radioButton3 = new JRadioButton("General Ward");
		JRadioButton radioButton4 = new JRadioButton("ICU");
		ArrayList<JRadioButton> buttons2 = new ArrayList<JRadioButton>();
		buttons2.add(radioButton3);
		buttons2.add(radioButton4);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(radioButton3);
		group2.add(radioButton4);
		roomType.add(radioButton3);
		roomType.add(radioButton4);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String roomNumber = roomNumberField.getText();
				String floorNumber = floorNumberField.getText();
				boolean available;
				HospitalRoom newRoom;
				
				JRadioButton selectedButton = null;
				for (JRadioButton button : buttons) {
				    if (button.isSelected()) {
				        selectedButton = button;
				        break;
				    }
				}
				if (selectedButton != null) {
				    if(selectedButton.getText().equals(radioButton1.getText())) {
				    	available = true;
				    }
				    else {
				    	available = false;
				    }
				} else {
				    available =true;
				}
				
				JRadioButton selectedButton2 = null;
				for (JRadioButton button : buttons2) {
				    if (button.isSelected()) {
				        selectedButton2 = button;
				        break;
				    }
				}
				if (selectedButton2 != null) {
				    if(selectedButton2.getText().equals(radioButton3.getText())) {
				    	newRoom = new GeneralWardRoom(roomNumber, floorNumber, available);
				    }
				    else {
				    	newRoom = new ICURoom(roomNumber, floorNumber, available);
				    }
				} else {
					newRoom = new GeneralWardRoom(roomNumber, floorNumber, available);
				}
				
			    String warning = hospital.addRoom(newRoom, floorNumber);
			    if(warning.equals("Success")) {
					DefaultTableModel model = (DefaultTableModel) rooms.getModel();
					Object[] row = {Integer.parseInt(newRoom.getRoomNumber()), Integer.parseInt(newRoom.getFloorNumber()), newRoom.getRoomType(), newRoom.getAvailability()};
					model.addRow(row);
					if(isChecked) {
						hospital.saveDetails();
					}
					dialog.setVisible(false);
			    }
			    else {
			    	info.setText(warning);
			    }
				

			}
		});
		
		
		form.add(roomNumber, gbc);
		form.add(floorNumber, gbc);
		form.add(isAvailable, gbc);
		form.add(roomType, gbc);
		form.add(submit, gbc);
		form.add(info, gbc);
        dialog.add(form);
        
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Creates and returns a JPanel that displays the list of medicines available in the hospital.
	 * This panel includes a table for medicine details and may include functionality for adding, editing, or removing medicine entries.
	 * It arranges the layout for the medicine list and sets up the necessary event handling for user interactions.
	 *
	 * @return JPanel representing the medicine list section of the application.
	 */
	private JPanel createMedicineListPanel() {
		JPanel medicinesPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Pharmacy's Medicine List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		searchText.setToolTipText("Enter the name of medicine");
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				int id = 0;
				try {
				    id = Integer.parseInt(text);
				} catch (NumberFormatException i) {
				    System.out.println(text + " cannot be parsed to an int");
				}
				DefaultTableModel model = (DefaultTableModel) medicines.getModel();
				int columnIndex = 0;
				
				for (int i = 0; i < model.getRowCount(); i++) {
				    if (model.getValueAt(i, columnIndex).equals(id)) {
				    	medicines.setRowSelectionInterval(i, i);
				    	medicines.scrollRectToVisible(medicines.getCellRect(i, 0, true));
				        break;
				    }
				}
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		JPanel addNewMedicine = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Medicine");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewMedicineDialog();
		});
		addNewMedicine .add(textInfo);
		addNewMedicine .add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(searchPanel, BorderLayout.EAST);
		header.add(addNewMedicine, BorderLayout.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel medicineListPanel = new JPanel(new BorderLayout(10,10));
		medicineListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = medicineTable();
		medicineListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		medicinesPanel.add(header, BorderLayout.NORTH);
		medicinesPanel.add(medicineListPanel, BorderLayout.CENTER);
		return medicinesPanel;
    }
	
	/**
	 * Constructs and returns a JScrollPane that includes a JTable for displaying medicine inventory.
	 * The table is initialized with appropriate column headers for medicine details, and the table model is populated with the current medicine stock from the system.
	 * This method ensures that the medicine data is presented in an organized, tabular format for easy viewing and management.
	 *
	 * @return JScrollPane containing a JTable with the inventory of medicines.
	 */
	private JScrollPane medicineTable() {
    	String[] columnNames = {"ID", "Name", "Description", "Price"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable medicineTable = new JTable(model);
    	medicines = medicineTable;
    	
    	ArrayList<Medicine> medicineI = new ArrayList<Medicine>();
    	medicineI.addAll(hospital != null ?  hospital.getMedicines() : medicineI);
    	
    	for(Medicine medicine : medicineI) {
    		model.addRow(new Object[]{Integer.parseInt(medicine.getId()), medicine.getName(), medicine.getDescription(), medicine.getPrice()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(medicineTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Creates and shows a dialog for entering details of new medicine to be added to the hospital's inventory.
	 * The dialog provides input fields for the medicine's name, dosage, manufacturer, and other pertinent information.
	 * It includes validation of the data entered and updates the medicine inventory list with the new item upon confirmation.
	 *
	 * @return JDialog for facilitating the addition of new medicine to the inventory.
	 */
	private JDialog addNewMedicineDialog() {
		JDialog dialog = new JDialog(frame, "Add New Medicine", true);
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel MedicineName = new JPanel();
		MedicineName.add(new JLabel("Medicine Name:"));
		JTextField MedicineNameField = new JTextField(10);
		MedicineName.add(MedicineNameField);
		
		JPanel price = new JPanel();
		price.add(new JLabel("Price:"));
		JTextField priceField = new JTextField(10);
		price.add(priceField);
		
		
		JPanel description = new JPanel();
		description.add(new JLabel("Description"));
		JTextField descriptionArea = new JTextField(10);
		description.add(descriptionArea);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = MedicineNameField.getText();
				double price = Double.parseDouble(priceField.getText());
				String description = descriptionArea.getText();
				String id = String.valueOf(hospital.getMedicineCount());
				
				Medicine medicine = new Medicine(id, name, description, price);
				hospital.addMedicine(medicine);
				if(isChecked) {
					hospital.saveDetails();
				}
				
				DefaultTableModel model = (DefaultTableModel) medicines.getModel();
				Object[] row = {Integer.parseInt(medicine.getId()), medicine.getName(), medicine.getDescription(), medicine.getPrice()};
				model.addRow(row);
				dialog.setVisible(false);
				
			}
		});
		
		
		form.add(MedicineName, gbc);
		form.add(price, gbc);
		form.add(description, gbc);
		form.add(submit, gbc);
		form.add(info, gbc);
        dialog.add(form);
        
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Creates and returns a JPanel that organizes and displays financial information for the hospital.
	 * This panel may include tables or summaries of income and expenses, and options for recording financial transactions.
	 * It sets up the layout for the finance section and integrates any necessary components for financial management and reporting.
	 *
	 * @return JPanel representing the finance management section of the application.
	 */
	private JPanel createFinancePanel() {
		JPanel financePanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Finances");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel expenseAndIncomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel expenses = new JLabel("Expenses: 1000000");
		JLabel income = new JLabel("Income: 1000000");
	    expenseAndIncomePanel.add(expenses);
	    expenseAndIncomePanel.add(income);
		
		JPanel addNewTransaction = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Room");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewTransactionDialog();
		});
		addNewTransaction .add(textInfo);
		addNewTransaction .add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(expenseAndIncomePanel, BorderLayout.EAST);
		header.add(addNewTransaction, BorderLayout.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel transactionPanel = new JPanel(new BorderLayout(10,10));
		transactionPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = transactionTable();
		transactionPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		financePanel.add(header, BorderLayout.NORTH);
		financePanel.add(transactionPanel, BorderLayout.CENTER);
		return financePanel;
    }
	
	/**
	 * Creates and returns a JScrollPane containing a JTable to display transaction records.
	 * The table is set up with column headers suitable for financial transactions, such as date, description, amount, and type.
	 * It populates the table model with transaction data from the hospital's financial records.
	 *
	 * @return JScrollPane with a JTable that provides a detailed view of financial transactions.
	 */
	private JScrollPane transactionTable() {
    	String[] columnNames = {"Transaction ID", "Patient ID", "Description", "Amount", "Date", "Payment Method", "Credit Or Debit"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable transactionTable = new JTable(model);
    	transactions = transactionTable;
    	
    	ArrayList<Transaction> transactionI = new ArrayList<Transaction>();
    	transactionI.addAll(hospital != null ? hospital.getTransactions() : transactionI);
    	
    	for(Transaction trans : transactionI) {
    		model.addRow(new Object[]{Integer.parseInt(trans.getTransactionId()), Integer.parseInt(trans.getPatientId()), trans.getDescription(), trans.getAmount(), trans.getDate(), trans.getPaymentMethod(), trans.getCreditOrDebit()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(transactionTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Constructs and displays a dialog for adding a new financial transaction to the hospital's records.
	 * This dialog includes fields for inputting the transaction details such as the type (income or expense), amount, description, and date.
	 * It ensures the validation of the entered data and updates the transaction list with the new record upon user confirmation.
	 *
	 * @return JDialog that enables the user to add a new financial transaction to the hospital's ledger.
	 */
	private JDialog addNewTransactionDialog() {
		JDialog dialog = new JDialog(frame, "Add New Transsaction", true);
		JLabel info = new JLabel("");
		info.setForeground(Color.RED);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel patientID = new JPanel();
		patientID.add(new JLabel("Patient ID:"));
		JTextField patientIDField = new JTextField(10);
		patientID.add(patientIDField);
		
		JPanel amount = new JPanel();
		amount.add(new JLabel("Amount:"));
		JTextField amountField = new JTextField(10);
		amount.add(amountField);
		
		JPanel date = new JPanel();
		date.add(new JLabel("Date:"));
		JTextField dateField = new JTextField(10);
		date.add(dateField);
		
		JPanel description = new JPanel();
		description.add(new JLabel("Payment Method:"));
		JTextField descriptionField = new JTextField(10);
		description.add(descriptionField);
		
		JPanel method = new JPanel();
		method.add(new JLabel("Payment Method:"));
		JTextField methodField = new JTextField(10);
		method.add(methodField);
		
		JPanel creditOrDebit = new JPanel();
		creditOrDebit.add(new JLabel("Credit or Debit:"));
		JRadioButton radioButton1 = new JRadioButton("Credit");
		JRadioButton radioButton2 = new JRadioButton("Debit");
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		buttons.add(radioButton1);
		buttons.add(radioButton2);
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		creditOrDebit.add(radioButton1);
		creditOrDebit.add(radioButton2);
		
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String patientId = patientIDField.getText();
					double amount = Double.parseDouble(amountField.getText());
					Integer.parseInt(patientId);
					
					String date = dateField.getText();
					String description = descriptionField.getText();
					String paymentMethod = methodField.getText();
					String creditOrDebit;
					
					JRadioButton selectedButton = null;
					for (JRadioButton button : buttons) {
					    if (button.isSelected()) {
					        selectedButton = button;
					        break;
					    }
					}
					if (selectedButton != null) {
					    if(selectedButton.getText().equals(radioButton1.getText())) {
					    	creditOrDebit = radioButton1.getText();
					    	income += amount;
					    }else {
					    	creditOrDebit = radioButton2.getText();
					    	expenses += amount;
					    }
					} else {
						creditOrDebit = " ";
					}
					String id = String.valueOf(hospital.getTransactionCount());
					System.out.println(id);
					Transaction trans = new Transaction(id,patientId, description, amount, date, paymentMethod, creditOrDebit );
					hospital.addTransaction(trans);
					
					DefaultTableModel model = (DefaultTableModel) transactions.getModel();
		    		model.addRow(new Object[]{Integer.parseInt(trans.getTransactionId()), Integer.parseInt(trans.getPatientId()), trans.getDescription(), trans.getAmount(), trans.getDate(), trans.getPaymentMethod(), trans.getCreditOrDebit()});
		    		hospital.saveDetails();
		    		
		    		dialog.setVisible(false);
				} catch (NumberFormatException i) {
				    info.setText("Invalid Input");
				}
				

			}
		});
		
		form.add(patientID, gbc);
		form.add(amount, gbc);
		form.add(date, gbc);
		form.add(description, gbc);
		form.add(method, gbc);
		form.add(creditOrDebit, gbc);
		form.add(submit, gbc);
		form.add(info, gbc);
        dialog.add(form);
        
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}

	/**
	 * Constructs and returns a JPanel that displays the list of floors within the hospital.
	 * This panel includes a table to show floor details, and may also provide options for adding, editing, or removing floor entries.
	 * It sets up the layout for the floor list and integrates necessary event handlers for user interactions with the floor data.
	 *
	 * @return JPanel representing the floor list section of the application.
	 */
	private JPanel createFloorListPanel() {
		JPanel floorPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Floors");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		
		JPanel addNewFloor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel textInfo = new JLabel("Add new Floor");
		JButton addButton = new JButton("+");
		addButton.addActionListener(e -> {
			JDialog dialog = addNewFloorDialog();
		});
		addNewFloor .add(textInfo);
		addNewFloor .add(addButton);
		
		header.add(titlePanel, BorderLayout.WEST);
		header.add(addNewFloor, BorderLayout.EAST);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel floorListPanel = new JPanel(new BorderLayout(10,10));
		floorListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JScrollPane scrollPane = floorTable();
		floorListPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		floorPanel.add(header, BorderLayout.NORTH);
		floorPanel.add(floorListPanel, BorderLayout.CENTER);
		return floorPanel;
    }
	
	/**
	 * Generates and returns a JScrollPane containing a JTable that lists the floors of the hospital.
	 * The table is initialized with column headers to display floor-specific information, such as floor number and room count.
	 * The method populates the table with data reflecting the current configuration of the hospital's floors.
	 *
	 * @return JScrollPane with a JTable that outlines the details of the hospital's floors.
	 */
	private JScrollPane floorTable() {
    	String[] columnNames = {"ID", "Floor Number", "Number of Rooms"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable floorTable = new JTable(model);
    	floors = floorTable;
    	
    	ArrayList<HospitalFloor> floorsI = new ArrayList<HospitalFloor>();
    	floorsI.addAll(hospital != null ? hospital.getFloors():floorsI);
    	for(HospitalFloor floor: floorsI) {
    		model.addRow(new Object[]{floor.getFloorNumber(), floor.getFloorNumber(), floor.getRoomCount()});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(floorTable);
    	return scrollPane;
    	
	}
	
	/**
	 * Creates and presents a dialog for adding a new floor to the hospital's structure.
	 * The dialog collects information such as the floor number and initializes the floor in the hospital system.
	 * It also handles the logic for updating the floor list and saving the details if necessary, based on user interaction.
	 *
	 * @return JDialog that facilitates the process of adding a new floor to the hospital.
	 */
	private JDialog addNewFloorDialog() {
		String floorNumber = String.valueOf(hospital.getfloorCount());
		HospitalFloor newFloor = new HospitalFloor(floorNumber);
		hospital.addFloor(newFloor);
		if(isChecked) {
			hospital.saveDetails();
		}
		
		
		DefaultTableModel model = (DefaultTableModel) floors.getModel();
		model.addRow(new Object[]{newFloor.getFloorNumber(), newFloor.getFloorNumber(), newFloor.getRoomCount()});
		
		JDialog dialog = new JDialog(frame, "Add New Floor", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		
		JPanel notification = new JPanel();
		notification.add(new JLabel("A new floor created"));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		form.add(notification, gbc);
		form.add(okButton, gbc);
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	/**
	 * Constructs and returns a JPanel for the settings section of the application.
	 * This panel includes various configuration options such as checkboxes for enabling or disabling features.
	 * It also handles the layout and organization of these settings components, providing a user interface for system preferences.
	 *
	 * @return JPanel that serves as the container for the application's settings controls.
	 */
	private JPanel createSettingsPanel() {
		JPanel settingPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Settings");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		
		
		header.add(titlePanel, BorderLayout.WEST);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel settingsPanel = new JPanel(new BorderLayout(10,10));
		settingsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JCheckBox checkbox = new JCheckBox("Save to backup file");
        checkbox.setBounds(50, 50, 200, 50);
        checkbox.setSelected(loadCheckboxState());

        // Add an ActionListener to the checkbox
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the state of the checkbox to a file
                saveCheckboxState(checkbox.isSelected());
                
            }
        });
        settingsPanel.add(checkbox, BorderLayout.NORTH);
		
		settingPanel.add(header, BorderLayout.NORTH);
		settingPanel.add(settingsPanel, BorderLayout.CENTER);
		return settingPanel;
    }
	
	/**
	 * Reads the saved state of a checkbox from a file and returns it as a boolean value.
	 * It attempts to open the file "checkbox_state.txt", read the state, and interpret it as a boolean.
	 * If the file does not exist or an error occurs during reading, it prints the stack trace and returns false by default.
	 *
	 * @return boolean representing the loaded state of the checkbox, or false if an error occurs.
	 */
    public boolean loadCheckboxState() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("checkbox_state.txt"));
            String state = reader.readLine();
            reader.close();

            return state.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    /**
     * Saves the state of a checkbox to a file named "checkbox_state.txt".
     * It writes the state as a string ("true" or "false") to the file, creating or overwriting it as needed.
     * If an IOException occurs during the save operation, the stack trace is printed to the console.
     *
     * @param state The boolean state of the checkbox to be saved.
     */
    public void saveCheckboxState(boolean state) {
        try {
            PrintWriter writer = new PrintWriter("checkbox_state.txt", "UTF-8");
            writer.println(state);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
    /**
     * Switches the currently displayed panel to a new panel within the main frame.
     * It removes the current panel from the frame (if it's not null) and adds the new panel,
     * then refreshes the frame to display the new panel. The activePanel field is updated to reference the new panel.
     *
     * @param currentPanel The currently displayed JPanel that needs to be removed.
     * @param newPanel     The new JPanel to be displayed in the main frame.
     */
    public void switchPanels(JPanel currentPanel, JPanel newPanel) {
    	if(currentPanel != null) {
    		frame.remove(currentPanel);
    	}
    	
    	frame.add(newPanel);
    	frame.revalidate();
    	frame.repaint();
    	this.activePanel = newPanel;
    }
    
    /**
     * Loads the hospital details from a file named "Hospital Management System.bak".
     * If the file exists, it deserializes the Hospital object from the file and assigns it to the 'hospital' field.
     * If any exceptions occur during the file reading or object deserialization process, they are caught and handled,
     * with relevant error messages printed to the console.
     */
    private void loadDetails() {
      this.hospital = null;
      File file = new File("Hospital Management System.bak");
      if(file.exists()) {
      	  try {
        		FileInputStream fileIn = new FileInputStream(file);
        		ObjectInputStream in  = new ObjectInputStream(fileIn);
        		hospital = (Hospital) in.readObject();
        		fileIn.close();
        	  } catch (FileNotFoundException e) {
        		// TODO Auto-generated catch block
        	    e.printStackTrace();
        	  } catch (IOException e) {
        		// TODO Auto-generated catch block
        	    e.printStackTrace();
        	  } catch (ClassNotFoundException e) {
        		// TODO Auto-generated catch block
        	    System.out.println("Class not found");
        	    e.printStackTrace();
        	  }
      } else {
    	  System.out.println("File does not exist");
      }
    }
    
}
