package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String d : data) {
                if (d.contains(name)) {
                    String[] dataArray = d.split(" ");
                    LocalDate currDate = LocalDate.parse(dataArray[DATE_INDEX],
                            DATE_TIME_FORMATTER);
                    if ((currDate.isAfter(localDateFrom) || currDate.isEqual(localDateFrom))
                            && (currDate.isBefore(localDateTo) || currDate.isEqual(localDateTo))) {
                        totalSalary += Integer.parseInt(dataArray[HOURS_INDEX])
                                * Integer.parseInt(dataArray[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            result.append("\n")
                    .append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}
