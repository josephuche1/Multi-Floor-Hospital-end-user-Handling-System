import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame {
	private JFrame frame;
	private JPanel createAccount;
	private JPanel login;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	
	
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
		
//		this.createAccount  = this.createAccountForm();
//		this.frame.add(createAccount);
		
//		this.login  = this.loginForm();
//		this.frame.add(login);
		
		this.tabbedPane = this.createTabbedPane();
		this.frame.add(tabbedPane);
		
		
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
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel Patients = createPatientsListsPanel("Hospital");
	    tabbedPane.addTab("Patients", Patients);
	    
	    JPanel Staff = createStaffListPanel("Hospital");
	    tabbedPane.addTab("Staff", Staff);
		
	    JPanel Equipments = createEquipmentListPanel("Hospital");
	    tabbedPane.addTab("Equipment", Equipments);
	    
	    JPanel Rooms = createRoomListPanel("Hospital");
	    tabbedPane.addTab("Rooms", Rooms);
	    
		return tabbedPane;
	}
	
	private JPanel createPatientsListsPanel(String hospitalName) {
		JPanel patientsPanel = new JPanel(new BorderLayout(10, 10));
	    patientsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+"'s Patients List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
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
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel patientsName = new JPanel();
		patientsName.add(new JLabel("Patient's Name:"));
		patientsName.add(new JTextField(10));
		
		JPanel patientsIllness = new JPanel();
		patientsIllness.add(new JLabel("Patient's Illness(es):"));
		patientsIllness.add(new JTextField(10));
		
		form.add(patientsName, gbc);
		form.add(patientsIllness, gbc);
		form.add(new JButton("submit"));
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
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[] {i, "Patient" + i, 100, "someting is wrong"});
    	}
    	JScrollPane scrollPane = new JScrollPane(patientsTable);
    	
    	return scrollPane;
    }
	
    private JPanel createStaffListPanel(String hospitalName) {
		JPanel staffPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+"'s Staff List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
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
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "Staff " + i, "Doctor"});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(staffTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewStaffDialog() {
		JDialog dialog = new JDialog(frame, "Add New Staff", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel StaffName = new JPanel();
		StaffName.add(new JLabel("Name:"));
		StaffName.add(new JTextField(10));
		
		JPanel staffPosition = new JPanel();
		staffPosition.add(new JLabel("Position:"));
		staffPosition.add(new JTextField(10));
		
		form.add(StaffName, gbc);
		form.add(staffPosition, gbc);
		form.add(new JButton("submit"));
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}
	
	private JPanel createEquipmentListPanel(String hospitalName) {
		JPanel equipmentsPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+"'s Equipments List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
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
    	String[] columnNames = {"ID", "Name", "Location"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable equipmentTable = new JTable(model);
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "item " + i, 123});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(equipmentTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewEquipmentDialog() {
		JDialog dialog = new JDialog(frame, "Add New Staff", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel equipmentName = new JPanel();
		equipmentName.add(new JLabel("Item Name:"));
		equipmentName.add(new JTextField(10));
		
		JPanel equipmentlocation = new JPanel();
		equipmentlocation.add(new JLabel("Item Location:"));
		equipmentlocation.add(new JTextField(10));
		
		form.add(equipmentName, gbc);
		form.add(equipmentlocation, gbc);
		form.add(new JButton("submit"));
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}

	private JPanel createRoomListPanel(String hospitalName) {
		JPanel roomsPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(hospitalName+"'s Room List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(title);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JTextField searchText = new JTextField(10);
		JButton searchButton = new JButton("Search");
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
    	String[] columnNames = {"ID", "Room Number", "Floor Number", "Availability"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	
    	JTable roomTable = new JTable(model);
    	
    	for(int i = 0; i < 100; i++) {
    		model.addRow(new Object[]{i, "room " + i, 1, "is available"});
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(roomTable);
    	return scrollPane;
    	
	}
	
	private JDialog addNewRoomDialog() {
		JDialog dialog = new JDialog(frame, "Add New Room", true);
		
		JPanel form  = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		JPanel roomNumber = new JPanel();
		roomNumber.add(new JLabel("Room Number:"));
		roomNumber.add(new JTextField(10));
		
		JPanel floorNumber = new JPanel();
		floorNumber.add(new JLabel("Floor Number:"));
		floorNumber.add(new JTextField(10));
		
		form.add(roomNumber, gbc);
		form.add(floorNumber, gbc);
		form.add(new JButton("submit"));
        dialog.add(form);
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this.frame);
        
        dialog.setVisible(true);
        
        return dialog;
	}

}
