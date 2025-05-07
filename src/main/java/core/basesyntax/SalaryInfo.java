package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final String SPACE = " ";
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + DASH + dateTo);

        for (String name : names) {
            salaryInfo.append(System.lineSeparator()).append(name).append(DASH)
                    .append(calculateSallaryInfo(name, data, fromDate, toDate));
        }

        return salaryInfo.toString();
    }

    public int calculateSallaryInfo(String name, String[] data, LocalDate fromDate,
                                    LocalDate toDate) {
        int salary = 0;
        for (String info : data) {
            String[] infoSplit = info.split(SPACE);
            LocalDate recordDate = LocalDate.parse(infoSplit[DAY_INDEX], DATE_FORMATTER);
            String employee = infoSplit[NAME_INDEX];
            int hours = Integer.parseInt(infoSplit[HOUR_INDEX]);
            int salaryHour = Integer.parseInt(infoSplit[SALARY_PER_HOUR_INDEX]);

            if (employee.equals(name) && !recordDate.isBefore(fromDate)
                    && !recordDate.isAfter(toDate)) {
                salary += hours * salaryHour;
            }
        }
        return salary;
    }
}
