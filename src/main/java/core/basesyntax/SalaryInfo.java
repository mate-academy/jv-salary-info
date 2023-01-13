package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKDAY_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate datFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate datTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info: data) {
                String[] infoDates = info.split(" ");
                LocalDate workDay = LocalDate.parse(infoDates[DATA_INDEX], FORMATTER);
                if (infoDates[NAME_INDEX].equals(name) && (workDay.isAfter(datFrom)
                        || workDay.isEqual(datFrom))
                        && (workDay.isBefore(datTo) || workDay.isEqual(datTo))) {
                    salary += Integer.parseInt(infoDates[WORKDAY_INDEX])
                            * Integer.parseInt(infoDates[INCOME_INDEX]);
                }
            }
            finalResult.append(System.getProperty("line.separator"))
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return finalResult.toString();
    }
}
