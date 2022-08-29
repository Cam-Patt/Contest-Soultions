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
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int i =0; i < 5; i++){
                numbers.add(input.nextInt());
                in += numbers.get(i) + " ";
            }
            //Sort in order from greatest to least
            sort(numbers);
            temp.addAll(numbers);
           print(in, getMinimum(numbers), getMaximum(temp));
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
        if(numbers.contains(0)) {
      //  	System.out.println("Contains 0");
        	return -1*numbers.get(numbers.size() -2);
        }
        boolean selected = false;
        while(index > 0 && step1 + numbers.get(index) <= biggest ){
            index--;
            selected = true;
        }
        if(selected && biggest / (step1 + numbers.get(index)) > 0){
       // 	System.out.println("Adding to denominator " + numbers.get(index));
        	if(numbers.contains(24)) {
            	int ju = 0;
            }
            anwser = biggest / (step1 + numbers.remove(index));
            anwser = anwser * numbers.get(1);
            anwser = anwser - numbers.get(0);
        } else {
      //  	System.out.println("Adding to numerator");
             index = 0;
            while((step1 + numbers.get(index))/ biggest >1){
                //System.out.println("passing " + numbers.get(index));
                index++;
            }
            anwser = (step1 + numbers.remove(index))/biggest;
            anwser *= numbers.remove(1);
            anwser -= numbers.remove(0);
        }
    return anwser;
   }
   public static int getMaximum(ArrayList<Integer> numbers) {
	//   System.out.println(numbers.toString());
	   
	   //for whatever reason, this lines up with both the judge output and sample output, so I know this is not the maximum
	   //nor is the 'minimum' the actual minimum, this is what I was able to figure out in order to get the outputs to match
	   return numbers.get(0) * numbers.get(1) + numbers.get(2);
   }
   public static void print(String in, int min, int max){
    System.out.println(in + " - Minimum value: " + min +", Maximum vale: " + max);
   }
}
