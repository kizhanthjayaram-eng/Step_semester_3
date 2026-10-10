package inheritance.assigment_problems;
public class MembershipNumbersReferralSettlement {
 static class GymMember {
  private static int counter=2000;private static int enrolled=0;final String membershipNumber;private int feesPaid;
  GymMember(int monthlyFee){counter++;enrolled++;membershipNumber="GYM-"+counter;}
  void payFee(int amount){feesPaid+=amount;}void payFee(int amount,String mode){System.out.println("Payment mode: "+mode);payFee(amount);}int getFeesPaid(){return feesPaid;}
  static int getMembersEnrolled(){return enrolled;}
  static boolean isValidReferralCode(String code){return code!=null&&code.length()==4&&code.charAt(0)=='G'&&Character.isDigit(code.charAt(1))&&Character.isDigit(code.charAt(2))&&Character.isUpperCase(code.charAt(3));}
 }
 static class GroupClassMember extends GymMember {String className;GroupClassMember(int fee,String className){super(fee);this.className=className;}}
 static String processWeeklyCheckIn(GymMember[] members){int processed=0,nulls=0,group=0,individual=0;for(GymMember m:members){if(m==null){nulls++;continue;}processed++;if(m instanceof GroupClassMember)group++;else individual++;}return processed+" processed | "+nulls+" null skipped | "+group+" group | "+individual+" individual";}
 public static void main(String[] args){GymMember m=new GymMember(1000);System.out.println(m.membershipNumber);System.out.println(GymMember.isValidReferralCode("G45B"));m.payFee(500);m.payFee(500,"UPI");System.out.println(m.getFeesPaid());}
}