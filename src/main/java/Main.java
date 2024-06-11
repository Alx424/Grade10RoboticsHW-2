import java.util.*;
import java.awt.Desktop;
import java.net.URI;

public class Main {
  public static void main(String[] args) {
    // Make a function that takes in input and checks that it's a valid website address. The specific rules for a valid website address, you decide + Google.

    // Part 2: Open the website.
    System.out.println( isValidWebsiteAddress("https://google.com"));
    problemFive();
    //problemTen();
    List<Double> list = new ArrayList<Double>();
    for (int i = 0; i <= 15; i++) list.add((double)i);
    List<Double> cappedVals = getCappedVals(list, 3, 6);
    System.out.println(cappedVals);

    //Running insertStr()
    List<String> list2 = new ArrayList<String>();
    list2.add("aae");
    list2.add("alex");
    list2.add("dab");
    insertStrIntoList(list2, "amber");

    // Running flattenList()
    List<Double> smallList1 = new ArrayList<Double>();
    smallList1.add(1.);
    smallList1.add(2.);
    smallList1.add(3.);
    List<Double> smallList2 = new ArrayList<Double>();
    smallList2.add(4.);
    smallList2.add(5.);
    List<Double> smallList3 = new ArrayList<Double>();
    smallList3.add(6.);
    List<List<Double>> bigList = new ArrayList<List<Double>>();
    bigList.add(smallList1);
    bigList.add(smallList2);
    bigList.add(smallList3);
    List<Double> flattenedList = flattenList(bigList);
    System.out.println(flattenedList);
  }
  
  public static boolean isValidWebsiteAddress(String input) {

    // replit.com - okay
    // poop - not okay -- apparently poop.com is a virus lol
    // https | www.google.com - okay
    // http://www.google.com - okay
    // aaa://not_allowed.com
    
    String protocol = "";
    String address = "";
    String[] allowedDomains = {"com", "ca", "net"};
    //separating the protocol and website address 
    if(input.contains("://")) {
      System.out.println("URL contains a protocol symbol");
      String[] splitInput = input.split("://");
      protocol = splitInput[0];
      address = splitInput[1];
    } else { 
      protocol = "https";
      address = input;
    }
    System.out.println("Protocol: " + protocol);
    System.out.println("Anchor: " + address);

    //checking if protocol is valid
    if(!protocol.equals("http") && !protocol.equals("https")) {
     return false;
    }
    int invalidDomainFlag = 0;
    //check sections of address separated by periods
    String[] separatedAddress = address.split("\\.");
    System.out.println(separatedAddress.length);
    for(int i = 0; i < separatedAddress.length; i++) {
      System.out.println("Testing: " + separatedAddress[i]);
      if(separatedAddress[i].equals("www") && i != 0) {
        // www. is not in the right place
        return false;
      }
      for(String s : allowedDomains) {
        // checking if the domain is in the wrong place
        if(separatedAddress[i].equals(s)) {
          if(i != (separatedAddress.length-1)) {
            return false;
          }
          System.out.println("Part of address matches with: " + s);
        }
        //checking if there's a valid domain and if it's at the end
        if(i == separatedAddress.length-1 && !separatedAddress[i].equals(s)) { 
          invalidDomainFlag++;
          System.out.println("Domain flag: " + invalidDomainFlag);
        }
        if(invalidDomainFlag == allowedDomains.length) return false;
      }
    }
    /*Desktop desktop = Desktop.getDesktop();
    try {
      desktop.browse(URI.create(input));
    } catch (IOException e) {
      e.printStackTrace();
    }
    */
  try {
    URI uri = new URI(input);
    Desktop.getDesktop().browse(uri);
  } catch (Exception evt) {
    System.out.println("Unable to open web  addess");
  }
  return true;
  }

  public static void problemFive() {
    for(int i = 20; i<=1000000000; i++) {
      if(i%11==0 && i%12==0 && i%13==0 && i%14==0 && i%15==0 && i%16==0 && i%17==0 && i%18==0 && i%19==0 && i%20==0) {
        System.out.println(i);
        return;
      }
    }
    return;
  }

  static long count = 0;
  static int num = 2;
  static long sum = 0;
  static boolean isPrime = false;
  public static void problemTen() {
    while(num <= 2000000) {
      //if (pcount%10000==0) System.out.println("Count:" + pcount);
      //System.out.println("Trying: " + pnum);
      if (num == 2) {
        //System.out.println("Prime found: " + num);
        sum += num;
        count++;
      }
      for(int d = 2; d < num; d++) {
        //System.out.println("Testing with: " + d);
        if(num%d==0 || num == 2) {
          isPrime = false;
          break;
        } else {
          isPrime = true;
        }
      }
      if (isPrime == true) {
        //System.out.println("Prime found: " + num);
        sum += num;
        count++;
      } else {
        //System.out.println( pnum + " is not a prime");
      }
      num ++;
    }
    System.out.println("Sum: " + sum);
  }

  static List<Double> getCappedVals(List<Double> initialNums, double min, double max) {
    List<Double> ans = new ArrayList<Double>();
    for(double num : initialNums) {
      if(num < min) {
        ans.add(min);
      } else if (num > max) {
        ans.add(max);
      } else {
        ans.add(num);
      }
    }
    return ans;
  }
  
  static void insertStrIntoList(List<String> list, String str) {
    int i = 0;
    int j = 0;
    List<String> ans = new ArrayList<String>();
    char[] splitStr = str.toUpperCase().toCharArray();
    boolean done = false;
    while (i < list.size()) {
      j = 0;
      char[] splitListStr = list.get(i).toUpperCase().toCharArray();
      while(j < (splitListStr.length < splitStr.length?splitListStr.length:splitStr.length) && !done) {
        System.out.println("Comparing " + splitStr[j] + " from " + str + " and " + splitListStr[j] + " from " + list.get(i));
        if((int)splitStr[j] > (int)splitListStr[j]) {
          ans.add(list.get(i));
          System.out.println("Added " + list.get(i));
          System.out.println(ans);
          System.out.println("j: " + j);
          System.out.println("i: " + i);
          break;
        } else if((int)splitListStr[j] > (int)splitStr[j])
        {
          ans.add(str);
          ans.add(list.get(i));
          System.out.println("Added " + str);
          System.out.println(ans);
          System.out.println("j: " + j);
          System.out.println("i: " + i);
          done = true;
        } else if((int)splitStr[j] == (int)splitListStr[j]) {
          System.out.println("Same values encountered: " + splitStr[j]);
          System.out.println(ans);
          System.out.println("j: " + j);
          System.out.println("i: " + i);
          j++;
        } else {
          System.out.println("Error");
        }
      }
      i++;
      while(done && i < list.size()){
        // add the rest
        ans.add(list.get(i));
        i++;
      }
    }
    System.out.println("Final answer: " + ans);
  }

  static List<Double> flattenList(List<List<Double>> listOfLists) {
    // Return a "flattened" list.
      // Example: If listOfLists if {{1, 2, 3}, {4, 5}, {6}}, the returned list should be {1, 2, 3, 4, 5, 6}
    List<Double> flattenedList = new ArrayList<Double>();
    for(List<Double> list : listOfLists) {
      for(double d : list) {
        flattenedList.add(d);
      }
    }
    
    return flattenedList;
  }
}