package com.revature.generic.arraylist;

/*
 * For this activity, simply implement
 * all of the bwlo methods!
 */
public class ArrayList<T> {
	T[] arr;
	int size, numValues;

	public ArrayList(){
		arr = (T[])(new Object[1]);
		size = arr.length;
		numValues = 0;
	}
	public void add(T t) {
		for(int i = 0; i < arr.length; i++) {
			//add T to array if i is the last index
			if(arr[i]==null) {
				arr[i]=t;
				++numValues;
				break;
			}
			//if array is full create new array that is twice as large
			if(arr[i]!=null && i == (arr.length-1)) {
				T[] tempArr = (T[])(new Object[arr.length*2]);
				for(int x = 0; x<arr.length;++x) {
					tempArr[x] = arr[x];
				}
				arr = tempArr;
				size = size *2;
			}
		}
	}
	
	public T get(int i) {
		return arr[i];
	}
	
	public void size() {
		System.out.println(arr.length);
	}
	
	/*
	 * Split should take arr and split into a number of subarrays.
	 * The number is given by numberOfNewArrays.
	 * 
	 * eg if [1, 5, 6, 5, 7] and input 3
	 * so the output would be: 
	 * [
	 *   [1, 5],
	 *   [6, 5],
	 *   [7, null]
	 * ]
	 */
	public T[][] split(int numberOfNewArrays) {
		//initiating all the variables i need and making the new 2d array
		int arraySize = numValues;
		int newArraySize = (arraySize / numberOfNewArrays);
		if(arraySize % numberOfNewArrays != 0) {
			newArraySize++;
		}
		System.out.println(newArraySize);
		T[][] splitArr = (T[][])(new Object[numberOfNewArrays][newArraySize]);
		int holder = 0;
		
		//iterate through the new array appending data from the 1D array
		for (int i = 0; i < numberOfNewArrays ; i ++) {
			for(int j = 0; j < newArraySize; j++) {
				if(holder<arraySize) {
					splitArr[i][j]=arr[holder];
					holder++;
				}
				else{
					splitArr[i][j] = null;
				}
			}
		}
		return splitArr;
	}
	
	//testing method
	public static void main(String[] args) {
		ArrayList<String> myArray = new ArrayList<String>();
		myArray.add("Hello");
		myArray.add("World");
		myArray.add("How");
		myArray.add("Are");
		myArray.add("You");
		myArray.add("Doing?");
		myArray.add("Doing?");
		

		for (int i = 0; i < myArray.size ; ++i) {
			System.out.println(myArray.get(i));
		}
		myArray.size();
		Object[][] splitArray = myArray.split(3);
		for (int i = 0; i < splitArray.length ; i ++) {
			for(int j = 0; j < splitArray[0].length; j++) {
				System.out.print("["+splitArray[i][j]+"] ");
			}
			System.out.println("");
		}	
	}
}