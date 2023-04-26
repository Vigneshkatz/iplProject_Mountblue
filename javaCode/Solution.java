package javaCode;

import java.util.*;

/*
* Code a Java program that will transform the raw csv data into a data structure into a Dictionary format.
    Number of matches played per year of all the years in IPL.
    Number of matches won of all teams over all the years of IPL.
    For the year 2016 get the extra runs conceded per team.
    For the year 2015 get the top economical bowlers.
    Create your own scenario.
* */
public class Solution {


    public static void main(String[] args)
    {
        CSVReaderDeliveries deliveries = new CSVReaderDeliveries();
        CSVReaderMatch matches = new CSVReaderMatch();
        deliveries.printArrayList();
        matches.printArrayList();
        NumberOfMatchedPlayedPerYear();
        numberOfMatchesWonOfTeamsInIPL();
        extraRunIn2016(deliveries.getMapOfDeliveries(),matches.getMapOfMatch());
        economicalBowler2015();

    }

    private static void economicalBowler2015() {
    }

    private static void extraRunIn2016(HashMap<Integer, HashMap<String, String>> mapOfDeliveries, HashMap<Integer, HashMap<String, String>> mapOfMatch) {

        int extra=0;
        int year = 2015;
        int count =0;

        for(Integer key :mapOfDeliveries.keySet())
        {
                HashMap<String ,String> individualDelivery = mapOfDeliveries.get(key);
                System.out.println(individualDelivery);
                System.out.println(count++);


        }


    }

    private static void numberOfMatchesWonOfTeamsInIPL() {
    }

    private static void NumberOfMatchedPlayedPerYear() {
    }
}
