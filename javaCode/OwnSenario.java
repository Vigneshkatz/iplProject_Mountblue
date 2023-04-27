package javaCode;

import java.util.*;

public class OwnSenario {

    public static void main(String[] args) {
        CSVReaderDeliveries deliveries = new CSVReaderDeliveries();
        CSVReaderMatch matches = new CSVReaderMatch();
        HashMap<Integer,String> purpleCap = new HashMap<>();
        for(int i = 2008;i<=2017;i++)
        {
            HashMap<String,Integer> bowlersName = new HashMap<>();
            HashMap<String,Integer> bowlersWicket = new HashMap<>();
            ArrayList<Integer> idList = getIds(matches.getMapOfMatch(),Integer.toString(i));
            ArrayList<HashMap<String, String>> res = getRes(idList,deliveries.getMapOfDeliveries());
            updateWicket(bowlersWicket,res);
            mostWicket(purpleCap,bowlersWicket,i);
        }
        System.out.println(purpleCap);
    }

    private static void mostWicket(HashMap<Integer, String> purpleCap, HashMap<String, Integer> bowlersWicket,int year) {

        int max = Integer.MIN_VALUE;

        StringBuilder bowler = null;
        for(String key : bowlersWicket.keySet())
        {
            if(max < bowlersWicket.get(key))
            {
                max = bowlersWicket.get(key);
                bowler= new StringBuilder(key);
            }
        }
        purpleCap.put(year,bowler.toString());



    }

    private static void updateWicket(HashMap<String, Integer> bowlers,
                                     ArrayList<HashMap<String, String>> res) {

        for(HashMap<String,String> i : res)
        {
            String bowlerName = i.get("bowler");
            String wicket = i.get("player_dismissed");
            if(wicket!=null)
            {
                bowlers.put(bowlerName,bowlers.getOrDefault(bowlerName,0)+1);
            }
//            System.out.println(wicket);
            if(bowlers.containsKey(wicket)) {
                bowlers.put(wicket, bowlers.getOrDefault(wicket, 0) + 1);
            }
        }
    }
    private static ArrayList<Integer> getIds(
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
    private static Integer parseIntFromString(String totalRuns) {


        if(totalRuns!=null &&totalRuns.matches("\\d+") )
        {
            int out = Integer.parseInt(totalRuns);
//            System.out.println(out);
            return out;
        }
        else {
            return 0;
        }
    }
    private static void updateBowlersName(HashMap<String, Integer> bowlers, ArrayList<HashMap<String, String>> res) {

        for(HashMap<String,String> i : res)
        {
            String key = i.get("bowler");
            if(bowlers.containsKey(key))
            {
                continue;
            }else{
                bowlers.put(key,0);
            }
        }
//        System.out.println(bowlers.entrySet());
    }

    private static ArrayList<HashMap<String, String>> getRes(ArrayList<Integer> idList,
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

    static void sortingHashMap(HashMap<String, Integer> map){


    }
//    static void nextSorting()
//    {
//
//        Integer economyMax = Integer.MAX_VALUE;
//        StringBuilder bowlerName= new StringBuilder("");
//        for(String bowler : map.keySet())
//        {
//            if(map.get(bowler) < economyMax)
//            {
//                economyMax= map.get(bowler);
//                bowlerName= new StringBuilder(bowler);
//            }
//        }
//    }
}
