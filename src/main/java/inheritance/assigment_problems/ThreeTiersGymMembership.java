package inheritance.assigment_problems;
public class ThreeTiersGymMembership {
 static class GymMember {int sessions;String id;GymMember(String id){this.id=id;}void attendSession(){sessions++;}int getSessionsAttended(){return sessions;}String displayInfo(){return "Standard Member | Sessions: "+sessions;}}
 static class PremiumMember extends GymMember {String trainer;PremiumMember(String id,String trainer){super(id);this.trainer=trainer;}@Override String displayInfo(){return "Premium Member | Trainer: "+trainer+" | Sessions: "+sessions;}}
 static class EliteMember extends PremiumMember {String locker;EliteMember(String id,String trainer,String locker){super(id,trainer);this.locker=locker;}@Override String displayInfo(){return "Elite Member | Trainer: "+trainer+" | Locker: "+locker+" | Sessions: "+sessions;}}
 static class GroupClassMember extends GymMember {String className;GroupClassMember(String id,String className){super(id);this.className=className;}@Override String displayInfo(){return "Group Class Member | Class: "+className+" | Sessions: "+sessions;}}
 static String classifyGeneration(GymMember m){if(m instanceof EliteMember)return "Multilevel descendant (3 generations deep)";if(m instanceof GroupClassMember)return "Hierarchical sibling (independent branch)";if(m instanceof PremiumMember)return "Second generation";return "Base member";}
 static int getTotalSessionsAttended(GymMember[] members){int total=0;for(GymMember m:members)total+=m.getSessionsAttended();return total;}
 public static void main(String[] args){EliteMember e=new EliteMember("MEM3","Coach Arjun","L12");System.out.println(e.displayInfo());System.out.println(classifyGeneration(e));}
}