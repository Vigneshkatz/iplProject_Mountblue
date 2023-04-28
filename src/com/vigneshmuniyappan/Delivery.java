package com.vigneshmuniyappan;

import java.util.HashMap;

public class Delivery {

        private HashMap<String, String> map = new HashMap<>();
        public HashMap<String, String> getMap() {
            return map;
        }
        public Delivery(String[] arr)
        {
            map.put("matchId",arr[0]);
            map.put("inning",arr[1]);
            map.put("batting_team",arr[2]);
            map.put("bowling_team",arr[3]);
            map.put("over",arr[4]);
            map.put("ball",arr[5]);
            map.put("batsman",arr[6]);
            map.put("non_striker",arr[7]);
            map.put("bowler",arr[8]);
            map.put("is_super_over",arr[9]);
            map.put("wide_runs",arr[10]);
            map.put("bye_runs",arr[11]);
            map.put("legbye_runs",arr[12]);
            map.put("noball_runs",arr[13]);
            map.put("penalty_runs",arr[14]);
            map.put("batsman_runs",arr[15]);
            map.put("extra_runs",arr[16]);
            map.put("total_runs",arr[17]);
            if(arr.length >= 19) {
                map.put("player_dismissed", arr[18]);
                if(arr.length >=20) {
                    map.put("dismissal_kind", arr[19]);
                    if(arr.length >=21
                    ) {
                        map.put("fielder", arr[20]);
                    }
                    else{
                        map.put("fielder", null);
                    }
                }
                else{
                    map.put("player_dismissed", null);
                    map.put("dismissal_kind", null);
                }
            }else{
                map.put("player_dismissed", null);
                map.put("dismissal_kind", null);
                map.put("fielder", null);
            }
        }
}

