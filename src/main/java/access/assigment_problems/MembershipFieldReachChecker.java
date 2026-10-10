package access.assigment_problems;
public class MembershipFieldReachChecker {
 static String classifyAccess(String modifier,String context){
  if(!modifier.equals("private")&&!modifier.equals("default")&&!modifier.equals("protected")&&!modifier.equals("public"))return "DENIED";
  if(context.equals("SAME_CLASS"))return "ALLOWED";
  if(modifier.equals("public"))return "ALLOWED";
  if(modifier.equals("private"))return "DENIED";
  if(modifier.equals("default"))return context.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
  if(modifier.equals("protected"))return context.equals("SAME_PACKAGE")||context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")?"ALLOWED":"DENIED";
  return "DENIED";
 }
 static String summarizeByModifier(String[][] attempts){
  String[] mods={"private","default","protected","public"};StringBuilder out=new StringBuilder();
  for(String mod:mods){int yes=0,no=0;for(String[] a:attempts)if(a[0].equals(mod)){if(classifyAccess(a[0],a[1]).equals("ALLOWED"))yes++;else no++;}
   if(out.length()>0)out.append(" | ");out.append(mod).append(": ").append(yes).append(" allowed / ").append(no).append(" denied");
  }return out.toString();
 }
 static String firstDeniedAttempt(String[][] attempts){
  for(int i=0;i<attempts.length;i++)if(classifyAccess(attempts[i][0],attempts[i][1]).equals("DENIED"))return attempts[i][0]+" via "+attempts[i][1]+" (attempt #"+(i+1)+")";
  return "None Denied";
 }
 public static void main(String[] args){
  String[][] attempts={{"private","SAME_CLASS"},{"private","SAME_PACKAGE"},{"default","SAME_PACKAGE"},{"default","DIFFERENT_PACKAGE"},{"protected","SAME_PACKAGE"},{"protected","SAME_CLASS"},{"public","DIFFERENT_PACKAGE"}};
  System.out.println(summarizeByModifier(attempts));
 }
}