package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class SalaryInfo {
    String formatter = "dd.MM.yyyy";
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom,DateTimeFormatter.ofPattern(formatter));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(formatter));
        int[] salary = new int[names.length];
        StringBuilder resultString = new StringBuilder();
        for (String datum : data) {
            String[] splitData = datum.split(" ");
            LocalDate workDay = LocalDate.parse(splitData[0],
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (workDay.isBefore(toDate) && workDay.isAfter(fromDate)
                    || workDay.equals(toDate) || workDay.equals(fromDate)) {
                for (int k = 0; k < names.length; k++) {
                    if (splitData[1].equals(names[k])) {
                        salary[k] += Integer.parseInt(splitData[2])
                                * Integer.parseInt(splitData[3]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            resultString.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }

        return "Report for period " + dateFrom + " - " + dateTo
                + resultString;
    }
}
