package javaCode;
import java.io.*;
import java.util.*;

public class CSVReaderDeliveries {
    private Scanner s;

    private HashMap<Integer,HashMap<String,String>> mapOfDeliveries = new HashMap<>();
    public CSVReaderDeliveries(){
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/deliveries.csv"));

        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        update();
    }

    public HashMap<Integer, HashMap<String, String>> getMapOfDeliveries() {
        return this.mapOfDeliveries;
    }

    //    to update the map
    public void update()
    {
        int count = 1;
        while(s.hasNext())
        {
            String[] arr = s.nextLine().split(",");
            DeliveryDecoding md = new DeliveryDecoding(arr);
            mapOfDeliveries.put(count++,md.getMap());
        }
    }
//    to check the date loaded or not
    public void printArrayList(){

        for(Integer key :mapOfDeliveries.keySet())
        {
            System.out.print(key+" ");
            System.out.println(mapOfDeliveries.get(key));

        }
    }
}
