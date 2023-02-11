package core.basesyntax;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
       String[] salaryInfo = new String[names.length];
        LocalDate startPeriod = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endPeriod = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate checkDate = null;
        int sum = 0;
        int k = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            for (int y = 0; y < data.length; y++) {
                String[] splitedData = data[y].split(" ");
                checkDate = LocalDate.parse(splitedData[0], DATE_FORMATTER);
                if ((!checkDate.isBefore(startPeriod)) && (!checkDate.isAfter(endPeriod)) && names[i].contains(splitedData[1])) {
                    sum += (Integer.parseInt(splitedData[2]) * Integer.parseInt(splitedData[3]));
                }
                        salaryInfo[i] = name + " - " + sum;
            }
            sum = 0;
        }
        StringBuilder builder2 = new StringBuilder("Report for period " + dateFrom + " - " + dateTo + "\n");
        for (String info : salaryInfo) {
            builder2.append(info).append("\n");
        }
        return builder2.toString();
    }

    public static void main(String[] args) {
        String[] test = new String[]{
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100"
        };
        String[] test2 = new String[]{
                "John",
                "Andrew",
                "John"
        };

        SalaryInfo info = new SalaryInfo();
        System.out.println(info.getSalaryInfo(test2, test, "22.04.2019", "25.06.2019"));
    }
}
//he LocalDate class represents a date-only value, without time-of-day and without time zone.
//
//LocalDate start = LocalDate.of( 2016 , 1 , 1 ) ;
//LocalDate stop = LocalDate.of( 2016 , 1 , 23 ) ;
//To get the current date, specify a time zone. At any given moment, today’s date varies by time zone. For example, a new day dawns earlier in Paris than in Montréal.
//
//LocalDate today = LocalDate.now( ZoneId.of( "America/Montreal" ) );
//We can use the isEqual, isBefore, and isAfter methods to compare. In date-time work we commonly use the Half-Open approach where the beginning of a span of time is inclusive while the ending is exclusive.
//
//Boolean containsToday = ( ! today.isBefore( start ) ) && ( today.isBefore( stop ) ) ;