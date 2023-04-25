package javaCode;
import java.io.*;
import java.util.*;

public class CSVReaderDeliveries {
    private Scanner s;
    ArrayList<String[]> listOfDel = new ArrayList<>();
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
        while(s.hasNext())
        {
            String[] arr = s.nextLine().split(",");
            listOfDel.add(arr);
        }
    }
    public void printArrayList(){

        for(String[] i:this.listOfDel)
        {
            System.out.println(Arrays.toString(i));
            System.out.println("=====================================");

        }
    }
}
