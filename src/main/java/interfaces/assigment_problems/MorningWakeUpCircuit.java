package interfaces.assigment_problems;
public class MorningWakeUpCircuit {
 interface Ringable{String ring();}
 static class AlarmClock implements Ringable{String time;AlarmClock(String time){this.time=time;}public String ring(){return "Alarm ringing for "+time;}}
 static class Doorbell implements Ringable{String location;Doorbell(String location){this.location=location;}public String ring(){return "Doorbell ringing at "+location;}}
 static void ringAll(Ringable[] devices){for(Ringable d:devices)System.out.println(d.ring());}
 public static void main(String[] args){ringAll(new Ringable[]{new AlarmClock("7:00 AM"),new Doorbell("Front Door")});}
}