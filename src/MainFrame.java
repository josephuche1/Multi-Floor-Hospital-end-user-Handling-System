import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame {
	private JFrame frame;
	private JPanel createAccount;
	
	
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
		
		this.createAccount  = this.createAccountForm();
		this.frame.add(createAccount);
		
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
		createAccount.add(submitOrLogin);
		
		
		return createAccount;
		
	}
}
