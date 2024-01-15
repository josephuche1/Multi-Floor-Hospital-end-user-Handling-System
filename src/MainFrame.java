import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame {
	private JFrame frame;
	private JPanel createAccount;
	private JPanel login;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JPanel activePanel;
	
	
	public MainFrame() {
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		this.frame = new JFrame();
		this.frame.setTitle("Multi-floor Hospital end-user Handling System");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		
		this.activePanel = null; 
		
//		this.createAccount  = this.createAccountForm();
//		this.frame.add(createAccount);
		
//		this.login  = this.loginForm();
//		this.frame.add(login);
		
		this.menuBar = this.createMenuBar();
		this.frame.setJMenuBar(menuBar);
		
		
		this.frame.setVisible(true);
	}
	
	private JPanel createAccountForm() {
		JPanel createAccount;
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
		submit.setBackground(Color.GREEN);
		submit.setForeground(Color.WHITE);
		
		JButton backToLogin = new JButton("Back to login");
		backToLogin.setFont(new Font("Arial", Font.BOLD, 15));
		backToLogin.setBackground(Color.RED);
		backToLogin.setForeground(Color.WHITE);
		
		submitOrLogin.add(backToLogin);
		submitOrLogin.add(submit);
		
		createAccount.add(titleSection, gbc);
		createAccount.add(hospitalNameField, gbc);
		createAccount.add(hospitalPasswordField, gbc);
		createAccount.add(hospitalConfirmPasswordField, gbc);
		createAccount.add(submitOrLogin, gbc);
		
		
		return createAccount;
		
	}
	
	private JPanel loginForm() {
		JPanel login = new JPanel();
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
		
		JButton SignUp = new JButton("Create an account");
		SignUp.setFont(new Font("Arial", Font.BOLD, 15));
		SignUp.setBackground(Color.RED);
		SignUp.setForeground(Color.WHITE);
		
		submitOrSignUp.add(submit);
		submitOrSignUp.add(SignUp);
		
		
		login.add(titleSection, gbc);
		login.add(hospitalNameField, gbc);
		login.add(hospitalPasswordField, gbc);
		login.add(submitOrSignUp, gbc);
		
		
		return login;
		
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		
		JButton patients = new JButton("Patients");
		patients.setFont(new Font("Arial", Font.PLAIN, 15));
		patients.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                JPanel patientsPanel = addPatientsListsPanel("Hospital");
                switchPanels(activePanel, patientsPanel);
         		System.out.println("Patients was clicked");
			}
		});
		
		JButton staff = new JButton("Staff");
		staff.setFont(new Font("Arial", Font.PLAIN, 15));
		staff.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                 JPanel staffPanel = addStaffListPanel("Hospital");
                 switchPanels(activePanel, staffPanel);
                 System.out.println("Staff was clicked");
			}
		});
		
		JButton rooms = new JButton("Rooms");
		rooms.setFont(new Font("Arial", Font.PLAIN, 15));
		rooms.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                 
			}
		});
		
		JButton equipments = new JButton("Equipments");
		equipments.setFont(new Font("Arial", Font.PLAIN, 15));
		equipments.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                 
			}
		});
		
		JButton pharmacy = new JButton("Pharmacy");
		pharmacy.setFont(new Font("Arial", Font.PLAIN, 15));
		pharmacy.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                 
			}
		});
		
		JButton finances = new JButton("Finances");
		finances.setFont(new Font("Arial", Font.PLAIN, 15));
		finances.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
                 
			}
		});
		
		menuBar.add(patients);
		menuBar.add(staff);
		menuBar.add(equipments);
		menuBar.add(rooms);
		menuBar.add(finances);
		menuBar.add(pharmacy);
		
		return menuBar;
	}
	
	private JPanel addPatientsListsPanel(String hospitalName) {
		JPanel patientsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPatientsPanel = new GridBagConstraints();
		gbcPatientsPanel.gridwidth = GridBagConstraints.REMAINDER;
		gbcPatientsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcPatientsPanel.weightx = 1.0;
	    patientsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel header = new JPanel();
		header.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+", Patients List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Serach");
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		header.add(titlePanel, gbc);
		header.add(searchPanel, gbc);
		
		
		JPanel patientsListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		patientsListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		for(int i = 0; i < 5; i++) {
			JPanel patient = patientsPanel();
			patientsListPanel.add(patient);
		}
		
		
		patientsPanel.add(header, gbcPatientsPanel);
		patientsPanel.add(patientsListPanel, gbcPatientsPanel);
		return patientsPanel;
	}
	
    private JPanel patientsPanel() {
    	String illness[] = {"illness 1", "illness 2", "illness 3"};
    	JPanel patient = new JPanel(new GridLayout(0, 3, 10, 10));
    	patient.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
    	patient.setBackground(Color.RED);
    	
    	JLabel patientName = new JLabel("Patient");
    	patientName.setHorizontalAlignment(JLabel.CENTER);
    	patientName.setFont(new Font("Arial", Font.BOLD, 15));
    	patientName.setForeground(Color.WHITE);
    	
    	JLabel patientRoom = new JLabel("100");
    	patientRoom.setHorizontalAlignment(JLabel.CENTER);
    	patientRoom.setFont(new Font("Arial", Font.BOLD, 15));
    	patientRoom.setForeground(Color.WHITE);
    	
    	patient.add(patientName);
    	patient.add(patientRoom);
    	
    	JPanel patientsIllness = new JPanel(new GridBagLayout());
        patientsIllness.setBackground(Color.RED);
    	GridBagConstraints gbc1 = new GridBagConstraints();
    	gbc1.gridwidth = GridBagConstraints.REMAINDER;
    	gbc1.fill = GridBagConstraints.HORIZONTAL;
    	
    	for(int i = 0; i < 3; i++) {
    		JLabel illnessi = new JLabel(illness[i]);
    		illnessi.setForeground(Color.WHITE);
    		patientsIllness.add(illnessi, gbc1);
    	}
    	
    	patient.add(patientsIllness);
    	
    	
    	return patient;
    }
	
    private JPanel addStaffListPanel(String hospitalName) {
		JPanel staffPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbcStaffPanel = new GridBagConstraints();
		gbcStaffPanel.gridwidth = GridBagConstraints.REMAINDER;
		gbcStaffPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcStaffPanel.weightx = 1.0;
		staffPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel header = new JPanel();
		header.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+", Patients List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		header.add(titlePanel, gbc);
		header.add(searchPanel, gbc);
		
		
		JPanel staffListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		staffListPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		for(int i = 0; i < 5; i++) {
			JPanel patient = staffPanel();
			staffListPanel.add(patient);
		}
		
		
		staffPanel.add(header, gbcStaffPanel);
		staffPanel.add(staffListPanel, gbcStaffPanel);
		return staffPanel;
    }

	private JPanel staffPanel() {
    	String illness[] = {"illness 1", "illness 2", "illness 3"};
    	JPanel staff = new JPanel(new GridLayout(0, 2, 10, 10));
    	staff.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
    	staff.setBackground(Color.CYAN);
    	
    	JLabel staffName = new JLabel("Patient");
    	staffName.setHorizontalAlignment(JLabel.CENTER);
    	staffName.setFont(new Font("Arial", Font.BOLD, 15));
    	staffName.setForeground(Color.WHITE);
    	
    	JLabel staffPosition = new JLabel("100");
    	staffPosition .setHorizontalAlignment(JLabel.CENTER);
    	staffPosition .setFont(new Font("Arial", Font.BOLD, 15));
    	staffPosition .setForeground(Color.WHITE);
    	
    	staff.add(staffName);
    	staff.add(staffPosition);
    	
 
    	
    	
    	return staff;
	}
	
	private void switchPanels(JPanel activePanel, JPanel newPanel) {
		if(activePanel != null) {
			frame.remove(activePanel);
		}
		frame.add(newPanel);
		frame.revalidate();
		frame.repaint();
		this.activePanel = newPanel;
	}
}
