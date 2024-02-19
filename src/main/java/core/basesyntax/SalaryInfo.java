package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int WORK_DAY_INDEX = 0;
    private static final int DAY_INDEX = 0;
    private static final int WORKER_NAME_INDEX = 1;
    private static final int MONTH_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int YEAR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        String[] splitedData;
        LocalDate workDayDate;
        int salary;
        for (String name : names) {
            salary = 0;
            for (String datum : data) {
                splitedData = datum.split(SPACE);
                workDayDate = LocalDate.parse(splitedData[WORK_DAY_INDEX], formatter);
                if (name.equals(splitedData[WORKER_NAME_INDEX])
                        && isWorkDayValid(fromDate, toDate, workDayDate)) {
                    salary += Integer.parseInt(splitedData[HOURS_INDEX])
                            * Integer.parseInt(splitedData[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private boolean isWorkDayValid(LocalDate fromDate, LocalDate toDate, LocalDate workDayDate) {
        return (toDate.isAfter(workDayDate) && fromDate.isBefore(workDayDate))
                || workDayDate.isEqual(fromDate)
                || workDayDate.isEqual(toDate);
    }
}
