package datastructures.assigment_problems;
public class TicketPriceSlotFinder {
 static int findSlot(int[] prices,int newPrice){int low=0,high=prices.length-1;while(low<=high){int mid=low+(high-low)/2;if(prices[mid]==newPrice)return mid;if(prices[mid]<newPrice)low=mid+1;else high=mid-1;}return low;}
 public static void main(String[] args){System.out.println(findSlot(new int[]{120,150,200,260},210));System.out.println("Binary search: O(log n) time, O(1) extra space; linear scan: O(n) time.");}
}