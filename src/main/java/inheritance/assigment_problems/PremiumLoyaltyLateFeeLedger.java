package inheritance.assigment_problems;
import java.util.Arrays;
public class PremiumLoyaltyLateFeeLedger {
 static class GymMember {private int[] history=new int[10];private int count;protected void chargeLateFee(int amount){if(count<history.length)history[count++]=amount;}int[] getLateFeeHistory(){return Arrays.copyOf(history,count);}int getTotalLateFees(){int total=0;for(int i=0;i<count;i++)total+=history[i];return total;}}
 static class PremiumMember extends GymMember {@Override protected void chargeLateFee(int amount){super.chargeLateFee(amount/2);}}
 public static void main(String[] args){PremiumMember p=new PremiumMember();p.chargeLateFee(200);System.out.println(p.getTotalLateFees());System.out.println(Arrays.toString(p.getLateFeeHistory()));}
}