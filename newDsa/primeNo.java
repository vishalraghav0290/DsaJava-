import java.util.*;
public class primeNo{
    public static boolean primeNumber(int num){
        // prime no are those no that can divide by itself and 1 none other than these two 
        // 
        System.out.println(num);
        if(num<2){
            return false; 
        }
// 13 /2 = 6.5  and 6.5
        for(int i=2; i<num/2; i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("enter the number to check it prime or not");
        int number = sc.nextInt();

        boolean devansh = primeNumber(number);
        System.out.println(devansh);
    }
   
    }
   
