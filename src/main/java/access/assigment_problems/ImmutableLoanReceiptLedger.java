package access.assigment_problems;
import java.util.Arrays;
public class ImmutableLoanReceiptLedger {
 static class LoanReceipt {
  private final String memberId; private final String[] bookIds;
  public LoanReceipt(String memberId,String[] bookIds){this.memberId=memberId;this.bookIds=Arrays.copyOf(bookIds,bookIds.length);}
  String getMemberId(){return memberId;} String[] getBookIds(){return Arrays.copyOf(bookIds,bookIds.length);}
  LoanReceipt withCorrectedBookId(int index,String newId){String[] copy=getBookIds();copy[index]=newId;return new LoanReceipt(memberId,copy);}
 }
 static class ReferenceOnlyLoanReceipt extends LoanReceipt {
  final String roomNumber;
  ReferenceOnlyLoanReceipt(String memberId,String[] bookIds,String roomNumber){super(memberId,bookIds);this.roomNumber=roomNumber;}
 }
 static class CirculationLedger {
  static String branchCode;
  static {branchCode="PT-LIB"; }
  static String processNightlyCirculation(LoanReceipt[] receipts){int processed=0,nulls=0,reference=0,regular=0;for(LoanReceipt r:receipts){if(r==null){nulls++;continue;}processed++;if(r instanceof ReferenceOnlyLoanReceipt)reference++;else regular++;}return processed+" processed | "+nulls+" null skipped | "+reference+" reference-only | "+regular+" regular";}
 }
 public static void main(String[] args){LoanReceipt r=new LoanReceipt("LIB-8841",new String[]{"BK-100","BK-101"});String[] ids=r.getBookIds();ids[0]="HACKED";System.out.println(Arrays.toString(r.getBookIds()));LoanReceipt corrected=r.withCorrectedBookId(1,"BK-102");System.out.println(Arrays.toString(corrected.getBookIds()));System.out.println(CirculationLedger.processNightlyCirculation(new LoanReceipt[]{new ReferenceOnlyLoanReceipt("LIB-001",new String[]{"BK-200"},"Reading Room 3"),null,new LoanReceipt("LIB-002",new String[]{"BK-201"})}));}
}