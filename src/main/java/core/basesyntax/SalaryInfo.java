package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        int sum = 0;
        int sum2 = 0;
        int sum3 = 0;
        StringBuilder builder = new StringBuilder();
        for (String str : data) {
            String[] strSplit = str.split(" ");
            LocalDate localStrSplit = LocalDate.parse(strSplit[0], formatter);
            if (strSplit[1].equals(names[0]) && localStrSplit.isAfter(localDateFrom)
                    && (localStrSplit.isAfter(localDateFrom)
                    || localStrSplit.isEqual(localDateFrom))
                    && (localStrSplit.isBefore(localDateTo)
                    || localStrSplit.isEqual(localDateTo))) {
                sum += Integer.parseInt(strSplit[2])
                        * Integer.parseInt(strSplit[3]);
            }
            if (strSplit[1].equals(names[1])
                    && (localStrSplit.isAfter(localDateFrom)
                    || localStrSplit.isEqual(localDateFrom))
                    && (localStrSplit.isBefore(localDateTo)
                    || localStrSplit.isEqual(localDateTo))) {
                sum2 += Integer.parseInt(strSplit[2])
                        * Integer.parseInt(strSplit[3]);
            }
            if (strSplit[1].equals(names[2]) && localStrSplit.isAfter(localDateFrom)
                    && (localStrSplit.isAfter(localDateFrom)
                    || localStrSplit.isEqual(localDateFrom))
                    && (localStrSplit.isBefore(localDateTo)
                    || localStrSplit.isEqual(localDateTo))) {
                sum3 += Integer.parseInt(strSplit[2])
                        * Integer.parseInt(strSplit[3]);
            }
        }
        builder.append("Report for period " + dateFrom + " - " + dateTo)
                .append(System.lineSeparator())
                .append("John - " + sum).append(System.lineSeparator())
                .append("Andrew - " + sum2).append(System.lineSeparator())
                .append("Kate - " + sum3);
        return builder.toString();
    }
}
