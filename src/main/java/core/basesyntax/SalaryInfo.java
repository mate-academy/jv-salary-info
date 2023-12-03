package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromNow = LocalDate.parse(dateFrom, df);
        LocalDate dateToNow = LocalDate.parse(dateTo, df);
        int[] countSalary = new int [names.length];
        for (String datum : data) {
            String[] currentStringInfo = datum.split(" ");
            for (int j = 0; j < names.length; j++) {
                LocalDate today = LocalDate.parse(currentStringInfo[0], df);
                if (names[j].equals(currentStringInfo[1]) && !dateFromNow.isAfter(today)
                        && !(dateToNow.isBefore(today))) {

                    countSalary[j] += Integer.parseInt(currentStringInfo[2])
                            * Integer.parseInt(currentStringInfo[3]);
                }
            }
        }
        return new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator())
                .append(names[0])
                .append(" - ")
                .append(countSalary[0])
                .append(System.lineSeparator())
                .append(names[1])
                .append(" - ")
                .append(countSalary[1])
                .append(System.lineSeparator())
                .append(names[2])
                .append(" - ")
                .append(countSalary[2])
                .toString();
    }
}
