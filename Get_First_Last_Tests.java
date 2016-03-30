// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar Get_First_Last_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar Get_First_Last_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Get_First_Last_Tests{
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Get_First_Last_Tests");
  }
  
  // @Test(timeout=1000,expected=SomeException.class)

  public static void failFmt(String fmt, Object... args){
    fail(String.format(fmt,args));
  }

  static String printArray(Object[] array) {
		StringBuilder B = new StringBuilder();
		B.append("[ ");
		for(Object o: array)
			B.append(o + " ");
		B.append("]");
		return B.toString();
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_first_Tests1(){
   // test ArrayLinkedList getFirst()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    String r = L.getFirst();
    if (!(r.equals("one")))
      failFmt("ArrayLinkedList getFirst:\n"+
          "Expect: getFirst = one \n"+
          "Actual: " + "getFirst = " + r + "\n");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_last_Tests1(){
   // test ArrayLinkedList getLast()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    L.add("two");
    String r = L.getLast();
    if (!r.equals("two"))
      failFmt("ArrayLinkedList getLast:\n"+
          "Expect: getLast = two \n"+
          "Actual: " + "getLast = " + r + "\n");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_first_Tests2(){
   // test ArrayLinkedList getFirst()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    boolean thrown = false;
    try {
      String r = L.getFirst();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    }
    if (!thrown)
      failFmt("ArrayLinkedList getFirst:\n"+
          "Expect: NoSuchElementException.  \n"+
          "Actual: exception NoSuchElementException not thrown. \n");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_last_Tests2(){
   // test ArrayLinkedList getLast()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    boolean thrown = false;
    try {
      String r = L.getLast();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    }
    if (!thrown)
      failFmt("ArrayLinkedList getLast:\n"+
          "Expect: NoSuchElementException.  \n"+
          "Actual: exception NoSuchElementException not thrown. \n");
  }


}

