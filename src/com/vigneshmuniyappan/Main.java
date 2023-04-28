package com.vigneshmuniyappan;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long stopWatch = System.nanoTime();
        HashMap<Integer,HashMap<String,String>> matches = getMatches();
        HashMap<Integer,HashMap<String,String>> deliveries = getDeliveries();

        findMatchesPerYear(matches);
        findEveryTeamsTotalWins(matches);

        extraRunsConcededPerTeamIn2016(matches,deliveries);
        getTheTopEconomicalBowlersOf2015(matches,deliveries);

        getWicketsPerTeam(deliveries);

//        long duration = (System.nanoTime()-stopWatch)/1000000;
        System.out.println((System.nanoTime()-stopWatch)/1000000);
    }
    public static HashMap<Integer, HashMap<String, String>> getDeliveries() {
        HashMap<Integer, HashMap<String, String>> deliveries = new HashMap<>();
        String path = "/home/katziio/Desktop/IplProject/resources/deliveries.csv";
        try (BufferedReader fileReaderForDeliveries = new BufferedReader(new FileReader(path))) {
            String line;
            int IDS = 1;
            while ((line = fileReaderForDeliveries.readLine()) != null) {
                String[] arr = line.split(",");
                Delivery md = new Delivery(arr);
                deliveries.put(IDS++, md.getMap());
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
        String path ="/home/katziio/Desktop/IplProject/resources/matches.csv" ;
        try (BufferedReader fileReaderOfMatches = new BufferedReader(new FileReader(path))) {
            String line;
            int IDS = 1;
            while ((line = fileReaderOfMatches.readLine()) != null) {
                String[] arr = line.split(",");
                Match md = new Match(arr);
                mapOfMatch.put(IDS++, md.getMap());
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
        Map<Integer,Integer> yearAndWins= new TreeMap<>();
        loadMapWithYearAndValue(yearAndWins);
        for(int KEY :matches.keySet())
        {
            HashMap<String,String> match =matches.get(KEY);
            for(int YEAR = 2008; YEAR <= 2017 ; YEAR++)
            {
                if(match.containsValue(new String(String.valueOf(YEAR)))){
                    yearAndWins.put(YEAR,yearAndWins.getOrDefault(YEAR,0) + 1);
                }
            }
        }
        System.out.println(yearAndWins);
    }

    public static void getTheTopEconomicalBowlersOf2015(HashMap<Integer, HashMap<String, String>> getMapOfMatch,HashMap<Integer,
            HashMap<String, String>> getMapOfDeliveries)
    {
        HashMap<String, Integer> noOfDeliveriesBowledByBowlers = new HashMap<>();
        HashMap<String, Double> bowlersAndEconomy = new HashMap<>();
        HashMap<String, Integer> runsConcededBowler = new HashMap<>();
        
        ArrayList<Integer> listOfIds = getIdsForGivenParameter(getMapOfMatch, "2015");
        ArrayList<HashMap<String, String>> listOfDeliveriesForGivenIds = getResultForGivenIds(listOfIds, getMapOfDeliveries);
        
        getBowlersName(noOfDeliveriesBowledByBowlers, listOfDeliveriesForGivenIds);
        getDeliveriesForGivenIds(noOfDeliveriesBowledByBowlers, listOfDeliveriesForGivenIds);
        getBowlersName(runsConcededBowler, listOfDeliveriesForGivenIds);
        getRunConcededByBowler(runsConcededBowler, listOfDeliveriesForGivenIds);
        getEconomyOfBowler(bowlersAndEconomy, runsConcededBowler, noOfDeliveriesBowledByBowlers);

        sortAndPrintSolution(bowlersAndEconomy);
    }
    public static void extraRunsConcededPerTeamIn2016(HashMap<Integer, HashMap<String, String>> matches, HashMap<Integer, HashMap<String, String>> deliveries) {
        HashMap<String,Integer> teamsAndExtras = new HashMap<>();
        
        ArrayList<Integer> listOfIds = getIdsForGivenParameter(matches,"2016");
        ArrayList<HashMap<String, String>> listOfDeliveriesForGivenIds= getResultForGivenIds(listOfIds, deliveries);
        
        updateMapWithIds(teamsAndExtras,listOfDeliveriesForGivenIds,"bowling_team","extra_runs");
        
        printSolution(teamsAndExtras);
    }
    public static void findEveryTeamsTotalWins(HashMap<Integer,
            HashMap<String, String>> matches) {
        HashMap<String, Integer> everyTeamsTotalWins = new HashMap<>();
        
        loadMapWithTeamAndValue(everyTeamsTotalWins);
        for (int KEY : matches.keySet()) {
            HashMap<String, String> match = matches.get(KEY);
            String name = match.get("winner");

            if(name!=null && !name.contains("winner") && match.get("result").contains("normal"))
                everyTeamsTotalWins.put(name, everyTeamsTotalWins.getOrDefault(name, 0) + 1);
        }
        
        printSolution(everyTeamsTotalWins);

    }
    public static void getWicketsPerTeam(HashMap<Integer,
            HashMap<String, String>> matches) {
        HashMap<String,Integer> getWicketsPerTeam = new HashMap<>();
        
        loadMapWithTeamAndValue(getWicketsPerTeam);

        for(Integer key :matches.keySet() ) {
            HashMap<String, String> match = matches.get(key);
            String name = match.get("bowling_team");
            if (match.get("dismissal_kind") != null) {
                getWicketsPerTeam.put(name, getWicketsPerTeam.getOrDefault(name, 0) + 1);
            }
        }
        
        printSolution(getWicketsPerTeam);
    }

    public static void loadMapWithTeamAndValue(HashMap<String,
            Integer> mapWithOutValue) {
        for(int VALUE=0;VALUE<1;VALUE++) {
            mapWithOutValue.put("Chennai Super Kings", VALUE);
            mapWithOutValue.put("Mumbai Indians", VALUE);
            mapWithOutValue.put("Royal Challenger Bangalore", VALUE);
            mapWithOutValue.put("Delhi Daredevils", VALUE);
            mapWithOutValue.put("Sunrisers Hyderabad", VALUE);
            mapWithOutValue.put("Deccan Chargers", VALUE);
            mapWithOutValue.put("Gujarat Lions", VALUE);
            mapWithOutValue.put("Rising Pune Supergiant", VALUE);
            mapWithOutValue.put("Kings XI Punjab", VALUE);
            mapWithOutValue.put("Kolkata Knight_Riders", VALUE);
            mapWithOutValue.put("Rajasthan Royals", VALUE);
        }
    }
    public static void loadMapWithYearAndValue(Map<Integer,
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
        ArrayList<Integer> ID = new ArrayList<>();

        for(Integer matchKey: mapOfMatch.keySet())
        {
            HashMap<String,String> singleMatch = mapOfMatch.get(matchKey);
            if(singleMatch.get("date").contains(parameter)){
                ID.add(Integer.parseInt(singleMatch.get(toAddInList)));
            }
        }
        return ID;
    }
    public static void getBowlersName(HashMap<String, Integer> bowlers,
                                         ArrayList<HashMap<String, String>> res) {

        for(HashMap<String,String> bowler : res)
        {
            String key = bowler.get("bowler");

                bowlers.put(key,0);
        }
    }
    public static void updateMapWithIds(HashMap<String, Integer> map,
                                 ArrayList<HashMap<String, String>> res,String setParameter,String getParameter) {
        for(HashMap<String,String> individualRes : res)
        {
            String team = individualRes.get(setParameter);

            map.put(team,map.getOrDefault(team,0) + parseIntFromString(individualRes.get(getParameter)));
        }
    }

    public static  ArrayList<HashMap<String, String>> getResultForGivenIds(ArrayList<Integer> idList,
                                                              HashMap<Integer, HashMap<String, String>> deliveries) {
        ArrayList<HashMap<String, String>> res = new ArrayList<>();
        int COUNT =0;
        for(int id:idList) {
            for (Integer i : deliveries.keySet()) {
                if(COUNT++==0)
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
        return res;
    }
    public static Integer parseIntFromString(String runsInString) {
        if(runsInString!=null && runsInString.matches("\\d+") )
        {
           return Integer.parseInt(runsInString);
        }
        else {
            return 0;
        }
    }
    public static <K, V extends Comparable<? super V>> void sortAndPrintSolution(HashMap<K, V> mapToBeSorted){



        List<Map.Entry<K, V>> list = new ArrayList<>(mapToBeSorted.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        LinkedHashMap<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<K, V> entry : sortedMap.entrySet()) {
            System.out.printf(entry.getKey() + ": " + "%.2f%n",entry.getValue());
        }
        System.out.println();

    }
    public static <K, V> void printSolution(Map<K,V> yearAndWins)
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