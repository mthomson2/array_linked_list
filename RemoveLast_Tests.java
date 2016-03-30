// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar RemoveLast_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar RemoveLast_Tests  # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class RemoveLast_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("RemoveLast_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_RemoveLast_Tests_Tests1(){
   // test ArrayLinkedList removeLast()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    String r = L.removeLast();
    if (!(r.equals("one")))
      failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: Remove one returns one \n"+
          "Actual: " + "Remove one returns " + r + "\n"+"");
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList removeLast:\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=2)
      failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: array.size() = 2 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != -1 || head.data != null)){
       failFmt("ArrayLinkedList removeFirst():\n"+
          "Expect: head.previous==-1+ head.next==-1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=-1 || n1.next != -1 || n1.data != null){
       failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: n1.previous=-1+ n1.next==-1 n1.data == null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     if(L.size!=0 || L.numberEmpty != 1 || L.firstEmpty != 1 || L.head!=0 || L.tail != 0){
       failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: L.size=0, L.numberEmpty = 1, L.firstEmpty = 1, L.head=0, L.tail = 0 \n"+
          "Actual: "+"L.size="+L.size+" L.numberEmpty="+L.numberEmpty+" L.firstEmpty="+L.firstEmpty
             +"L.head="+L.head+" L.tail="+L.tail+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_RemoveLast_Tests2(){
   // test ArrayLinkedList removeLast()
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add("one"); 
    String r = L.removeLast();
    L.add("two");
    if (!(r.equals("one")))
      failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: Remove one returns true \n"+
          "Actual: " + "Remove one returns " + r + "\n"+"");
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=2)
      failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: array.size() = 2 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList remove(i,e):\n"+
          "Expect: head.previous==-1+ head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != -1 || (!(n1.data.equals("two")))){
       failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: n1.previous==0+ n1.next==-1 n1.data == two \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     if(L.size!=1 || L.numberEmpty != 0 || L.firstEmpty != -1 || L.head!=0 || L.tail != 1){
       failFmt("ArrayLinkedList removeLast():\n"+
          "Expect: L.size=1, L.numberEmpty = 0, L.firstEmpty = -1, L.head=0, L.tail = 1 \n"+
          "Actual: "+"L.size="+L.size+" L.numberEmpty="+L.numberEmpty+" L.firstEmpty="+L.firstEmpty
             +"L.head="+L.head+" L.tail="+L.tail+"\n"+"");
     }
  }

}

