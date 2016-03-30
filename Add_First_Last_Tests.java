// CHANGELOG: 
// - Fixed a bug in ArrayLinkedList_Add_First2() 2/11 at 9am.

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar Add_First_Last_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar Add_First_Last_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Add_First_Last_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Add_First_Last_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_Add_First1(){
   // test ArrayLinkedList addFirst(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.addFirst("one"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=2)
      failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: array.size() = 2 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != -1 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: n1.previous==0 n1.next==-1 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_Add_First2(){
   // test ArrayLinkedList addFirst
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.addFirst("one"); 
    L.addFirst("two"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=3)
      failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: array.size() = 3 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 2 || head.data != null)){
       failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: head.previous==-1 head.next==2 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=2 || n1.next != -1 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: n1.previous==2 n1.next==-1 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     Node<String> n2 = array.get(2);
     if(n2.previous!=0 || n2.next != 1 || !(n2.data.equals("two"))){
       failFmt("ArrayLinkedList addFirst(e):\n"+
          "Expect: n2.previous==0 n2.next==1 n2.data == two \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_Add_Last1(){
   // test ArrayLinkedList addLast(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.addLast("one"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=2)
      failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: array.size() = 2 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != -1 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: n1.previous==0 n1.next==-1 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_Add_Last2(){
   // test ArrayLinkedList addLast(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.addLast("one"); 
    L.addLast("two"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=3)
      failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: array.size() = 3 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != 2 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: n1.previous==0 n1.next==2 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     Node<String> n2 = array.get(2);
     if(n2.previous!=1 || n2.next != -1 || !(n2.data.equals("two"))){
       failFmt("ArrayLinkedList addLast(e):\n"+
          "Expect: n2.previous==1 n2.next==-1 n2.data == two \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }
  }



}

