// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar PoolTests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar PoolTests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class PoolTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("PoolTests");
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
  @Test(timeout=1000) public void Node_construct1(){
  // Check construct Node
     Node<String> n1 = new Node<String>();
  
     if(! (n1.previous==-1 && n1.next == -1 && n1.data == null)){
       failFmt("Node constructor:\n"+
          "Expect: +n1.previous==-1+ n1.next==-1 n1.data== null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }


  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Node_init1(){
  // Check init Node
     Node<String> n1 = new Node<String>();
     n1.next= 1; n1.previous=1; n1.data= new String("non-null");
     n1.init();
  
     if(! (n1.previous==-1 && n1.next == -1 && n1.data == null)){
       failFmt("init:\n"+
          "Expect: +n1.previous==-1+ n1.next==-1 n1.data== null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Node_toString1(){
  // Check Node toString
     Node<String> n1 = new Node<String>();
     n1.next= 1; n1.previous=1; n1.data= new String("non-null");
     String actualString = n1.toString().replaceAll("\\s+","");
     String expectedString =  new String("[1,1,non-null]"); 
  
     if(! actualString.equals(expectedString)){
       failFmt("Node toString:\n"+
          "Expect: "+expectedString + "\n"+
          "Actual: "+actualString+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void NodePool_reset1(){
  // Check NodePool reset
     NodePool<String> pool = new NodePool<String>(8);
     Node<String> n1 = new Node<String>();
     n1.next= 1; n1.previous=1; n1.data= new String("non-null");
     n1 = pool.reset(n1);
  
     if(! (n1.previous==-1 && n1.next == -1 && n1.data == null)){
       failFmt("NodePool reset:\n"+
          "Expect: +n1.previous==-1+ n1.next==-1 n1.data== null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void NodePool_create1(){
  // Check NodePoll create
     NodePool<String> pool = new NodePool<String>(8);
     Node<String> n = pool.create();
  
     if(! (n.previous==-1 && n.next == -1 && n.data == null)){
       failFmt("NodePool create:\n"+
          "Expect: +n.previous==-1+ n.next==-1 n.data== null \n"+
          "Actual: "+"n.previous=="+n.previous+" n.next=="+n.next+" n.data=="+n.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ObjectPool_allocate1(){
  // Check allocate Node from empty pool
     NodePool<String> pool = new NodePool<String>(8);
     int actualSize = pool.size();
     if(actualSize != 0){
       failFmt("allocate:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
     Node<String> n = pool.allocate();
  
     if(! (n.previous==-1 && n.next == -1 && n.data == null)){
       failFmt("allocate:\n"+
          "Expect: +n.previous==-1+ n.next==-1 n.data== null \n"+
          "Actual: "+"n.previous=="+n.previous+" n.next=="+n.next+" n.data=="+n.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ObjectPool_release1(){
  // Check release Node
     NodePool<String> p = new NodePool<String>(8);
     int actualSize = p.size();
     if(actualSize != 0){
       failFmt("Pool release:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
     Node<String> n1 = p.allocate();
  
     if(! (n1.previous==-1 && n1.next == -1 && n1.data == null)){
       failFmt("Pool release:\n"+
          "Expect: +n1.previous==-1+ n1.next==-1 n1.data== null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }

     n1.next= 1; n1.previous=1; n1.data= new String("non-null");
     p.release(n1);

     actualSize = p.size();
     if(actualSize != 1){
       failFmt("release:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
 
     Stack<Node<String>> s = p.pool;
     actualSize = s.size();
     if(actualSize != 1){
       failFmt("Pool release:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
     Node<String> n2 = s.pop();
     if(! (n2.previous==-1 && n2.next == -1 && n2.data == null)){
       failFmt("Pool release:\n"+
          "Expect: +n2.previous==-1+ n2.next==-1 n2.data== null \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ObjectPool_allocate2(){
  // Check allocate Node
     NodePool<String> p = new NodePool<String>(8);
     int actualSize = p.size();
     if(actualSize != 0){
       failFmt("allocate:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
     Node<String> n1 = p.allocate();
  
     if(! (n1.previous==-1 && n1.next == -1 && n1.data == null)){
       failFmt("allocate:\n"+
          "Expect: +n1.previous==-1+ n1.next==-1 n1.data== null \n"+
          "Actual: "+"n1.previous=="+n1.previous+" n1.next=="+n1.next+" n1.data=="+n1.data+"\n"+"");
     }

     n1.next= 1; n1.previous=1; n1.data= new String("non-null");
     p.release(n1);

     actualSize = p.size();
     if(actualSize != 1){
       failFmt("allocate:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
 
     Stack<Node<String>> s = p.pool;
     actualSize = s.size();
     if(actualSize != 1){
       failFmt("allocate:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
     Node<String> n2 = s.pop();
     if(! (n2.previous==-1 && n2.next == -1 && n2.data == null)){
       failFmt("allocate:\n"+
          "Expect: +n2.previous==-1+ n2.next==-1 n2.data== null \n"+
          "Actual: "+"n2.previous=="+n2.previous+" n2.next=="+n2.next+" n2.data=="+n2.data+"\n"+"");
     }

     Node<String> n3 = p.allocate();

     actualSize = p.size();
     if(actualSize != 0){
       failFmt("allocate:\n"+
          "Expect: "+ 0 + "\n" +
          "Actual: "+ actualSize +"\n" + "");
     }
  
     if(! (n3.previous==-1 && n3.next == -1 && n3.data == null)){
       failFmt("allocate:\n"+
          "Expect: +n3.previous==-1+ n3.next==-1 n3.data== null \n"+
          "Actual: "+"n3.previous=="+n3.previous+" n3.next=="+n3.next+" n3.data=="+n3.data+"\n"+"");
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void ObjectPool_toString1(){
  // Check Pool toString
     NodePool<String> p = new NodePool<String>(8);
     Node<String> n1 = p.allocate();
     Node<String> n2 = p.allocate();
     p.release(n1);
     p.release(n2);
     String actualString = p.toString().replaceAll("\\s+","");
     String expectedString = "[-1,-1,null], [-1,-1,null]".replaceAll("\\s+","");
     if(! actualString.equals(expectedString)){
       failFmt("ObjectPool toString:\n"+
          "Expect: "+expectedString + "\n"+
          "Actual: "+actualString+"\n"+"");
     }
  }

}

