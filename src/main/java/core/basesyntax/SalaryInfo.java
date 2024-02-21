package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORK_DAY_INDEX = 0;
    private static final int WORKER_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(DASH).append(dateTo);
        for (String name : names) {
            LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
            int salary = 0;
            for (String datum : data) {
                String[] splitedData = datum.split(SPACE);
                LocalDate workDayDate = LocalDate.parse(splitedData[WORK_DAY_INDEX], FORMATTER);
                if (isNameValid(name, splitedData)
                        && isWorkDayValid(fromDate, toDate, workDayDate)) {
                    salary += Integer.parseInt(splitedData[HOURS_INDEX])
                            * Integer.parseInt(splitedData[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(DASH).append(salary);
        }
        return result.toString();
    }

    private boolean isNameValid(String name, String[] splitedData) {
        return name.equals(splitedData[WORKER_NAME_INDEX]);
    }

    private boolean isWorkDayValid(LocalDate fromDate, LocalDate toDate, LocalDate workDayDate) {
        return (toDate.isAfter(workDayDate) && fromDate.isBefore(workDayDate))
                || workDayDate.isEqual(fromDate)
                || workDayDate.isEqual(toDate);
    }
}
