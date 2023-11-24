package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ARRAY_INDEX_FOR_DATE = 0;
    private static final int ARRAY_INDEX_FOR_NAME = 1;
    private static final int ARRAY_INDEX_FOR_WORKING_HOURS = 2;
    private static final int ARRAY_INDEX_FOR_INCOME_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        StringBuilder salaryInfo = new StringBuilder();
        for (String name : names) {
            for (String datum : data) {
                String[] dataEntry = datum.split(" ");
                if (name != null && name.equals(dataEntry[ARRAY_INDEX_FOR_NAME])
                        && isDatePass(dataEntry[ARRAY_INDEX_FOR_DATE], dateFrom, dateTo)) {
                    salary += Integer.parseInt(dataEntry[ARRAY_INDEX_FOR_WORKING_HOURS])
                            * Integer.parseInt(dataEntry[ARRAY_INDEX_FOR_INCOME_PER_HOUR]);
                }
            }
            if (name != null && name.equals(names[names.length - 1])) {
                salaryInfo.append(name).append(" - ").append(salary);
            } else if (name != null) {
                salaryInfo.append(name).append(" - ").append(salary)
                        .append(System.lineSeparator());
            }
            salary = 0;
        }
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator()
                + salaryInfo;
    }

    public boolean isDatePass(String datum, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        LocalDate date = LocalDate.parse(datum, FORMATTER);
        return from.compareTo(date) <= 0 && to.compareTo(date) >= 0;
    }
}

