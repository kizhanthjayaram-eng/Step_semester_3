package interfaces.assigment_problems;
public class BackyardToolshedRoutine {
 static abstract class GardenTool {abstract String use();}
 static class CuttingTool extends GardenTool {@Override String use(){return "Using the tool in the garden, blade sharpened first";}}
 static class Pruner extends CuttingTool {@Override String use(){return super.use()+", then trimming branches precisely";}}
 public static void main(String[] args){System.out.println(new CuttingTool().use());System.out.println(new Pruner().use());}
}