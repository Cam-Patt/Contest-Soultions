import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class fixedGeorge {
	private static ArrayList<ArrayList<Integer>> permutations;
	private static ArrayList<ArrayList<Character>> operations;
	private static ArrayList<Integer> mins;
	private static ArrayList<Integer> maxs;
	private static ArrayList<Character> minOps;
	private static ArrayList<Character> maxOps;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(new File("George.txt"));
		ArrayList<Character> ops = new ArrayList<Character>();
		ops.add('+');
		ops.add('-');
		ops.add('*');
		ops.add('/');
		operations = new ArrayList<ArrayList<Character>>();
		operationOptions(ops, 0, ops.size() -1);
		while(input.hasNextInt()) {
			String in = "";
			ArrayList<Integer> numbers = new ArrayList<Integer>();
        	//ArrayList<Integer> temp = new ArrayList<Integer>();
        	for(int i =0; i < 5; i++){
        		numbers.add(input.nextInt());
        		in += numbers.get(i) + " ";
        	}
        	permutations =new ArrayList<ArrayList<Integer>>();
        	integerPermutation(numbers, 0,numbers.size() -1);
        //	System.out.println(permutations.size());
        //	testPrint();
        	ArrayList<Integer> minMax = minMax();
        	print(in,minMax.get(0), minMax.get(1));
		}
	}
	public static void operationOptions(ArrayList<Character> operation, int length, int options) {
		if(length == options) {
			operations.add(new ArrayList(operation));
	//		System.out.println(numbers.toString());
			
		}else {
			//System.out.println("in else");
			for(int i = length; i <= options; i++) {
				operation = swapOperations(operation, length, i);
				operationOptions(operation, length + 1, options);
				operation = swapOperations(operation,length, i);
			}
		}
	}
	public static void integerPermutation(ArrayList<Integer> numbers, int length, int options){
		if(length == options) {
			permutations.add(new ArrayList(numbers));
	//		System.out.println(numbers.toString());
			
		}else {
			//System.out.println("in else");
			for(int i = length; i <= options; i++) {
				numbers = swap(numbers, length, i);
				integerPermutation(numbers, length + 1, options);
				numbers = swap(numbers,length, i);
			}
		}
	}
	public static void testPrint() {
		for(int i =0; i < operations.size(); i++) {
			System.out.println(operations.get(i).toString());
		}
	}
	public static ArrayList<Integer> swap(ArrayList<Integer> num, int i, int k){
		int number = num.get(i);
		num.set(i, num.get(k));
		num.set(k, number);
		return num;
	}
	public static ArrayList<Character> swapOperations(ArrayList<Character> operation, int i, int k){
		char op = operation.get(i);
		operation.set(i, operation.get(k));
		operation.set(k, op);
		return operation;
	}
	public static ArrayList<Integer> minMax(){
		ArrayList<Integer> minMax = new ArrayList<Integer>();
		minMax.add(0, Integer.MAX_VALUE);
		minMax.add(1, Integer.MIN_VALUE);
		for(int i =0; i < permutations.size();i++) {
			ArrayList<Integer> nums = permutations.get(i);
			for(int j = 0; j < operations.size(); j++) {
				ArrayList<Character> ops = operations.get(j);
				int value = nums.get(0);
				boolean possible = true;
				for(int k =0; k < ops.size(); k++) {
					if(ops.get(k) == '+') {
						value += nums.get(k+1);
					} else if(ops.get(k) == '-') {
						value -= nums.get(k+1);
					}else if(ops.get(k) == '/') {
						if(nums.get(k + 1) == 0 ||value / nums.get(k +1) ==0) {
							possible = false;
						}else {
							value /= nums.get(k+1);
						}
						
					}else if(ops.get(k) == '*') {
						value *= nums.get(k+1);
					}
				}
				if(value < minMax.get(0) && possible) {
					minMax.set(0, value);
					mins = nums;
					minOps = ops;
				}
				if(value > minMax.get(1) && possible) {
					minMax.set(1, value);
					maxs = nums;
					maxOps = ops;
				}
			}//end of operations
		}
		return minMax;
	}
	 public static void print(String in, int min, int max){
		    System.out.println(in + " - Minimum value: " + min +", Maximum vale: " + max);
		    System.out.print("Minimum equation: ((" + mins.get(0)+" ");
		    for(int j = 1; j < mins.size(); j++) {
		    	if(minOps.get(j-1) == '-' || minOps.get(j-1) =='+') {
		    		System.out.print(minOps.get(j -1)+" "+ mins.get(j)+")");
		    	}else {
		    	System.out.print(minOps.get(j -1)+" "+ mins.get(j));
		    	}
		    }
		    System.out.println();
		    System.out.print("Maximum equation: ((" + maxs.get(0)+ " ");
		    for(int j = 1; j < maxs.size(); j++) {
		    	if(maxOps.get(j-1) == '-' || maxOps.get(j-1) =='+') {
		    		System.out.print(maxOps.get(j -1)+" "+ maxs.get(j) +")");		    
		    	}else {
		    		System.out.print(maxOps.get(j -1)+" "+ maxs.get(j) );
		    	}
		    }
		    System.out.println();
		    System.out.println();
	 	}
}
