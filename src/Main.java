import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long stopWatch = System.nanoTime();

        List<Map<String,String>> matches = getMatches();

        List<Map<String, String>> deliveries = getDeliveries();
        findMatchesPerYear(matches);
        findEveryTeamsTotalWins(matches);
        extraRunsConcededPerTeamIn2016(matches,deliveries);
        getTheTopEconomicalBowlersOf2015(matches,deliveries);
        getWicketsPerTeam(deliveries);

        System.out.println((System.nanoTime() - stopWatch) / 1000000);
    }
    public static void extraRunsConcededPerTeamIn2016(List<Map<String,String>> listOfMatches,List<Map<String,String>> listOfDeliveries) {
        HashMap<String,Integer> teamsAndExtras = new HashMap<>();

        ArrayList<Integer> listOfIds = getIdsForGivenParameter(listOfMatches,"2016");
        List<Map<String, String>> listOfDeliveriesForGivenIds= getResultForGivenIds(listOfIds, listOfDeliveries);

        updateTeamExtraRuns(teamsAndExtras,listOfDeliveriesForGivenIds,"bowling_team","extra_runs");

        printSolution(teamsAndExtras);
    }
    private static void findEveryTeamsTotalWins(List<Map<String, String>> matches) {


            HashMap<String, Integer> everyTeamsTotalWins = new HashMap<>();

            for (Map<String,String> match : matches) {
                String name = match.get("winner");
                if(name!=null && !name.contains("winner") && match.get("result").contains("normal"))
                    everyTeamsTotalWins.put(name, everyTeamsTotalWins.getOrDefault(name, 0) + 1);
            }
            printSolution(everyTeamsTotalWins);

        }
       public static  List<Map<String,String>> getDeliveries() {
        List<Map<String,String>> deliveries = new ArrayList<>();
        String path = "/home/katziio/Desktop/IplProject/resources/deliveries.csv";
        try (BufferedReader fileReaderForDeliveries = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fileReaderForDeliveries.readLine()) != null) {
                String[] arr = line.split(",");
               Delivery delivery = new Delivery(arr);
                deliveries.add(delivery.getMap());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deliveries;
    }
    public static List<Map<String,String>> getMatches() {
        List<Map<String,String>> matches = new ArrayList<>();
        String path = "/home/katziio/Desktop/IplProject/resources/matches.csv";
        try (BufferedReader fileReaderOfMatches = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fileReaderOfMatches.readLine()) != null) {
                String[] arr = line.split(",");
                Match match = new Match(arr);
                matches.add(match.getMap());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return  matches;
    }
    public static void findMatchesPerYear(  List<Map<String,String>> matches)
    {
        Map<Integer,Integer> yearAndWins= new TreeMap<>();
        for(Map match : matches)
        {
            for(int YEAR = 2008; YEAR <= 2017 ; YEAR++)
            {
                if(match.containsValue(new String(String.valueOf(YEAR)))){
                    yearAndWins.put(YEAR,yearAndWins.getOrDefault(YEAR,0) + 1);
                }
            }
        }
        printSolution(yearAndWins);
    }
    public static void getTheTopEconomicalBowlersOf2015(List<Map<String,String>> getMapOfMatch,List<Map<String,String>> getMapOfDeliveries)
    {
        HashMap<String, Integer> noOfDeliveriesBowledByBowlers = new HashMap<>();
        HashMap<String, Double> bowlersAndEconomy = new HashMap<>();
        HashMap<String, Integer> runsConcededBowler = new HashMap<>();

        ArrayList<Integer> listOfIds = getIdsForGivenParameter(getMapOfMatch, "2015");
        List<Map<String, String>> listOfDeliveriesForGivenIds = getResultForGivenIds(listOfIds, getMapOfDeliveries);

        getBowlersName(noOfDeliveriesBowledByBowlers, listOfDeliveriesForGivenIds);
        getDeliveriesForGivenIds(noOfDeliveriesBowledByBowlers, listOfDeliveriesForGivenIds);
        getBowlersName(runsConcededBowler, listOfDeliveriesForGivenIds);
        getRunConcededByBowler(runsConcededBowler, listOfDeliveriesForGivenIds);
        getEconomyOfBowler(bowlersAndEconomy, runsConcededBowler, noOfDeliveriesBowledByBowlers);

        sortAndPrintSolution(bowlersAndEconomy);
    }
    public static void getWicketsPerTeam(List<Map<String, String>> matches) {
        HashMap<String,Integer> getWicketsPerTeam = new HashMap<>();

        for(Map<String,String> match : matches) {
            String name = match.get("bowling_team");
            if (match.get("dismissal_kind") != null) {
                getWicketsPerTeam.put(name, getWicketsPerTeam.getOrDefault(name, 0) + 1);
            }
        }

        printSolution(getWicketsPerTeam);
    }
    public static  List<Map<String, String>> getResultForGivenIds(ArrayList<Integer> idList,
                                                                           List<Map<String, String>> deliveries) {
        List<Map<String, String>> res = new ArrayList<>();
        int COUNT =0;
        for(int id:idList) {
            for (Map<String,String> delivery : deliveries) {
                if(COUNT++==0)
                {
                    continue;
                }

                if(delivery.get("matchId")!=null && delivery.get("matchId").toString().matches("\\d+") && Integer.parseInt(delivery.get("matchId"))==id )
                {
                    res.add(delivery);
                }
            }
        }
        return res;
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
    public static void getRunConcededByBowler(HashMap<String, Integer> bowlerRuns,
                                              List<Map<String, String>> listOfResultOfRequiredParameter) {
        for(Map<String,String> delivery : listOfResultOfRequiredParameter)
        {
            String bowler = delivery.get("bowler");
            int totalRunsConceded = parseIntFromString(delivery.get("total_runs")) - parseIntFromString(delivery.get("bye_runs"))- parseIntFromString(delivery.get("legbye_runs"));
            if(bowlerRuns.containsKey(bowler)) {
                bowlerRuns.put(bowler, bowlerRuns.getOrDefault(bowler, 0) + totalRunsConceded);
            }
        }
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
    public static void getDeliveriesForGivenIds(HashMap<String, Integer> bowlers,
                                                List<Map<String, String>> listOfDeliveriesForGivenIds) {

        for(Map<String,String> delivery : listOfDeliveriesForGivenIds)
        {
            String bowler = delivery.get("bowler");
            if(bowlers.containsKey(bowler) && delivery.get("noball_runs").contains("0") && delivery.get("wide_runs").contains("0")) {
                bowlers.put(bowler, bowlers.getOrDefault(bowler, 0) + 1);
            }
        }
    }
    public static ArrayList<Integer> getIdsForGivenParameter(
            List<Map<String, String>> mapOfMatch, String parameter){

        String toAddInList ="id";
        ArrayList<Integer> ID = new ArrayList<>();
        for(Map<String,String> match : mapOfMatch)
        {
            if(match.get("date").contains(parameter)){
                ID.add(Integer.parseInt(match.get(toAddInList)));
            }
        }
        return ID;
    }
    public static void getBowlersName(HashMap<String, Integer> bowlers,
                                      List<Map<String, String>> res) {

        for(Map<String,String> bowler : res)
        {
            String key = bowler.get("bowler");

            bowlers.put(key,0);
        }
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
    public static void updateTeamExtraRuns(HashMap<String, Integer> map,
                                        List<Map<String, String>> idsToFilteredMatches, String setParameter, String getParameter) {
        for(Map<String,String> idOfMatch : idsToFilteredMatches)
        {
            String team = idOfMatch.get(setParameter);

            map.put(team,map.getOrDefault(team,0) + parseIntFromString(idOfMatch.get(getParameter)));
        }
        System.out.println(map);
    }
}