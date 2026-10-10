package access.assigment_problems;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
public class LibraryMemberJavaBean {
 private String membershipId,name,securityAnswerHash; private boolean premiumMember;
 public LibraryMemberJavaBean(){}
 public String getMembershipId(){return membershipId;}
 public void setMembershipId(String id){if(membershipId==null)membershipId=id;}
 public String getName(){return name;} public void setName(String name){this.name=name;}
 public boolean isPremiumMember(){return premiumMember;} public void setPremiumMember(boolean premium){premiumMember=premium;}
 public void setSecurityAnswer(String answer){
  try{MessageDigest md=MessageDigest.getInstance("SHA-256");byte[] hash=md.digest(answer.getBytes(StandardCharsets.UTF_8));StringBuilder s=new StringBuilder();for(byte b:hash)s.append(String.format("%02x",b));securityAnswerHash=s.toString();}
  catch(Exception e){throw new IllegalStateException(e);}
 }
 public static void main(String[] args){LibraryMemberJavaBean m=new LibraryMemberJavaBean();m.setMembershipId("LIB-8841");m.setName("Priya Nair");m.setPremiumMember(true);m.setMembershipId("FAKE-0000");System.out.println(m.getMembershipId());System.out.println(m.isPremiumMember());m.setSecurityAnswer("BlueMountain");}
}