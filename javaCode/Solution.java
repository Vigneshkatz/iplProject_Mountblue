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
        NumberOfMatchedPlayedPerYear(matches.getMapOfMatch());
//        second
//        numberOfMatchesWonOfTeamsInIPL(matches.getMapOfMatch());
//        third
//            extraRunIn2016(deliveries.getMapOfDeliveries(),matches.getMapOfMatch());
////        fourth
//        economicalBowler2015(deliveries.getMapOfDeliveries(),matches.getMapOfMatch());
//        separateBYType(deliveries.getMapOfDeliveries(),matches.getMapOfMatch());

    }

    private static void NumberOfMatchedPlayedPerYear(HashMap<Integer, HashMap<String, String>> mapOfMatch) {
        HashMap<Integer,Integer> yearOfMatches = new HashMap<>();
        for(int i = 2008;i<=2017;i++)
        {
            yearOfMatches.put(i,0);
        }

        for(int i :mapOfMatch.keySet())
        {
            HashMap<String,String> n =mapOfMatch.get(i);

            if(n.containsValue("2008")){
                yearOfMatches.put(2008,yearOfMatches.getOrDefault(2008,0) + 1);
            }
            if(n.containsValue("2009")){
                yearOfMatches.put(2009,yearOfMatches.getOrDefault(2009,0) + 1);
            }
            if(n.containsValue("2010")){
                yearOfMatches.put(2010,yearOfMatches.getOrDefault(2010,0) + 1);
            }
            if(n.containsValue("2011")){
                yearOfMatches.put(2011,yearOfMatches.getOrDefault(2011,0) + 1);
            }
            if(n.containsValue("2012")){
                yearOfMatches.put(2012,yearOfMatches.getOrDefault(2012,0) + 1);
            }
            if(n.containsValue("2013")){
                yearOfMatches.put(2013,yearOfMatches.getOrDefault(2013,0) + 1);
            }
            if(n.containsValue("2014")){
                yearOfMatches.put(2014,yearOfMatches.getOrDefault(2014,0) + 1);
            }
            if(n.containsValue("2015")){
                yearOfMatches.put(2015,yearOfMatches.getOrDefault(2015,0) + 1);
            }
            if(n.containsValue("2016")){
                yearOfMatches.put(2016,yearOfMatches.getOrDefault(2016,0) + 1);
            }
            if(n.containsValue("2017")){
                yearOfMatches.put(2017,yearOfMatches.getOrDefault(2017,0) + 1);
            }
        }
//        System.out.println(yearOfMatches.keySet());
        for(int year: yearOfMatches.keySet())
        {
            System.out.printf("In %d : %d Matches Had played",year,yearOfMatches.get(year));
            System.out.println();
        }

    }

    private static void economicalBowler2015(HashMap<Integer, HashMap<String, String>> mapOfDeliveries,
                                             HashMap<Integer, HashMap<String, String>> mapOfMatch)  {
//total balls bowled
//        HashMap<String,Integer> ballBowled = new HashMap<>();
        //        total balls total runs
        HashMap<String,Integer> runsLeaked = new HashMap<>();
        HashMap<String,Double> map = new HashMap<>();
        ArrayList<Integer> idList = getIds(mapOfMatch,"2015");
//        System.out.println(idList.size());
        ArrayList<HashMap<String, String>> res = getRes(idList,mapOfDeliveries);
//        for(HashMap<String, String> i : res)
//        {
//            System.out.println(i);
//        }
//        updateMap(ballBowled,res,"bowler","ball");
        HashMap<String,Integer> ballBowled =updateMapWithKeys(new HashMap<String,Integer>(),res,"bowler");
//        updateMap(runsLeaked,res,"bowler","total_runs");
//        System.out.println("No of balls");
//        System.out.println(ballBowled);
//        System.out.println("No of runs");
        addNumberOfBalls(ballBowled,res);

//        System.out.println(ballBowled);

//        for(String name : ballBowled.keySet())
//        {
//            int ballsBowled = ballBowled.get(name) / 6;
//            System.out.println(ballsBowled);
//            int totalRuns = runsLeaked.get(name);
//            System.out.println(totalRuns);
//            double economy = totalRuns / ballsBowled;
//            map.put(name,economy);
//
//        }
//        System.out.println(map);55
    }

    private static void addNumberOfBalls(HashMap<String, Integer> ballBowled, ArrayList<HashMap<String, String>> res) {

        for(HashMap<String, String> i:res)
        {
            System.out.println(i);
        }
    }

    //3.to calculate extra runs
    private static void extraRunIn2016(HashMap<Integer, HashMap<String, String>> mapOfDeliveries, HashMap<Integer, HashMap<String, String>> mapOfMatch) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("Chennai_Super_Kings",0);
        map.put("Mumbai_Indians",0);
        map.put("Royal_Challengers_Bangalore",0);
        map.put("Delhi_Daredevils",0);
        map.put("Sunrisers_Hyderabad",0);
        map.put("Deccan_Chargers",0);
        map.put("Gujarat_Lions",0);
        map.put("Rising_Pune_Supergiant",0);
        map.put("Kings_XI_Punjab",0);
        map.put("Kolkata_Knight_Riders",0);
        map.put("Rajasthan_Royals",0);
        ArrayList<Integer> id = getIds(mapOfMatch,"2016");
