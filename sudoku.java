package project;

import java.util.Scanner;

public class sudoku {

	public int[] sudoku_answer(int[] array, int layer){
		int[] answer = new int[array.length];
		answer = generate_num(answer);
		
		// at first time, must be failure.
		int[] sum_row = new int[layer];
		int[] sum_col = new int[layer];
		sum_row[0] = 1; // everyone else is 0
		int row = 0;
		int col = 0;
		boolean first_row = true;
		boolean first_col = true;
		
		while(!((check_equal(sum_row, layer)) && (check_equal(sum_col, layer)))){

			// initialization
			for(int i = 0; i < layer; i++){
				sum_row[i] = 0;
				sum_col[i] = 0;
			}

			row = 0;
			col = 0;
			first_row = true;
			first_col = true;
			
			answer = generate_num(answer);
			
			// checking row sums
			for(int i = 0; i < answer.length; i++){
				if(i % layer == 0){
					if(!first_row){
						row++;
					}
					sum_row[row] += answer[i];
					first_row = false;
				}
				else{
					sum_row[row] += answer[i];
				}
				
			} // checking row sums
			
			// checking column sums
			for(int i = 0; i < answer.length; i++){
				if(col < (layer-1)){
					if(!first_col){
						col++;
					}
					sum_col[col] += answer[i];
					first_col = false;
				}
				else{
					col = 0;
					sum_col[col] += answer[i];
				}
			} // checking column sums
		}
		
		return answer;
	}
	
	public boolean check_equal(int[] sum, int layer){
		int index = 0;
		for(int i = 0; i < layer; i++){
			if(sum[index] != sum[i])
				return false;
		}
		
		return true;
	}
	
	public int[] generate_num(int[] sdk){
		int randomNum = (int) ((Math.random() * sdk.length) + 1); 
		int index = 0;
		int[] answer = new int[sdk.length];
		
		// initialization
		while(index < sdk.length){
			//System.out.println("random " + randomNum);
			if(redundancy_check(answer, randomNum)){
				// if there is same number in the array, run below function.
				randomNum = (int) ((Math.random() * sdk.length) + 1);
			}
			else{
				// if there is not equal value in the array, put the value into the array.
				answer[index] = randomNum;
				index++;
			}
		}
		
		return answer;
	}
	
	public boolean redundancy_check(int[] answer, int randomNum){
		for(int i = 0; i < answer.length; i++){
			if(answer[i] == randomNum)
				return true;
		}	
		return false;
	}
	
	public void print(int[] ary, int layer){
		for(int i = 0; i < ary.length; i++){
			if(i % layer == 0)
				System.out.println();
			
			System.out.print(ary[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		sudoku test = new sudoku();
		
		System.out.println("Enter sudoku number: ");
		int input = scanner.nextInt();
		
		int[] array = new int[input*input];
		//test.print(array, input);
		array = test.sudoku_answer(array, input);
		test.print(array, input);
	}
}
