import java.io.Serializable;

public class HospitalFinancial_Accounts implements Serializable{
    private double totalIncome;
    private double totalExpenses;
    public double balance;

    // Default constructor
    public HospitalFinancial_Accounts() {
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.balance = 0.0;
    }

    // Constructor with totalIncome and totalExpenses
    public HospitalFinancial_Accounts(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.balance = totalIncome - totalExpenses;
    }

    // Constructor with balance
    public HospitalFinancial_Accounts(double balance) {
        this.totalIncome = balance;
        this.totalExpenses = 0.0;
        this.balance = balance;
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

    // Overriding toString method
    @Override
    public String toString() {
        return "Total Income: " + this.totalIncome + ", Total Expenses: " + this.totalExpenses + ", Balance: " + this.balance;
    }
}