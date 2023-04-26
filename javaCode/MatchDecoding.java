package javaCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MatchDecoding {
     private HashMap<String, String> map = new HashMap<>();

     public HashMap<String, String> getMap() {
          return map;
     }



     public  MatchDecoding(String[] arr)
     {

          map.put("id",arr[0]);
          map.put("season",arr[1]);
          map.put("city",arr[2]);
          map.put("date",arr[3]);
          map.put("team1",arr[4]);
          map.put("team2",arr[5]);
          map.put("tossWinner",arr[6]);
          map.put("tossDecision",arr[7]);
          map.put("result",arr[8]);
          map.put("dlApplied",arr[9]);
          map.put("winner",arr[10]);
          map.put("winByRun",arr[11]);
          map.put("winByWicket",arr[12]);
          map.put("playerOfTheMatch",arr[13]);
          if(arr.length >= 15) {
               map.put("venue",arr[14]);
               if(arr.length >= 16) {
                    map.put("umpire1", arr[15]);
                    if (arr.length >= 17) {
                         map.put("umpire2", arr[16]);
                         if (arr.length >= 18) {
                              map.put("umpire3", arr[17]);
                         }else{
                              map.put("umpire3", null);

                         }
                    }else{

                         map.put("umpire2", null);
                         map.put("umpire3", null);

                    }
               }else{
                    map.put("umpire1", null);
                    map.put("umpire2", null);
                    map.put("umpire3", null);

               }
          }else{
               map.put("venue",null);
               map.put("umpire1", null);
               map.put("umpire2", null);
               map.put("umpire3", null);
          }
     }



}
