package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String record : data) {
                String[] recordData = record.split(" ");
                LocalDate recordDate = LocalDate.parse(recordData[DATE_INDEX], formatter);
                if (recordData[NAME_INDEX].equals(name)
                        && (recordDate.isAfter(from) || recordDate.isEqual(from))
                        && (recordDate.isBefore(to) || recordDate.isEqual(to))) {
                    salary += Integer.parseInt(recordData[HOURS_INDEX])
                            * Integer.parseInt(recordData[SALARY_INDEX]);
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
