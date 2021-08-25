package main.java.core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int INDEXZERO = 0;
    public static final int INDEXONE = 1;
    public static final int INDEXTWO = 2;
    public static final int INDEXTHREE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER).minusDays(1);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int userSalary = 0;
            for (String string : data) {
                String[] userWorkingData = string.split(" ");
                LocalDate userDate = LocalDate.parse(userWorkingData[INDEXZERO], FORMATTER);
                if (name.equals(userWorkingData[INDEXONE]) && userDate.isAfter(fromDate)
                        && userDate.isBefore(toDate)) {
                    userSalary += Integer.parseInt(
                            userWorkingData[INDEXTWO])
                            * Integer.parseInt(userWorkingData[INDEXTHREE]);
                }
            }
            result.append(name).append(" - ").append(userSalary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
