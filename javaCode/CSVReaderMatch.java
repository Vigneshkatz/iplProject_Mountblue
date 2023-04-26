package javaCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class CSVReaderMatch {

    private Scanner s;
    HashMap<Integer,HashMap<String,String>> mapOfMatch = new HashMap<>();
    public void loadFile(){
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/matches.csv"));

        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
    public Scanner getS() {
        return s;
    }
    public void setS() {
        loadFile();
    }
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
    public void printArrayList(){

        for(Integer key :mapOfMatch.keySet())
        {
            System.out.print(key+" ");
            System.out.println(mapOfMatch.get(key));

        }
    }
}
