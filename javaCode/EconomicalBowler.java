package javaCode;

import java.util.*;


public class EconomicalBowler {
    public static void main(String[] args) {
        CSVReaderDeliveries deliveries = new CSVReaderDeliveries();
        CSVReaderMatch matches = new CSVReaderMatch();
        HashMap<String,Integer> bowlersDeliveries = new HashMap<>();
        HashMap<String,Double> map = new HashMap<>();
        ArrayList<Integer> idList = getIds(matches.getMapOfMatch(),"2015");
        ArrayList<HashMap<String, String>> res = getRes(idList,deliveries.getMapOfDeliveries());
        updateBowlersName(bowlersDeliveries,res);
        updateDeliveries(bowlersDeliveries,res);
        HashMap<String,Integer> bowlerRuns = new HashMap<>();
        updateBowlersName(bowlerRuns,res);
        updateRuns(bowlerRuns,res);
        updateEconomy(map,bowlerRuns,bowlersDeliveries);
        sortingHashMap(map);
//        HashMap<String,Double> economy = new HashMap<>();

//
//        double economyMax = Integer.MAX_VALUE;
//        StringBuilder bowlerName= new StringBuilder("");
//        for(String bowler : map.keySet())
//        {
//            if(map.get(bowler) < economyMax)
//            {
//                economyMax= map.get(bowler);
//                bowlerName= new StringBuilder(bowler);
//            }
//        }
//        economy.put(String.valueOf(bowlerName),economyMax);
//        System.out.println(economy);
    }


    private static void updateEconomy(HashMap<String, Double> map, HashMap<String, Integer> bowlerRuns, HashMap<String, Integer> bowlersDeliveries) {
            for(String bowler : bowlerRuns.keySet())
            {
                double economy = bowlerRuns.get(bowler) / (bowlersDeliveries.get(bowler)/6);
                map.put(bowler,economy);
            }
    }

    static void sortingHashMap(HashMap<String, Double> map){
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                // Sort in ascending order
                return o1.getValue().compareTo(o2.getValue());
                // For descending order, use o2.getValue().compareTo(o1.getValue());
            }
        });
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }



    private static void updateRuns(HashMap<String, Integer> bowlerRuns, ArrayList<HashMap<String, String>> res) {
        for(HashMap<String,String> i : res)
        {
            String bowler = i.get("bowler");
            if(bowlerRuns.containsKey(bowler)) {
                bowlerRuns.put(bowler, bowlerRuns.getOrDefault(bowler, 0) + parseIntFromString(i.get("total_runs")));
            }
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

    private static void updateDeliveries(HashMap<String, Integer> bowlers, ArrayList<HashMap<String, String>> res) {

        for(HashMap<String,String> i : res)
        {
            String bowler = i.get("bowler");
            if(bowlers.containsKey(bowler)) {
                bowlers.put(bowler, bowlers.getOrDefault(bowler, 0) + 1);
            }
        }
    }

    private static ArrayList<HashMap<String, String>> getRes(ArrayList<Integer> idList, HashMap<Integer, HashMap<String, String>> mapOfDeliveries) {
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

}
