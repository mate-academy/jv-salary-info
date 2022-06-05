package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String formatter = "dd.MM.yyyy";
    private static final String splitRegex = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(formatter));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(formatter));
        int[] salaryCounter = new int[names.length];
        StringBuilder resultString = new StringBuilder();
        for (int k = 0; k < names.length; k++) {
            for (String datum : data) {
                String[] splitData = datum.split(splitRegex);
                LocalDate workDay = LocalDate.parse(splitData[0],
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (workDay.isBefore(toDate) && workDay.isAfter(fromDate)
                        || workDay.equals(toDate) || workDay.equals(fromDate)) {
                    if (splitData[1].equals(names[k])) {
                        salaryCounter[k] += Integer.parseInt(splitData[2])
                                * Integer.parseInt(splitData[3]);
                    }
                }
            }
            resultString.append(System.lineSeparator())
                    .append(names[k])
                    .append(" - ")
                    .append(salaryCounter[k]);
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + resultString;
    }
}
