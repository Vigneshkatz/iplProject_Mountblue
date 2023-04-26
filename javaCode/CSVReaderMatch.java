package javaCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class CSVReaderMatch {

    private Scanner s;
    private HashMap<Integer,HashMap<String,String>> mapOfMatch = new HashMap<>();
    public CSVReaderMatch(){
//        incase file not found
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/matches.csv"));

        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        
        update();
    }

    public HashMap<Integer, HashMap<String, String>> getMapOfMatch() {
        return mapOfMatch;
    }

    //       to update the file using update function
    public void update()
    {
        int count = 1;
        while(s.hasNext())
        {
            String[] arr = s.nextLine().split(",");
            MatchDecoding md = new MatchDecoding(arr);
            mapOfMatch.put(count++,md.getMap());
        }
    }
//    to check the date using printArrayList function
    public void printArrayList(){

        for(Integer key :mapOfMatch.keySet())
        {
            System.out.print(key+" ");
            System.out.println(mapOfMatch.get(key));

        }
    }
}
