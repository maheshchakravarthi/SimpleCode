package com.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Author: Sai Mahesh Vyakaranam
 * Problem statement:
 * Given a string of | and * combinations
 * consider | as a container open or closing material
 * consider * as items
 * Start indices are INCLUSIVE
 * End indices are INCLUSIVE
 * 
 * Example: String **|***|*|***
 * start indices are 1,3
 * end indices are 4,11
 * 
 * The result should give us a a result list containing the 
 * number of items in the closed containers of the substrings made with start and end indices.
 * 
 *  For Eg - The first substring start and end indices are 1,4.
 *  so substring is **|*
 *  there are no items with in two | i.e. closed containers. So items would be 0.
 *  The second substring start and end indices are 3,11
 *  so substring is |***|*|**
 *  There are 4 items between closed |'s here(3+1), so the num of items here is 4
 *  Final output list will be 0,4 
 * 
 * */
public class ContainerItems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "*|**|****|********|***|**********||****|";
		System.out.println(s);
		List<Integer> startIndices = new ArrayList<Integer>();
		List<Integer> endIndices = new ArrayList<Integer>();
		startIndices = Arrays.asList(new Integer[] {1,5,7,22});
		endIndices = Arrays.asList(new Integer[] {3,12,24,40});
		System.out.println(getNumberOfItems(s, startIndices, endIndices));
	}
	
	public static List<Integer> getNumberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices){
		System.out.println("s : "+s);
		System.out.println("startIndices : "+startIndices);
		System.out.println("endIndices : "+endIndices);
		List<Integer> numItemsList = new ArrayList<Integer>();
		String str  = "";
		boolean isOpen = false;
		int bufItems = 0;
		int numItems = 0;
		for(int i = 0; i < startIndices.size(); i++){
			int startIndex = startIndices.get(i);
			int endIndex = endIndices.get(i);
			startIndex = startIndex - 1;
			if(endIndex == s.length()){
				str = s.substring(startIndex);
			}
			else{
				str = s.substring(startIndex, endIndex);
			}
			System.out.println("str : "+str);
			char[] cArr = str.toCharArray(); 
			for(int j = 0; j < cArr.length; j++){
				char c = cArr[j];
				if(c == '|'){
					if(isOpen){
						numItems = numItems + bufItems;
						bufItems = 0;
					}
					else{
						isOpen = true;
						bufItems = 0;
					}
				}
				else if(c == '*'){
					if(isOpen){
						bufItems += 1;
					}
				}
//				System.out.println("c : "+c+" numItems : "+numItems + "  bufItems : "+bufItems+"  isOpen : "+isOpen);
			}
			numItemsList.add(numItems);
			bufItems = 0;
			numItems = 0;
			isOpen = false;
			System.out.println("numItemsList : "+numItemsList);
		}
		System.out.println("numItemsList : "+numItemsList);
		return numItemsList;
	}

}
