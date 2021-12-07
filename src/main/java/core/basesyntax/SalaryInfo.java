package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int RATE_INDEX = 2;
    public static final int QUOTIENT_INDEX = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                  .append(dateFrom)
                  .append(" - ")
                  .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String showing : data) {
                String[] info = showing.split(" ");
                try {
                    LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
                    LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
                    LocalDate localDate = LocalDate.parse(info[DATE_INDEX], formatter);
                    if (localDate.isAfter(localDateFrom)
                            && localDate.isBefore(localDateTo.plusDays(1))
                            && (names[i].equals(info[NAME_INDEX]))) {
                        salary[i] += Integer.parseInt(info[RATE_INDEX])
                                * Integer.parseInt(info[QUOTIENT_INDEX]);
                    }
                } catch (DateTimeException e) {
                    throw new RuntimeException("Date is not parsable", e);
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return salaryInfo.toString();
    }
}
