package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import tools.AdvancedCalculator;
import tools.Calculator;
import tools.VeryAdvancedCalculator;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
	    B obj = new B();
        obj.show();
        Calculator calc = new Calculator();
        int r1 = calc.add(5, 4);
        int r2 = calc.sub(5, 4);
        System.out.println(r1);
        System.out.println(r2);
        AdvancedCalculator advCalc = new AdvancedCalculator();
        int r3 = advCalc.add(4, 9);
        System.out.println(r3);
        VeryAdvancedCalculator veryAdvCalc = new VeryAdvancedCalculator();
        double r4 = veryAdvCalc.multiply(5, 4);
            System.out.println(r4);
        
        Computer lap = new Laptop();
        Computer desk = new Desktop();
        Developer dev1 = new Developer(lap);
        Developer dev2 = new Developer(desk); 
        dev1.code();
        dev2.code();

        // Enum
        Status [] ss = Status.values();
        for(Status s : ss){
            System.out.println(s + ":" + s.ordinal());
            switch(s){
                case Running:
                    System.out.println("Process Started");
                    break;
                case Pending:
                    System.out.println("Please Wait...");
                    break;
                case Inturupted:
                    System.out.println("Process has been inturupted");
                    break;
                case Failed:
                    System.out.println("Process Failed to complete");
                    break;
                default:
                    System.out.println("Process Completed Successfully");       
            }
        }
        Laptops[] laptops = Laptops.values();
        for(Laptops laptop: laptops){
            System.out.println(laptop.name() + ":" + laptop.ordinal() + ":" + laptop.getPrice());
        }

        FI fi_class = new FI();
        fi_class.add(5, 4);

        // Lambda 
        F f_object = (i, j) -> i+j; 
        int total = f_object.add(10, 20);
        System.out.println("total: " + total);      

        // Exception

        int divisor =20 ;
        int num = 18;
        try {
            int ans = num/divisor;
            if(ans==0){
                throw new MyException("I don't want to print Zero");
            }
        }
        catch(MyException e){
           int j = 18/ 1;
           System.out.println(j + " is default output " + e); 
        }
        catch(Exception e){
            System.out.println("Something Went wrong " + e); 
        }

        // throws keyword
        Forname fNameObj= new Forname();
        try{
             fNameObj.printClassName();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class name not found " + e);
        }

        // User input
        TakeUserInput takeInputObj = new TakeUserInput();
        int inputNum = takeInputObj.takeNumber();
        System.out.println(inputNum);
        // int inputNumberUsingScanner = takeInputObj.takeNumberUsingScanner();
        // System.out.println(inputNumberUsingScanner);

        // Threading
        Counter c = new Counter();
        Runnable aRunnableObj = () -> {
                for(int i=0; i < 100000; i++){
                    c.increment();
            }
        };

        Runnable bRunnableObj = () -> {
            for(int i=0; i < 100000; i++){
                c.increment();
            }
        };
          
        Thread aThreadObj= new Thread(aRunnableObj);
        Thread bThreadObj = new Thread(bRunnableObj);
        // aThreadObj.setPriority(Thread.MAX_PRIORITY);
        aThreadObj.start();
        bThreadObj.start();
        try {
            aThreadObj.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


   
        try {
            bThreadObj.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter is: " + c.count);
    
    }

}
class Counter{
    int count;
    public synchronized void increment() {
        count++;
        
    }
}
class MyException extends Exception{

    public MyException(String message) {
        super(message);
    }
    
}

class A{
    public A() {
        super();
        System.out.println("in A");
    }
    public A(int a) {
        super();
        System.out.println("in A int: " + a);
    }
    public void show(){
        System.out.println("in A show");
    }


}

 class B extends A{

     public B() {
         this(5);
         System.out.println("in B");
     }

      public B(int b) {
         super(10);
         System.out.println("in B int: " + b);
     }

     public void show() {
         System.out.println("In B show");
     }

 }

interface C{
    void show();
    void config();
}

class D implements C{
    public void show(){
        System.out.println("Show in D");
    }

    public void config() {
       
       System.out.println("in config D");
    }
    
}

interface Computer{
    String code();
}

class Laptop implements Computer{

    public String code() {
    String codingMessage = "code, compile and run on a Laptop";
    System.out.println(codingMessage);
    return codingMessage;
    }
    
}

class Desktop implements Computer{

    public String code() {
    String codingMessage = "code, compile and run on a Desktop";
    System.out.println(codingMessage);
    return codingMessage;
        
    }
    
}

class Developer{
    Computer comp;
    String name;

    public Developer(Computer comp) {
       this.comp = comp;
       this.name = this.toString();
    }
;
    public void code(){
        String codingMessge = this.comp.code();
        System.out.println(this.name + "coding: " + codingMessge); 
    }
    
}

enum Status{
    Pending, Running, Inturupted, Failed, Success
}

enum Laptops{
    MacBook(2000), Suraface(1800), ThinkPad(1500), XPS(2200), Dell;
    private int price;
    
    private Laptops() {
        this.price = 500;
    }
    
    private Laptops(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
}

//  Lamda expression and functional interface

@FunctionalInterface
interface F{
    int add(int i, int j);
}

class FI implements F{
    public int add(int i, int j){
        System.out.println("In Show FI" + i+j);
        return i+j;
    }
}

class Forname {
    public void printClassName() throws ClassNotFoundException {
        Class.forName("NotKnown");
       
    }
}

class TakeUserInput{

    public int takeNumber() throws NumberFormatException, IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            System.out.println("Enter your number: ");
            
            int num = Integer.parseInt(br.readLine());
            return num;

        }
    
    }

    public int takeNumberUsingScanner() {
        System.out.println("Enter your number: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        return num;
        
    }
}

class AThread extends Thread{
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println("in A show");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }


}

 class BThread implements Runnable{
     public void run() {
        for(int i=0; i<10; i++){
            System.out.println("In B show");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
           }
        
     }

 }
}