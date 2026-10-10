package interfaces.assigment_problems;
public class SkylineDeliveryFleet {
 static abstract class Drone {abstract String fly();}
 interface Trackable {String getLocation();}
 static class DeliveryDrone extends Drone implements Trackable {String id;DeliveryDrone(String id){this.id=id;}String fly(){return id+" flying";}public String getLocation(){return id+" at Sector 4";}}
 static class ScoutDrone extends Drone {String id;ScoutDrone(String id){this.id=id;}String fly(){return id+" scouting";}}
 static class GroundRobot implements Trackable {String id;GroundRobot(String id){this.id=id;}public String getLocation(){return id+" at Sector 4";}}
 static String getLocationIfTrackable(Object o){if(o instanceof Trackable)return ((Trackable)o).getLocation();return "Tracking not available";}
 public static void main(String[] args){System.out.println(getLocationIfTrackable(new DeliveryDrone("DR-1")));System.out.println(getLocationIfTrackable(new ScoutDrone("SC-1")));System.out.println(getLocationIfTrackable(new GroundRobot("GR-1")));}
}