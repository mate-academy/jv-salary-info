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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String dateSalary : data) {
                String[] firstLineArrays = dateSalary.split(" ");
                LocalDate firstArraysDate = LocalDate.parse(
                        firstLineArrays[CURRENT_DATE], FORMATTER);
                if (localDateFrom.compareTo(firstArraysDate) <= 0
                        && localDateTo.compareTo(firstArraysDate) >= 0
                        && names[i].equals(firstLineArrays[NAME])) {
                    salary += (Integer.parseInt(firstLineArrays[HOURS])
                            * Integer.parseInt(firstLineArrays[PRICE_PER_HOUR]));
                }
            }
            salaryInfo.append(System.lineSeparator() + names[i] + " - " + salary);
        }
        return salaryInfo.toString();
    }
}
