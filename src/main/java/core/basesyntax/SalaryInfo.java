package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String DIVIDER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultInfo = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sumSalary = 0;
            for (String datum : data) {
                String[] parsData = datum.split(DIVIDER);
                LocalDate dateFromNow = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate dateToNow = LocalDate.parse(dateTo, FORMATTER);
                LocalDate dateWork = LocalDate.parse(parsData[DATE_INDEX], FORMATTER);
                if (name.equals(parsData[NAME_INDEX])
                        && checkDate(dateWork, dateFromNow, dateToNow)) {
                    int hour = Integer.parseInt(parsData[WORK_HOUR_INDEX]);
                    int salary = Integer.parseInt(parsData[INCOME_PER_HOUR_INDEX]);
                    sumSalary += hour * salary;
                }
            }
            resultInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
        }
        return resultInfo.toString();
    }

    public boolean checkDate(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }
}
