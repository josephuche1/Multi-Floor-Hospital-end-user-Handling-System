import java.io.Serializable;
import java.util.ArrayList;


/**
 * HospitalFinancial_Accounts is a class that manages the financial records of a hospital.
 * It tracks the total income, expenses, and maintains a list of all financial transactions.
 * The balance is calculated as the difference between total income and expenses.
 * This class is essential for maintaining a clear financial picture of the hospital's operations.
 * It implements Serializable to allow financial data to be saved and restored as needed.
 * 
 * @author Uche Joseph
 * @version 1.0
 */
public class HospitalFinancial_Accounts implements Serializable{
    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private ArrayList<Transaction> transactions;
    private int transactionCount;
    

    /**
     * Default constructor initializes financial accounts with zero values and an empty transaction list.
     */
    public HospitalFinancial_Accounts() {
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
        transactionCount = 0;
    }

    /**
     * Constructor that initializes the accounts with specified total income and expenses.
     * The balance is calculated and a new transaction list is created.
     *
     * @param totalIncome    The initial total income.
     * @param totalExpenses  The initial total expenses.
     */
    public HospitalFinancial_Accounts(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.balance = totalIncome - totalExpenses;
        this.transactions = new ArrayList<Transaction>();
        transactionCount = 0;
    }

    /**
     * Constructor that initializes the accounts with a specified balance.
     * Assumes total expenses are zero and sets total income to the balance.
     *
     * @param balance The initial balance of the accounts.
     */
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
    

    // Accessor and mutator methods for the class fields
    // ...

    /**
     * Adds a financial transaction to the records and updates the transaction count.
     *
     * @param transaction The Transaction object to be added.
     */
    public void addTransaction(Transaction transaction) {
    	transactions.add(transaction);
    	transactionCount++;
    }
    

    /**
     * Retrieves the list of all financial transactions.
     *
     * @return An ArrayList of Transaction objects.
     */
    public ArrayList<Transaction> getTransactions(){
    	return this.transactions;
    }
    


    /**
     * Provides a string representation of the financial status of the hospital.
     *
     * @return A string detailing total income, expenses, and current balance.
     */
    @Override
    public String toString() {
        return "Total Income: " + this.totalIncome + ", Total Expenses: " + this.totalExpenses + ", Balance: " + this.balance;
    }

    /**
     * Retrieves the count of financial transactions.
     *
     * @return The number of transactions recorded.
     */
	public int getTransactionCount() {
		// TODO Auto-generated method stub
		return this.transactionCount;
	}
}