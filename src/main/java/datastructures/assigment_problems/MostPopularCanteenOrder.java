package datastructures.assigment_problems;
import java.util.*;
public class MostPopularCanteenOrder {
 static String[] mostPopular(String[] orders){Map<String,Integer> counts=new HashMap<>();for(String order:orders)counts.put(order,counts.getOrDefault(order,0)+1);String best=orders[0];for(String order:orders)if(counts.get(order)>counts.get(best))best=order;return new String[]{best,String.valueOf(counts.get(best))};}
 public static void main(String[] args){String[] result=mostPopular(new String[]{"tea","coffee","coffee","tea"});System.out.println("("+result[0]+", "+result[1]+")");System.out.println("Hash-map approach: O(n) time and O(u) extra space; repeated full scans: O(n^2) time.");}
}