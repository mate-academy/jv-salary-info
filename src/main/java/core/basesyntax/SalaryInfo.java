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
            for (String info : data) {
                if (info.contains(name)) {
                    String[] splittedDataArray = info.split(" ");
                    LocalDate currentDate = LocalDate.parse(splittedDataArray[DATE_INDEX],
                            DATE_TIME_FORMATTER);
                    if ((currentDate.isAfter(localDateFrom)
                            || currentDate.isEqual(localDateFrom))
                            && (currentDate.isBefore(localDateTo)
                            || currentDate.isEqual(localDateTo))) {
                        totalSalary += Integer.parseInt(splittedDataArray[HOURS_INDEX])
                                * Integer.parseInt(splittedDataArray[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            result.append("\n")
                    .append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}
