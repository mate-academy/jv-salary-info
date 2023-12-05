package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyy");
    private static final int DEFAULT_SALARY = 0;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name: names) {
            int salary = DEFAULT_SALARY;
            for (String line: data) {
                String[] dataPeaces = line.split(" ");
                if (!getLocalDate(dataPeaces[DATE_INDEX]).isBefore(getLocalDate(dateFrom))
                        && !getLocalDate(dataPeaces[DATE_INDEX]).isAfter(getLocalDate(dateTo))
                        && name.equals(dataPeaces[NAME_INDEX])) {
                    salary += getIntFromString(dataPeaces[HOURS_INDEX])
                            * getIntFromString(dataPeaces[RATE_PER_HOUR_INDEX]);
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return builder.toString().replaceAll("[\n\r]$", "");
    }

    public LocalDate getLocalDate(String date) {
        return LocalDate.parse(date,FORMATTER);
    }

    public int getIntFromString(String number) {
        return Integer.parseInt(number);
    }
}
