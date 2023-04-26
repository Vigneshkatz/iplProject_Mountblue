package javaCode;
import java.text.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args)
    {
        CSVReaderDeliveries deliveries = new CSVReaderDeliveries();
//        CSVReaderMatch match = new CSVReaderMatch();
//        match.loadFile();
//        match.update();
//        match.printArrayList();
        deliveries.loadFile();
        deliveries.update();
        deliveries.printArrayList();
    }
}
