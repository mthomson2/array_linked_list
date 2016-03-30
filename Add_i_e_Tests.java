// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar Add_i_e_Tests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar Add_i_e_Tests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Add_i_e_Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Add_i_e_Tests");
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
  @Test(timeout=1000) public void ArrayLinkedList_add_i_e_1(){
    // test add(e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>();  
    if(L.firstEmpty != -1 || L.numberEmpty != 0 || L.head != 0
            || L.tail != 0 || L.size != 0){
       failFmt("ArrayLinkedList constructor:\n"+
          "Expect: firstEmpty=-1, numberEmpty=head=tail=size=0 \n"+
          "Actual: "+"firstEmpty="+L.firstEmpty+" numberEmpty="+L.numberEmpty+" head="+L.head
           + " tail=" + L.tail + " size=" + L.size +"\n"+"");
     }

  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_add_i_e_2(){
   // test ArrayLinkedList add(0,e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add(0,"one"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=2)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: array.size() = 2 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != -1 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: n1.previous==0 n1.next==-1 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_add_i_e_3(){
   // test ArrayLinkedList add(0,e1);add(1,e2);
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add(0,"one"); 
    L.add(1,"two"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=3)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: array.size() = 3 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != 2 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: n1.previous==0 n1.next==2 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     Node<String> n2 = array.get(2);
     if(n2.previous!=1 || n2.next != -1 || !(n2.data.equals("two"))){
       failFmt("ArrayLinkedList add(i,e);add(e):\n"+
          "Expect: n2.previous==1 n2.next==-1 n2.data == two \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ArrayLinkedList_add_i_e_4(){
   // test ArrayLinkedList add(0,e1);add(1,e2);add(1,e)
    ArrayLinkedList<String> L = new ArrayLinkedList<String>(); 
    L.add(0,"one"); 
    L.add(1,"three"); 
    L.add(1,"two"); 
    ArrayList<Node<String>> array = L.array;
    if (array == null)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: non-null array \n"+
          "Actual: " + "null array \n"+"");
    if (array.size()!=4)
      failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: array.size() = 4 \n"+
          "Actual: " + "array.size() = " + array.size() + "\n"+"");
     Node<String> head = array.get(0);
     if((head.previous !=-1 || head.next != 1 || head.data != null)){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: head.previous==-1 head.next==1 head.data == null \n"+
          "Actual: "+"head.previous=="+head.previous+" head.next=="+head.next+" head.data=="+head.data+"\n"+"");
     }
     Node<String> n1 = array.get(1);
     if(n1.previous!=0 || n1.next != 3 || !(n1.data.equals("one"))){
       failFmt("ArrayLinkedList add(i,e):\n"+
          "Expect: n1.previous==0 n1.next==3 n1.data == one \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
     Node<String> n2 = array.get(2);
     if(n2.previous!=3 || n2.next != -1 || !(n2.data.equals("three"))){
       failFmt("ArrayLinkedList add(i,e);add(i,e):\n"+
          "Expect: n2.previous==3 n2.next==-1 n2.data == three \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }
     Node<String> n3 = array.get(3);
     if(n3.previous!=1 || n3.next !=2 || !(n3.data.equals("two"))){
       failFmt("ArrayLinkedList add(i,e);add(i,e)add(i,e):\n"+
          "Expect: n3.previous==1 n3.next==2 n3.data == two \n"+
          "Actual: "+"n3.previous=="+n3.previous+" n3.next=="+n3.next+" n3.data=="+n3.data+"\n"+"");
     }
  }

}

