import java.util.Scanner;
import java.util.TooManyListenersException;
import java.io.*;
import java.util.ArrayList;
public class George {
   public static void main(String[] args) throws IOException{
    Scanner input = new Scanner(new File("George.txt"));
    //Minimum -> Divicd ghd 2 biggest
    
        while(input.hasNextInt()){
            //division, mutiplication, addition, subtraction
            String in = "";
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for(int i =0; i < 5; i++){
                numbers.add(input.nextInt());
                in += numbers.get(i) + " ";
            }
            //Sort in order from greatest to least
            sort(numbers);
            
           print(in, getMinimum(numbers), 100);
        }
   } 
   public static void sort(ArrayList<Integer> list){
        for(int i =0; i < list.size()-1; i++){
            int val = list.get(i);
            int max = val;
            int index = i;
            for(int j =i +1; j < list.size(); j++){
                int temp = list.get(j);
                if(temp >max){
                    max = temp;
                    index = j;
                }
            }
            if(max != val){
                list.set(index, val);
                list.set(i, max);
            }
            
        }
   }
   public static void fillOperations(ArrayList<String> list){
    list.add("/");
    list.add("*");
    list.add("+");
    list.add("-");
   }
   public static int getMinimum(ArrayList<Integer> numbers){
        ArrayList<String> hasUsedOperation = new ArrayList<String>();
        fillOperations(hasUsedOperation);
        //Step 1 -> Add the 2nd biggest and 3rd biggest in order to remain below biggest
        //Step 2 -> divide the two biggest numbers
        //Step 3-> mulitple the 2 smallest
        //Step 4 -> subtract the smallest - big
        //return
        int anwser = 0;
        int biggest = numbers.remove(0);
        int step1 = numbers.remove(0);
        int index = numbers.size() -1;
        boolean selected = false;
        while(step1 + numbers.get(index)< biggest && index > 0){
            index--;
            selected = true;
        }
        if(selected){
            anwser = biggest / (step1 + numbers.remove(index));
            System.out.println(anwser);
            anwser = anwser * numbers.get(1);
            System.out.println(anwser);
            anwser = anwser - numbers.get(0);
            System.out.println(anwser);
        } else {
             index = 0;
            while((biggest + numbers.get(index))/ step1 >1){
                //System.out.println("passing " + numbers.get(index));
                index++;
            }
            anwser = (biggest + numbers.remove(index))/step1;
            System.out.println(anwser);
            anwser *= numbers.remove(1);
            System.out.println(anwser);
            anwser -= numbers.remove(0);
            System.out.println(anwser);
        }
    return anwser;
   }
   public static void print(String in, int min, int max){
    System.out.println(in + " - Minimum value: " + min +", Maximum vale: " + max);
   }
}
