package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        StringBuilder salaryInfoBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] splittedLine = datum.split(" ");
                String nameFromData = splittedLine[NAME_INDEX];
                int workingHours = Integer.parseInt(splittedLine[HOURS_INDEX]);
                int income = Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                LocalDate date = getDate(splittedLine[DATE_INDEX]);
                if (name.equals(nameFromData) && (date.isAfter(localDateFrom)
                        || date.equals(localDateFrom)) && (date.isBefore(localDateTo)
                        || date.equals(localDateTo))) {
                    salary = salary + workingHours * income;
                }
            }
            salaryInfoBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return salaryInfoBuilder.toString();
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
