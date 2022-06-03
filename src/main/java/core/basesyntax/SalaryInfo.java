package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom,DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int[] salary = new int[names.length];
        StringBuilder resultString = new StringBuilder();
        for (String datum : data) {
            String[] splitedData = datum.split(" ");
            LocalDate workDate = LocalDate.parse(splitedData[0],
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (workDate.isBefore(toDate) && workDate.isAfter(fromDate)
                    || workDate.equals(toDate) || workDate.equals(fromDate)) {
                for (int k = 0; k < names.length; k++) {
                    if (splitedData[1].equals(names[k])) {
                        salary[k] += Integer.parseInt(splitedData[2])
                                * Integer.parseInt(splitedData[3]);
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
