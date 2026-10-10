package inheritance.assigment_problems;
public class GymMembershipFoundation {
 static class GymMember {
  protected String memberId; protected int monthlyFee,sessions;
  GymMember(String memberId,int monthlyFee){if(memberId==null||memberId.trim().length()<4)throw new IllegalArgumentException("Invalid member ID");if(monthlyFee<=0)throw new IllegalArgumentException("Fee must be positive");this.memberId=memberId;this.monthlyFee=monthlyFee;}
  void attendSession(){sessions++;} int getSessionsAttended(){return sessions;}
 }
 static class PremiumMember extends GymMember {
  String trainerName;
  PremiumMember(String memberId,int monthlyFee,String trainerName){super(memberId,monthlyFee);this.trainerName=trainerName;}
 }
 static String signUpBatch(String[] ids,int fee){int signed=0,rejected=0;for(String id:ids){try{new GymMember(id,fee);signed++;}catch(IllegalArgumentException e){rejected++;}}return "Signed Up: "+signed+" | Rejected: "+rejected;}
 public static void main(String[] args){PremiumMember p=new PremiumMember("MEM01",2000,"Coach Riya");p.attendSession();p.attendSession();System.out.println(p.getSessionsAttended());System.out.println(signUpBatch(new String[]{"MEM1","GM1","MEM2"," ","MEM3"},1000));}
}