// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar Contains_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar Contains_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Contains_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Contains_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_contains_Tests1(){
   // test ArrayLinkedList contains(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    boolean r = L.contains("one");
    if (!r==true)
      failFmt("ArrayLinkedList contains:\n"+
          "Expect: contains one = true \n"+
          "Actual: " + "contains one = " + r + "\n"+"");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_indexOf_Tests2(){
   // test ArrayLinkedList contains(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    L.add("two");
    boolean r = L.contains("one");
    if (!r)
      failFmt("ArrayLinkedList contains:\n"+
          "Expect: contains two = true \n"+
          "Actual: " + "contains two = " + r + "\n"+"");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_contains_Tests3(){
   // test ArrayLinkedList contains(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    boolean r = L.contains("two");
    if (r)
      failFmt("ArrayLinkedList contains:\n"+
          "Expect: contains two = false \n"+
          "Actual: " + "contains two  = " + r + "\n"+"");
  }

}

