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
	private boolean isFirstTime;
	private JPanel activePanel;
	private Hospital hospital;
	private JTable patients;
	private JTable rooms;
	private JTable staff;
	private JTable equipments;
	
	public MainFrame() {
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		this.frame = new JFrame();
		this.frame.setTitle("Multi-floor Hospital end-user Handling System");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		
		this.loadDetails();
		
		this.createAccount  = this.createAccountForm();
		this.tabbedPane = this.createTabbedPane();
		
		this.login  = this.loginForm();
		this.frame.add(login);
		this.activePanel = this.login;
		
		this.frame.setVisible(true);
	}
	
	
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
				hospital.saveDetails();
				form.setVisible(false);
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
				hospital.saveDetails();
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
				hospital.saveDetails();
				
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

	private JPanel createRoomListPanel() {
		JPanel roomsPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(this.hospital != null? this.hospital.getName(): ""+"'s Room List");
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
					hospital.saveDetails();
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
	
	private JPanel createMedicineListPanel() {
		JPanel medicinesPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(this.hospital != null? this.hospital.getName(): ""+" Pharmacy's Medicine List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
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
	
	private JScrollPane medicineTable() {
    	String[] columnNames = {"ID", "Name", "Description", "Price"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable medicineTable = new JTable(model);
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "medicine " + i, "dexcribe it", 1000});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(medicineTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewMedicineDialog() {
		JDialog dialog = new JDialog(frame, "Add New Medicine", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel MedicineName = new JPanel();
		MedicineName.add(new JLabel("Medicine Name:"));
		MedicineName.add(new JTextField(10));
		
		JPanel price = new JPanel();
		price.add(new JLabel("Price:"));
		price.add(new JTextField(10));
		
		
		JPanel description = new JPanel();
		description.add(new JLabel("Description"));
		description.add(new JScrollPane(new JTextArea(5, 20)));
		
		
		form.add(MedicineName, gbc);
		form.add(price, gbc);
		form.add(description, gbc);
		form.add(new JButton("submit"), gbc);
        dialog.add(form);
        
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	private JPanel createFinancePanel() {
		JPanel financePanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(this.hospital != null? this.hospital.getName(): ""+"'s Finances");
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

	private JScrollPane transactionTable() {
    	String[] columnNames = {"Transaction ID", "Patient ID", "Description", "Amount", "Date", "Payment Method", "Credit Or Debit"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable transactionTable = new JTable(model);
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "P001", "Payment for Medicine", "200", "2022-01-01", "Cash", "credit"});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(transactionTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewTransactionDialog() {
		JDialog dialog = new JDialog(frame, "Add New Transsaction", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel patientID = new JPanel();
		patientID.add(new JLabel("Patient ID:"));
		patientID.add(new JTextField(10));
		
		JPanel amount = new JPanel();
		amount.add(new JLabel("Amount:"));
		amount.add(new JTextField(10));
		
		JPanel date = new JPanel();
		date.add(new JLabel("Date:"));
		date.add(new JTextField(10));
		
		JPanel method = new JPanel();
		method.add(new JLabel("Payment Method:"));
		method.add(new JTextField(10));
		
		JPanel creditOrDebit = new JPanel();
		creditOrDebit.add(new JLabel("Credit or Debit:"));
		creditOrDebit.add(new JTextField(10));
		
		form.add(patientID, gbc);
		form.add(amount, gbc);
		form.add(date, gbc);
		form.add(method, gbc);
		form.add(creditOrDebit, gbc);
		form.add(new JButton("submit"), gbc);
        dialog.add(form);
        
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}

	private JPanel createFloorListPanel() {
		JPanel floorPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(this.hospital != null? this.hospital.getName(): ""+"'s Floors");
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
	
	private JScrollPane floorTable() {
    	String[] columnNames = {"ID", "Floor Number", "Number of Rooms"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable floorTable = new JTable(model);
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "floor " + i, 25});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(floorTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewFloorDialog() {
		JDialog dialog = new JDialog(frame, "Add New Floor", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel floorNumber = new JPanel();
		floorNumber.add(new JLabel("Floor Number:"));
		floorNumber.add(new JTextField(10));
		
		JPanel NumberOfRooms = new JPanel();
		NumberOfRooms.add(new JLabel("Number of Rooms:"));
		NumberOfRooms.add(new JTextField(10));
		
		form.add(floorNumber, gbc);
		form.add(NumberOfRooms, gbc);
		form.add(new JButton("submit"), gbc);
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	private JDialog saveHospital() {
		JDialog dialog = new JDialog(frame, "Save Hospital", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel filename1 = new JPanel();
		filename1.add(new JLabel("Filename:"));
		filename1.add(new JTextField(10));
		
		form.add(filename1, gbc);
		form.add(new JButton("submit"), gbc);
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
    
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
        isFirstTime = loadIsFirstTime();

        // Add an ActionListener to the checkbox
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the state of the checkbox to a file
                saveCheckboxState(checkbox.isSelected());
                saveIsFirstTime(isFirstTime);
                
                if (checkbox.isSelected() && isFirstTime) {
                    saveHospital();
                    isFirstTime = false;
                }
            }
        });
        settingsPanel.add(checkbox, BorderLayout.NORTH);
		
		settingPanel.add(header, BorderLayout.NORTH);
		settingPanel.add(settingsPanel, BorderLayout.CENTER);
		return settingPanel;
    }
	
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

    public void saveCheckboxState(boolean state) {
        try {
            PrintWriter writer = new PrintWriter("checkbox_state.txt", "UTF-8");
            writer.println(state);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
    public static boolean loadIsFirstTime() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("isfirsttime_state.txt"));
            String state = reader.readLine();
            reader.close();

            return state.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void saveIsFirstTime(boolean state) {
        try {
            PrintWriter writer = new PrintWriter("isfirsttime_state.txt", "UTF-8");
            writer.println(state);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void switchPanels(JPanel currentPanel, JPanel newPanel) {
    	if(currentPanel != null) {
    		frame.remove(currentPanel);
    	}
    	
    	frame.add(newPanel);
    	frame.revalidate();
    	frame.repaint();
    	this.activePanel = newPanel;
    }
    
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
        	    System.out.println("User class not found");
        	    e.printStackTrace();
        	  }
      } else {
    	  System.out.println("File does not exist");
      }
    }

}
