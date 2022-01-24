package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int daySalaryAmount = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");
            for (String datum : data) {
                String[] dayPersonInfo = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(dayPersonInfo[DATE_INDEX], FORMATTER);
                int hours = Integer.parseInt(dayPersonInfo[HOURS_INDEX]);
                int salary = Integer.parseInt(dayPersonInfo[SALARY_INDEX]);
                if (name.equals(dayPersonInfo[NAME_INDEX])
                        && currentDate.isAfter(fromDate)
                        && currentDate.isBefore(toDate.plusDays(1))) {
                    daySalaryAmount += hours * salary;
                }
            }
            result.append(daySalaryAmount);
        }
        return result.toString();
    }
}
