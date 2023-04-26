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
//        deliveries.printArrayList();
//        matches.printArrayList();
////        first
//        NumberOfMatchedPlayedPerYear();
////        second
        numberOfMatchesWonOfTeamsInIPL(matches.getMapOfMatch());
////        third
//        extraRunIn2016(deliveries.getMapOfDeliveries(),matches.getMapOfMatch());
////        fourth
//        economicalBowler2015();

    }

    private static void economicalBowler2015() {
    }

    private static void extraRunIn2016(HashMap<Integer, HashMap<String, String>> mapOfDeliveries, HashMap<Integer, HashMap<String, String>> mapOfMatch) {
//
//        int extra=0;
//        int year = 2015;
//        int count =0;
//
//        for(Integer key :mapOfDeliveries.keySet())
//        {
//                HashMap<String ,String> individualDelivery = mapOfDeliveries.get(key);
//                System.out.println(individualDelivery.get("extra_runs")+individualDelivery.get("legbye_runs"));
//                System.out.println(count++);
//        }
    }
    private static void numberOfMatchesWonOfTeamsInIPL(HashMap<Integer, HashMap<String, String>> mapOfMatch) {

        int Chennai_Super_Kings=0;
        int Mumbai_Indians=0;
        int Royal_Challengers_Bangalore=0;
        int Delhi_Daredevils=0;
        int Sunrisers_Hyderabad=0;
        int Deccan_Chargers=0;
        int Gujarat_Lions=0;
        int Rising_Pune_Supergiant=0;
        int Kings_XI_Punjab=0;
        int Kolkata_Knight_Riders=0;
        int Rajasthan_Royals=0;
        for(int i :mapOfMatch.keySet() )
        {
            HashMap<String,String> n =mapOfMatch.get(i);
//            System.out.println(n.entrySet());

            String name = n.get("winner");
//            System.out.println(name);
//            System.out.println(name);
            switch (name)
            {
                case "Chennai Super Kings":
                    Chennai_Super_Kings++;
                    break;
                case "Mumbai Indians":
                    Mumbai_Indians++;
                    break;
                case "Royal Challengers Bangalore":
                    Royal_Challengers_Bangalore++;
                    break;
                case "Delhi Daredevils":
                    Delhi_Daredevils++;
                    break;
                case "Sunrisers Hyderabad":
                    Sunrisers_Hyderabad++;
                    break;
                case "Deccan Chargers":
                    Deccan_Chargers++;
                    break;
                case "Gujarat Lions":
                    Gujarat_Lions++;
                    break;
                case "Rising Pune Supergiant":
                    Rising_Pune_Supergiant++;
                    break;
                case "Kings XI Punjab":
                    Kings_XI_Punjab++;
                    break;
                case "Kolkata Knight Riders":
                    Kolkata_Knight_Riders++;
                    break;
                case "Rajasthan Royals":
                    Rajasthan_Royals++;
                    break;

            }

        }
        System.out.println("Chennai_Super_Kings : "+ Chennai_Super_Kings);
        System.out.println("Mumbai_Indians : "+Mumbai_Indians);
        System.out.println("Royal_Challengers_Bangalore : "+Royal_Challengers_Bangalore);
        System.out.println("Delhi_Daredevils : "+Delhi_Daredevils);
        System.out.println("Sunrisers_Hyderabad : "+Sunrisers_Hyderabad);
        System.out.println("Deccan_Chargers : "+Deccan_Chargers);
        System.out.println("Gujarat_Lions : "+Gujarat_Lions);
        System.out.println("Rising_Pune_Supergiant : "+Rising_Pune_Supergiant);
        System.out.println("Kings_XI_Punjab : "+ Kings_XI_Punjab);
        System.out.println("Kolkata_Knight_Riders : "+Kolkata_Knight_Riders);
        System.out.println("Rajasthan_Royals : "+Rajasthan_Royals);
    }
}