//
        ArrayList<HashMap<String, String>> res = getRes(id,mapOfDeliveries);
        System.out.println(res.size());
        updateMap(map,res,"bowling_team","extra_runs");
//
        for(String team : map.keySet())
        {
            if(map.get(team) != 0) {
                System.out.printf("%s has bowled %d Extra runs", team, map.get(team));
                System.out.println();
            }
        }
    }

    private static void updateMap(HashMap<String, Integer> map, ArrayList<HashMap<String, String>> res,String setParameter,String getParameter) {

//        String parameter = "bowling_team";

        for(HashMap<String,String> individualRes : res)
        {
            String team = individualRes.get(setParameter);

            map.put(team,map.getOrDefault(team,0) + intOf(individualRes.get(getParameter)));
        }
    }
    private static HashMap<String, Integer> updateMapWithKeys(HashMap<String, Integer> map,ArrayList<HashMap<String, String>> res,String setParameter){

            for(HashMap<String,String> individualRes : res)
            {
                String key = individualRes.get(setParameter);
                if(map.containsKey(key))
                {
                    continue;
                }else{
                    map.put(key,0);
                }

            }
            return map;

    }

    private static int intOf(String penaltyRuns) {
        if(penaltyRuns!=null &&penaltyRuns.matches("\\d+") )
        {
            int out = Integer.parseInt(penaltyRuns);
//            System.out.println(out);
            return out;
        }
        else {
            return 0;
        }
    }
    private static void numberOfMatchesWonOfTeamsInIPL(HashMap<Integer, HashMap<String, String>> mapOfMatch) {
        /*
        *
        HashMap<String,Integer> map = new HashMap<>();
        map.put("Chennai_Super_Kings",0);
        map.put("Mumbai_Indians",0);
        map.put("Royal_Challengers_Bangalore",0);
        map.put("Delhi_Daredevils",0);
        map.put("Sunrisers_Hyderabad",0);
        map.put("Deccan_Chargers",0);
        map.put("Gujarat_Lions",0);
        map.put("Rising_Pune_Supergiant",0);
        map.put("Kings_XI_Punjab",0);
        map.put("Kolkata_Knight_Riders",0);
        map.put("Rajasthan_Royals",0);*/
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
//    using separate method to easy purpose of separation
    private static ArrayList<Integer> getIds(
            HashMap<Integer,HashMap<String, String>> mapOfMatch,String parameter){

        String toAddInList ="id";
        ArrayList<Integer> id = new ArrayList<>();

        for(Integer matchKey: mapOfMatch.keySet())
        {
            HashMap<String,String> singleMatch = mapOfMatch.get(matchKey);
           if(singleMatch.get("date").contains(parameter)){
              id.add(Integer.parseInt(singleMatch.get(toAddInList)));
           }
        }
        return id;

    }

    private static ArrayList<HashMap<String, String>> getRes(ArrayList<Integer> idList,
                                                             HashMap<Integer,HashMap<String, String>> deliveries)
    {
        ArrayList<HashMap<String, String>> res = new ArrayList<>();
        int count =0;
        for(int id:idList) {
            for (Integer i : deliveries.keySet()) {
                if(count++==0)
                {
                    continue;
                }
                HashMap<String, String> eachDelivery = deliveries.get(i);
                if(eachDelivery.get("matchId")!=null && eachDelivery.get("matchId").toString().matches("\\d+") && Integer.parseInt(eachDelivery.get("matchId"))==id )
                {
                    res.add(eachDelivery);
                }

            }
        }
        System.out.println(res.size());
        return res;
    }
}
