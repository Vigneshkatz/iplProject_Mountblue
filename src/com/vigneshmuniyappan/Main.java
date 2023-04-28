package com.vigneshmuniyappan;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer,HashMap<String,String>> matches = getMatches();
        HashMap<Integer,HashMap<String,String>> deliveries = getDeliveries();

        findMatchesPerYear(matches);
        findEveryTeamsTotalWins(matches);

        extraRunsConcededPerTeamIn2016(matches,deliveries);
        getTheTopEconomicalBowlersOf2015(matches,deliveries);

        getWicketsPerTeam(deliveries);
    }
    public static HashMap<Integer, HashMap<String, String>> getDeliveries() {
        HashMap<Integer, HashMap<String, String>> deliveries = new HashMap<>();
        String path = "/home/katziio/Desktop/IPlProjectRefactored/IplProject/resources/deliveries.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                Delivery md = new Delivery(arr);
                deliveries.put(count++, md.getMap());
            }
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return deliveries;
    }
    public static HashMap<Integer, HashMap<String, String>> getMatches() {
        HashMap<Integer,HashMap<String,String>> mapOfMatch = new HashMap<>();
        String path ="/home/katziio/Desktop/IPlProjectRefactored/IplProject/resources/matches.csv" ;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                Match md = new Match(arr);
                mapOfMatch.put(count++, md.getMap());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return mapOfMatch;
    }
    public static void findMatchesPerYear(HashMap<Integer, HashMap<String, String>> matches)
    {
        HashMap<Integer,Integer> yearAndWins= new HashMap<>();
        loadMapWithYearAndValue(yearAndWins);
        for(int key :matches.keySet())
        {
            HashMap<String,String> n =matches.get(key);
            for(int YEAR = 2008; YEAR <= 2017 ; YEAR++)
            {
                if(n.containsValue(new String(String.valueOf(YEAR)))){
                    yearAndWins.put(YEAR,yearAndWins.getOrDefault(YEAR,0) + 1);
                }
            }
        }
        printSolution(yearAndWins);
    }
    public static void getTheTopEconomicalBowlersOf2015(HashMap<Integer, HashMap<String, String>> getMapOfMatch,HashMap<Integer,
            HashMap<String, String>> getMapOfDeliveries)
    {
        HashMap<String, Integer> noOfDeliveriesBowledBybowlers = new HashMap<>();
        HashMap<String, Double> bowlersAndEconomy = new HashMap<>();
        HashMap<String, Integer> runsConcededBybowlers = new HashMap<>();
        ArrayList<Integer> listOfIds = getIdsForGivenParameter(getMapOfMatch, "2015");
        ArrayList<HashMap<String, String>> listOfDeliveriesForGivenIds = getResultForGivenIds(listOfIds, getMapOfDeliveries);
        getBowlersName(noOfDeliveriesBowledBybowlers, listOfDeliveriesForGivenIds);
        getDeliveriesForGivenIds(noOfDeliveriesBowledBybowlers, listOfDeliveriesForGivenIds);
        getBowlersName(runsConcededBybowlers, listOfDeliveriesForGivenIds);
        getRunConcededByBowler(runsConcededBybowlers, listOfDeliveriesForGivenIds);
        getEconomyOfBowler(bowlersAndEconomy, runsConcededBybowlers, noOfDeliveriesBowledBybowlers);
        getSortedForTopBowlersPerformance(bowlersAndEconomy);
    }
    public static void extraRunsConcededPerTeamIn2016(HashMap<Integer, HashMap<String, String>> matches, HashMap<Integer, HashMap<String, String>> deliveries) {
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<Integer> listOfIds = getIdsForGivenParameter(matches,"2016");
        ArrayList<HashMap<String, String>> listOfDeliveriesForGivenIds= getResultForGivenIds(listOfIds, deliveries);
        updateMap(map,listOfDeliveriesForGivenIds,"bowling_team","extra_runs");
        printSolution(map);
    }
    public static void findEveryTeamsTotalWins(HashMap<Integer,
            HashMap<String, String>> matches) {
        HashMap<String, Integer> everyTeamsTotalWins = new HashMap<>();
        loadMapWithTeamAndValue(everyTeamsTotalWins);
        for (int i : matches.keySet()) {
            HashMap<String, String> n = matches.get(i);
            String name = n.get("winner");

            if(name!=null && !name.contains("winner") && n.get("result").contains("normal"))
                everyTeamsTotalWins.put(name, everyTeamsTotalWins.getOrDefault(name, 0) + 1);
        }
        printSolution(everyTeamsTotalWins);

    }
    public static void getWicketsPerTeam(HashMap<Integer,
            HashMap<String, String>> matches) {
        HashMap<String,Integer> map = new HashMap<>();
        loadMapWithTeamAndValue(map);

        for(Integer i :matches.keySet() ) {
            HashMap<String, String> match = matches.get(i);
            String name = match.get("bowling_team");
            if (match.get("dismissal_kind") != null) {
                map.put(name, map.getOrDefault(name, 0) + 1);
            }
        }
        printSolution(map);
    }

    public static void loadMapWithTeamAndValue(HashMap<String,
            Integer> map) {
        for(int TEAMS = 0,VALUE=0;TEAMS<11;TEAMS++) {
            map.put("Chennai Super Kings", VALUE);
            map.put("Mumbai Indians", VALUE);
            map.put("Royal Challenger Bangalore", VALUE);
            map.put("Delhi Daredevils", VALUE);
            map.put("Sunrisers Hyderabad", VALUE);
            map.put("Deccan Chargers", VALUE);
            map.put("Gujarat Lions", VALUE);
            map.put("Rising Pune Supergiant", VALUE);
            map.put("Kings XI Punjab", VALUE);
            map.put("Kolkata Knight_Riders", VALUE);
            map.put("Rajasthan Royals", VALUE);
        }
    }
    public static void loadMapWithYearAndValue(HashMap<Integer,
            Integer> yearAndWins){
        for(int YEAR = 2008;YEAR<=2017;YEAR++)
        {
            yearAndWins.put(YEAR,0);
        }
    }
    public static void getEconomyOfBowler(HashMap<String, Double> map,
                                     HashMap<String, Integer> bowlerRuns,
                                     HashMap<String, Integer> bowlersDeliveries) {
        for(String bowler : bowlerRuns.keySet())
        {
            double economy = bowlerRuns.get(bowler) / (bowlersDeliveries.get(bowler)/6.0);
            map.put(bowler,economy);
        }
    }
    public static void getRunConcededByBowler(HashMap<String, Integer> bowlerRuns,
                                  ArrayList<HashMap<String, String>> listOfResultOfRequiredParameter) {
        for(HashMap<String,String> delivery : listOfResultOfRequiredParameter)
        {
            String bowler = delivery.get("bowler");
            int totalRunsConceded = parseIntFromString(delivery.get("total_runs")) - parseIntFromString(delivery.get("bye_runs"))- parseIntFromString(delivery.get("legbye_runs"));
            if(bowlerRuns.containsKey(bowler)) {
                bowlerRuns.put(bowler, bowlerRuns.getOrDefault(bowler, 0) + totalRunsConceded);
            }
        }
    }
    public static void getDeliveriesForGivenIds(HashMap<String, Integer> bowlers,
                                        ArrayList<HashMap<String, String>> listOfDeliveriesForGivenIds) {

        for(HashMap<String,String> delivery : listOfDeliveriesForGivenIds)
        {
            String bowler = delivery.get("bowler");
            if(bowlers.containsKey(bowler) && delivery.get("noball_runs").contains("0") && delivery.get("wide_runs").contains("0")) {
                bowlers.put(bowler, bowlers.getOrDefault(bowler, 0) + 1);
            }
        }
    }
    public static ArrayList<Integer> getIdsForGivenParameter(
            HashMap<Integer, HashMap<String, String>> mapOfMatch, String parameter){

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
    public static void getBowlersName(HashMap<String, Integer> bowlers,
                                         ArrayList<HashMap<String, String>> res) {

        for(HashMap<String,String> bowler : res)
        {
            String key = bowler.get("bowler");

                bowlers.put(key,0);
        }
    }
    public static void updateMap(HashMap<String, Integer> map,
                                 ArrayList<HashMap<String, String>> res,String setParameter,String getParameter) {


        for(HashMap<String,String> individualRes : res)
        {
            String team = individualRes.get(setParameter);

            map.put(team,map.getOrDefault(team,0) + parseIntFromString(individualRes.get(getParameter)));
        }
    }

    public static  ArrayList<HashMap<String, String>> getResultForGivenIds(ArrayList<Integer> idList,
                                                              HashMap<Integer, HashMap<String, String>> mapOfDeliveries) {
        ArrayList<HashMap<String, String>> res = new ArrayList<>();
        int count =0;
        for(int id:idList) {
            for (Integer i : mapOfDeliveries.keySet()) {
                if(count++==0)
                {
                    continue;
                }
                HashMap<String, String> eachDelivery = mapOfDeliveries.get(i);
                if(eachDelivery.get("matchId")!=null && eachDelivery.get("matchId").toString().matches("\\d+") && Integer.parseInt(eachDelivery.get("matchId"))==id )
                {
                    res.add(eachDelivery);
                }
            }
        }
        return res;
    }
    public static Integer parseIntFromString(String totalRuns) {
        if(totalRuns!=null && totalRuns.matches("\\d+") )
        {
           return Integer.parseInt(totalRuns);
        }
        else {
            return 0;
        }
    }
    public static void getSortedForTopBowlersPerformance(HashMap<String, Double> map){
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort
                (list, new Comparator<Map.Entry<String, Double>>()
                {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }
    public static <K, V> void printSolution(HashMap<K,V> yearAndWins)
    {
        for(K key: yearAndWins.keySet())
        {
            V type = yearAndWins.get(key);
            if(type instanceof  Integer && (Integer) type > 1)
            {
                System.out.printf("%s : %s",key.toString(),yearAndWins.get(key).toString());
                System.out.println();
            }

        }
        System.out.println();
    }
}