// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar PositionOf_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar PositionOf_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class PositionOf_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("PositionOf_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_PositionOf_Tests1(){
   // test ArrayLinkedList PositionOf(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    int index = L.positionOf("one");
    if (index!=1)
      failFmt("ArrayLinkedList positionOf:\n"+
          "Expect: positionOf one = 1 \n"+
          "Actual: " + "positionOf one = " + index + "\n"+"");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_PositionOf_Tests2(){
   // test ArrayLinkedList PositionOf(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    L.add("two");
    int index = L.positionOf("two");
    if (index!=2)
      failFmt("ArrayLinkedList positionOf:\n"+
          "Expect: positionOf two = 2 \n"+
          "Actual: " + "positionOf two = " + index + "\n"+"");
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_PositionOf_Tests3(){
   // test ArrayLinkedList PositionOf(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    int index = L.positionOf("two");
    if (index!=-1)
      failFmt("ArrayLinkedList positionOf:\n"+
          "Expect: positionOf two = -1 \n"+
          "Actual: " + "positionOf two  = " + index + "\n"+"");
  }

}

