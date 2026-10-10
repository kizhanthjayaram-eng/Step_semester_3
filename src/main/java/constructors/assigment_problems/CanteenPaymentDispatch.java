package constructors.assigment_problems;
public class CanteenPaymentDispatch {
 static class Payment {double pay(double amount){System.out.println("Paid (cash): Rs "+amount);return amount;}}
 static class CardPayment extends Payment {double payWithProcessingFee(double amount){double total=amount*1.02;System.out.println("Charged (card, incl. fee): Rs "+total);return total;}}
 static double processTransaction(Payment payment,double amount){if(payment instanceof CardPayment)return ((CardPayment)payment).payWithProcessingFee(amount);return payment.pay(amount);}
 public static void main(String[] args){
  Payment[] payments={new CardPayment(),new Payment(),new CardPayment(),new Payment(),new CardPayment()};
  double[] amounts={100,50,200,75,120};double total=0;
  for(int i=0;i<payments.length;i++)total+=processTransaction(payments[i],amounts[i]);
  System.out.println("Total Collected: Rs "+total);
 }
}