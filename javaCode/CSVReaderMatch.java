package javaCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class CSVReaderMatch {

    private Scanner s;
    ArrayList<String[]> listOfMatch = new ArrayList<>();
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
        while(s.hasNext())
        {
            String[] arr = s.nextLine().split(",");
            listOfMatch.add(arr);
        }
    }
    public void printArrayList(){

        for(String[] i:this.listOfMatch)
        {
            System.out.println(Arrays.toString(i));
            System.out.println("=====================================");

        }
    }
}
