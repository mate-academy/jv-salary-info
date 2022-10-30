package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HYPHEN = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period ").append(dateFrom)
                .append(HYPHEN).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String records : data) {
                String[] splited = records.split(" ");
                LocalDate date = LocalDate.parse(splited[DATE_INDEX], FORMATTER);
                if (name.equals(splited[NAME_INDEX]) && (localDateFrom.equals(date)
                        || (localDateFrom.isBefore(date)) && (localDateTo.equals(date)
                        || localDateTo.isAfter(date)))) {
                    salary = salary + Integer.parseInt(splited[HOURS_INDEX])
                            * Integer.parseInt(splited[INCOME_INDEX]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(HYPHEN).append(salary);
        }
        return salaryInfo.toString();
    }
}
