package javaCode;

import java.util.Collections;

public class Demo {
    public static void main(String[] args)
    {
        CSVReaderDeliveries deliveries = new CSVReaderDeliveries();
        CSVReaderMatch match = new CSVReaderMatch();
        deliveries.loadFile();;
        deliveries.print();
        match.loadFile();
        match.print();

    }
}
