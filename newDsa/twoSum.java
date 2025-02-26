import java.util.*;

public class twoSum{

    public static int[] indexTarget(int arr[] , int target , int newArr[]){

        for(int i =0 ; i<arr.length ; i++){
             for (int j=i+1; j<arr.length ; j++){
                if(arr[i]+arr[j]== target){
                    
                   newArr[0]=i;
                   newArr[1]=j;

                   return newArr;
                }

             }
        }
        return newArr;


    }
    
    
    public static void main(String args[]){
    int arr[]= {3, 5 , 6 , 5 , 9, 6,2};

    // here we gonna call function to wrok on   it 
    int[] newArr = new int[2];
    int answer[] = indexTarget(arr, 10 ,newArr);
    
    for (int i = 0; i < answer.length; i++) {
        System.out.println(answer[i] + " is the index");
    }
}
}
