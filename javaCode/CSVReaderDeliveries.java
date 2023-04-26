package javaCode;
import java.io.*;
import java.util.*;

public class CSVReaderDeliveries {
    private Scanner s;

    HashMap<Integer,HashMap<String,String>> mapOfDeliveries = new HashMap<>();
    public void loadFile(){
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/deliveries.csv"));

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
            mapOfDeliveries.put(count++,md.getMap());
        }
    }
    public void printArrayList(){

        for(Integer key :mapOfDeliveries.keySet())
        {
            System.out.print(key+" ");
            System.out.println(mapOfDeliveries.get(key));

        }
    }
}
