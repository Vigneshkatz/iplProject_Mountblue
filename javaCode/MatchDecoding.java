package javaCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MatchDecoding {
     int id;
     int season;
     String city;
     Date date;
     String actualDate;
     String team1;
     String team2;
     String tossWinner;
     String tossDecision;
     String result;
     int dlApplied;
     String winner;
     int winByRun;
     int winByWicket;
     String playerOfTheMatch;
     String venue;
     String umpire1;
     String umpire2;
     String umpire3;

     public MatchDecoding(){

     }
     public MatchDecoding(int id, int season, String city, String date, String team1, String team2, String tossWinner, String tossDecision, String result, int dlApplied, String winner, int winByRun, int winByWicket, String playerOfTheMatch, String venue, String umpire1, String umpire2, String umpire3) {
          this.id = id;
          this.season = season;
          this.city = city;
          this.date =stringToDate(date);
          this.team1 = team1;
          this.team2 = team2;
          this.tossWinner = tossWinner;
          this.tossDecision = tossDecision;
          this.result = result;
          this.dlApplied = dlApplied;
          this.winner = winner;
          this.winByRun = winByRun;
          this.winByWicket = winByWicket;
          this.playerOfTheMatch = playerOfTheMatch;
          this.venue = venue;
          this.umpire1 = umpire1;
          this.umpire2 = umpire2;
          this.umpire3 = umpire3;
     }
     void print(){
          System.out.println(this.date);
     }
     Date stringToDate(String d) {
          Date s = null;
          try {
               s = new SimpleDateFormat("yyyy-mm-dd").parse(d);
          } catch (ParseException e) {
               System.out.println(e);
          }
          return s;
     }



}
