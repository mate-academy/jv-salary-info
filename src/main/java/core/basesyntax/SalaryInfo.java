package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder majorSB = new StringBuilder("Report for period");
        String [] dateFromAR = dateFrom.split( " ");
        String [] dateToAR = dateTo.split( " ");
        String date1 = dateFromAR[dateFromAR.length -2].substring( 1);
        String date2 = dateToAR[dateFromAR.length -2].substring(1,dateToAR[dateFromAR.length -2].length()-1);
        majorSB.append(date1).append(" - ").append(date2).append(" ").append("\n");

        final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date1 = date1.replace(".","-");
        LocalDate dateFrom1 = LocalDate.parse(date1,pattern);
        date2 = date2.replace(".","-");
        LocalDate dateTo1 = LocalDate.parse(date2,pattern);

        for (int i = 0; i < names.length; i++){
            int money = 0;
            majorSB.append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++){
                String[] elements = data[j].split(" ");
                String workingDay = elements[0];
                workingDay = workingDay.replace(".","-");
                LocalDate workDayDate = LocalDate.parse(workingDay,pattern);
                if (workDayDate.compareTo(dateFrom1) >= 0 && workDayDate.compareTo(dateTo1) <= 0){
                    if (names[i].equals(elements[1])){
                        money += Integer.valueOf (Integer.valueOf(elements[2]) * Integer.valueOf(elements[3]));
                    }
                }
            }
            majorSB.append(money).append("\n");
        }
        return majorSB.toString();
    }
}
