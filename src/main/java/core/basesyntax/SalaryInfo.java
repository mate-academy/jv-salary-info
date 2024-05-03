package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy",Locale.ENGLISH);
    private static final int CURRENT_DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PRICE_PER_HOUR = 3;
    private static final int JOHN = 0;
    private static final int ANDREW = 1;
    private static final int KATE = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;
        for (String dateSalary : data) {
            String[] firstLineArrays = dateSalary.split(" ");
            LocalDate firstArraysDate = LocalDate.parse(firstLineArrays[CURRENT_DATE],FORMATTER);
            if (localDateFrom.compareTo(firstArraysDate) <= 0
                    && localDateTo.compareTo(firstArraysDate) >= 0) {
                if (names[JOHN].equals(firstLineArrays[NAME])) {
                    johnSalary += (Integer.parseInt(firstLineArrays[HOURS])
                            * Integer.parseInt(firstLineArrays[PRICE_PER_HOUR]));
                } else if (names[ANDREW].equals(firstLineArrays[NAME])) {
                    andrewSalary += (Integer.parseInt(firstLineArrays[HOURS])
                            * Integer.parseInt(firstLineArrays[PRICE_PER_HOUR]));
                } else if (names[KATE].equals(firstLineArrays[NAME])) {
                    kateSalary += (Integer.parseInt(firstLineArrays[HOURS])
                            * Integer.parseInt(firstLineArrays[PRICE_PER_HOUR]));
                }
            }
        }
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        salaryInfo.append(System.lineSeparator() + names[JOHN] + " - " + johnSalary);
        salaryInfo.append(System.lineSeparator() + names[ANDREW] + " - " + andrewSalary);
        salaryInfo.append(System.lineSeparator() + names[KATE] + " - " + kateSalary);
        return salaryInfo.toString();
    }
}
