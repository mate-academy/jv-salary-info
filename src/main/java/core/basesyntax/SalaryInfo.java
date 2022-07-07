package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        StringBuilder salaryInfoBuilder = new StringBuilder();
        StringBuilder reportForPeriod = new StringBuilder("Report for period ");
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] day = data[i].split(" ");
                String nameFromData = day[NAME_INDEX];
                int workinghours = Integer.parseInt(day[HOURS_INDEX]);
                int income = Integer.parseInt(day[INCOME_PER_HOUR_INDEX]);
                LocalDate date = getDate(day[DATE_INDEX]);
                if (name.equals(nameFromData) && (date.isAfter(localDateFrom)
                        || date.equals(localDateFrom)) && (date.isBefore(localDateTo)
                        || date.equals(localDateTo))) {
                    salary = salary + workinghours * income;
                }
            }
            salaryInfoBuilder = salaryInfoBuilder.append(System.lineSeparator())
                            .append(name).append(" - ").append(salary);
        }
        return reportForPeriod.append(dateFrom).append(" - ").append(dateTo)
                .append(salaryInfoBuilder).toString();
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
