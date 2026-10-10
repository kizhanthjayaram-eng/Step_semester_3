package inheritance.assigment_problems;
public class MonthlyAttendanceAnnouncer {
 static class GymMember {int sessions;String displayInfo(){return "Standard | Sessions: "+sessions;}}
 static class PremiumMember extends GymMember {String trainer;PremiumMember(String trainer){this.trainer=trainer;}@Override String displayInfo(){return "Premium | Trainer: "+trainer+" | Sessions: "+sessions;}}
 static String batchPrint(GymMember[] members){StringBuilder out=new StringBuilder();for(GymMember m:members){out.append(m.displayInfo());if(m instanceof PremiumMember){PremiumMember p=(PremiumMember)m;out.append(" [Trainer via downcast: ").append(p.trainer).append("]");}out.append(" | ");}return out.toString();}
 public static void main(String[] args){System.out.println(batchPrint(new GymMember[]{new GymMember(),new PremiumMember("Coach Riya")}));}
}