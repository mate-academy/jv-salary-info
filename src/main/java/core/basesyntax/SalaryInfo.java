package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE_IND = 0;
    private static final int NAME_IND = 1;
    private static final int HOUR_QUANTITY_IND = 2;
    private static final int SALARY_PER_HOUR_IND = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder res = new StringBuilder();
        res.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int totalSalary;
        int[] totalMoney = new int[names.length];
        for (String name : names) {
            totalSalary = 0;
            for (String dataLine : data) {
                String[] dataElements = dataLine.split("\\s");
                if (name.equals(dataElements[NAME_IND])
                        && isDateInRange(dataElements[DATE_IND], dateFrom, dateTo)) {
                    totalSalary += Integer.parseInt(dataElements[HOUR_QUANTITY_IND])
                            * Integer.parseInt(dataElements[SALARY_PER_HOUR_IND]);
                }
            }
            res.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        return LocalDate.parse(date, FORMAT)
                .isAfter(LocalDate.parse(dateFrom, FORMAT).minusDays(1))
                && LocalDate.parse(date, FORMAT)
                .isBefore(LocalDate.parse(dateTo, FORMAT).plusDays(1));
    }
}
