package javaCode;
import java.io.*;
import java.util.*;

public class CSVReaderDeliveries {
    private Scanner s;
    public void loadFile(){
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/deliveries.csv"));
            s.useDelimiter(",");
        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
    public void print()
    {
        while(s.hasNext())
        {
            System.out.println(s.next());
            System.out.println("=====");
        }
    }
}
