package com.samples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BowlingScoreSimulator {

    public static ArrayList<Integer> scoreList;
    public static void main(String[] args) {

        getScoreList();

        System.out.println("Here is the integer list of inputs captured : " +scoreList);

        //TODO write logic for calculating score by looping through list

        HashMap<Integer, Integer> segratedFrameMap =  segratedFrameMap(scoreList);

        System.out.println("segratedFrameMap no filter: "+segratedFrameMap);



    }


    private static HashMap<Integer, Integer> segratedFrameMap(ArrayList<Integer> scoreList){

        StringBuilder output = new StringBuilder();
        output.append("|");
        int aggregate = 0;

        HashMap<Integer, Integer> segratedFrameMap = new HashMap<>();


        int score = 0;
        int count = 0;
        int frameNumber = 1;
        int frameScoreNoFilter = 0;
        boolean isStrikeorSpare = false;
        boolean isStrike = false;
        for(int iteration = 0; iteration < scoreList.size(); iteration++){
            score = scoreList.get(iteration);

            if(score < 10 || frameScoreNoFilter < 10 ||count < 2){
                frameScoreNoFilter += score;
                count+=1;

            }
            if(score==10){
                frameScoreNoFilter = score;
                isStrike = true;
            }

            if(frameScoreNoFilter == 10){
                isStrikeorSpare = true;
            }

            if(frameScoreNoFilter ==10 ||count == 2){
//                frameScoreNoFilter = 10;
                segratedFrameMap.put(frameNumber,frameScoreNoFilter);
                aggregate += frameScoreNoFilter;

                if(!isStrikeorSpare){
                    output.append(" "+aggregate+"|");
                }
                else{
                    if(!isStrike){
                        aggregate += scoreList.get(iteration+1);
                        output.append(" "+aggregate +"| ");
                    }
                    else{
                        aggregate = aggregate + scoreList.get(iteration+1)+ scoreList.get(iteration+2);
                        output.append(" "+aggregate +"| ");
                    }

                }


                frameNumber += 1;
                count = 0;
                frameScoreNoFilter = 0;
                if(frameScoreNoFilter < 10){
                    isStrikeorSpare = false;
                    isStrike = false;
                }

            }

        }
        if(count == 1){
            segratedFrameMap.put(frameNumber,frameScoreNoFilter);
        }

        System.out.println("Expected : |  5| 14| 29| 49| 60| 61| 77| 97|117|133|");
        System.out.println("Output : "+output.toString());

        return segratedFrameMap;
    }

    private static void getScoreList(){
        String input = getInput();
        while(!checkValidInput(input)){
            System.out.println("Not a valid valid input.. please try again..");
            input = getInput();
        }


    }

    private static boolean checkValidInput(String input){

        try{
            scoreList = new ArrayList<>();
            String[] inpArray = input.split(",");
            int inpInt = 0;
            for(int i=0; i < inpArray.length ; i++){
                inpInt = Integer.parseInt(inpArray[i]);
                if(inpInt>=0 && inpInt <= 10){
                    scoreList.add(Integer.parseInt(inpArray[i]));
                }
                else{
                    System.out.println("Invalid score passed in input. Score inputs has to be >=0 and <=10.");
                    throw new Exception();
                }

            }
            return true;
        }
        catch(Exception e){
            scoreList.clear();
            return false;

        }

    }


    private static String getInput(){
        String scoreStr = "";
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter score number : ");
            scoreStr = bufferedReader.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return scoreStr;
    }
}
