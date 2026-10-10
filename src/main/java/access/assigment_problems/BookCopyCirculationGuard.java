package access.assigment_problems;
public class BookCopyCirculationGuard {
 static class BookInventory {
  private final int copiesTotal; private int copiesAvailable;
  BookInventory(int copiesTotal){if(copiesTotal<=0)throw new IllegalArgumentException();this.copiesTotal=copiesTotal;copiesAvailable=copiesTotal;}
  void checkOut(){if(copiesAvailable>0)copiesAvailable--;}
  void checkIn(){if(copiesAvailable<copiesTotal)copiesAvailable++;}
  int getCopiesAvailable(){return copiesAvailable;}
 }
 public static void main(String[] args){BookInventory b=new BookInventory(3);b.checkOut();b.checkOut();b.checkOut();b.checkOut();System.out.println(b.getCopiesAvailable());}
}