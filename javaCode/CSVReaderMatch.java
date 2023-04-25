package javaCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReaderMatch {

    private Scanner s;
    public void loadFile(){
        try {
            this.s = new Scanner(new File("/home/katziio/Desktop/iplProject/matches.csv"));
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
        }
    }
}
