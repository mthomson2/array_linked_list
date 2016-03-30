// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar Get_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar Get_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Get_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Get_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_get_Tests1(){
   // test ArrayLinkedList get(i)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    String r = L.get(0);
    if (!(r.equals("one")))
      failFmt("ArrayLinkedList get:\n"+
          "Expect: get(0) = one \n"+
          "Actual: " + "get(0) = " + r + "\n");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_Tests2(){
   // test ArrayLinkedList get(i)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    L.add("two");
    String r = L.get(1);
    if (!r.equals("two"))
      failFmt("ArrayLinkedList get:\n"+
          "Expect: get(1) = two \n"+
          "Actual: " + "get(1) = " + r + "\n");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_get_Tests3(){
   // test ArrayLinkedList add(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    boolean thrown = false;
    try {
      String r = L.get(1);
    }
    catch (IndexOutOfBoundsException e) {
      thrown = true;
    }
    if (!thrown)
      failFmt("ArrayLinkedList get:\n"+
          "Expect: IndexOutOfBoundsException.  \n"+
          "Actual: exception IndexOutOfBoundsException not thrown. \n");
  }

}

