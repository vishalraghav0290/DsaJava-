import java.util.*;

public class fibonnaciSeries{
    public static int[] displayFibonnaci(int nummber , int arr[]){

        // approch is to mack loop run back ward and make a minus of value of current index to previous index and than store the value 

        // approch can also we we can use num as limit and simply play an additon of current index value to next index value and this is easier than first one than  we go for this one 

        // check base case that no is not 2 or less than 2 

        if(nummber<=2){
            return arr;
        }
        // we manully add o and 1 postion as it remain through every case 

        arr[0]=1;
        arr[1]=2;

        //now we play a loop than move index 
        for(int i=2; i<arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
            if(arr[i] >= nummber) {  // Change condition here
                return arr;
            }
        }
        return arr;
    }

    //main methonds
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int arr[]= new int[8];
        System.out.println("enter the no upto which u wish to find fibonacci no");
        int nummber = sc.nextInt();
        int answer[]=displayFibonnaci(nummber , arr);

        for(int i=0 ; i<answer.length ; i++){
            System.out.println(answer[i]);
        }

    }

}