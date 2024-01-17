import java.io.Serializable;

public class Transaction implements Serializable{
   private String transactionId;
   private String patientId;
   private String description;
   private double amount;
   private String date;
   private String paymentMethod;
   private String creditOrDebit;
   
   public Transaction(String transactionId, String patientId, String description, double amount, String date, String paymentMethod, String creditOrDebit) {
	   this.transactionId = transactionId;
	   this.patientId = patientId;
	   this.description = description;
	   this.amount = amount;
	   this.date = date;
	   this.paymentMethod = paymentMethod;
	   this.creditOrDebit = creditOrDebit;
   }
   
   public String getTransactionId() {
	   return this.transactionId;
   }
   
   public String getPatientId() {
	   return this.patientId;
   }
   public String getDescription() {
	   return this.description;
   }
   public double getAmount() {
	   return this.amount;
   }
   
   public String getDate() {
	   return this.date;
   }
   public String getPaymentMethod() {
	   return this.paymentMethod;
   }
   public String getCreditOrDebit() {
	   return this.creditOrDebit;
   }
}
