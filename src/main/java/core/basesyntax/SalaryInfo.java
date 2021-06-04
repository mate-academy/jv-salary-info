package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HEADER = "Report for period %s - %s";
    private static final int DATE_INDEX = 0;
    private static final int COUNT_OF_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder buildInfo = new StringBuilder(String.format(HEADER, dateFrom, dateTo));
        for (String name : names) {
            int sumOfSalary = 0;
            buildInfo.append("\n").append(name).append(" - ");
            for (String info : data) {
                if (info.contains(name)) {
                    String[] dateInfo = info.split(" ");
                    LocalDate currentDate = LocalDate.parse(dateInfo[DATE_INDEX], FORMATTER);
                    if ((fromDate.isBefore(currentDate) || fromDate.isEqual(currentDate))
                            && (toDate.isAfter(currentDate) || toDate.isEqual(currentDate))) {
                        sumOfSalary += Integer.parseInt(dateInfo[COUNT_OF_HOURS_INDEX])
                                * Integer.parseInt(dateInfo[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            buildInfo.append(sumOfSalary);
        }
        return buildInfo.toString();
    }
}
