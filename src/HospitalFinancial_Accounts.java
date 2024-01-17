import java.io.Serializable;
import java.util.ArrayList;

public class HospitalFinancial_Accounts implements Serializable{
    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private ArrayList<Transaction> transactions;
    private int transactionCount;
    

    // Default constructor
    public HospitalFinancial_Accounts() {
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
        transactionCount = 0;
    }

    // Constructor with totalIncome and totalExpenses
    public HospitalFinancial_Accounts(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.balance = totalIncome - totalExpenses;
        this.transactions = new ArrayList<Transaction>();
        transactionCount = 0;
    }

    // Constructor with balance
    public HospitalFinancial_Accounts(double balance) {
        this.totalIncome = balance;
        this.totalExpenses = 0.0;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
        transactionCount = 0;
    }

    // Get total income
    public double getTotalIncome() {
        return this.totalIncome;
    }

    // Get total expenses
    public double getTotalExpenses() {
        return this.totalExpenses;
    }

    // Get balance
    public double getBalance() {
        return this.balance;
    }

    // Add income
    public void addIncome(double income) {
        this.totalIncome += income;
        this.balance += income;
    }

    // Add expenses
    public void addExpenses(double expenses) {
        this.totalExpenses += expenses;
        this.balance -= expenses;
    }
    
    public void addTransaction(Transaction transaction) {
    	transactions.add(transaction);
    	transactionCount++;
    }
    
    public ArrayList<Transaction> getTransactions(){
    	return this.transactions;
    }
    

    // Overriding toString method
    @Override
    public String toString() {
        return "Total Income: " + this.totalIncome + ", Total Expenses: " + this.totalExpenses + ", Balance: " + this.balance;
    }

	public int getTransactionCount() {
		// TODO Auto-generated method stub
		return this.transactionCount;
	}
}