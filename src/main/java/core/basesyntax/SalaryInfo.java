package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INCOME_PER_HOUR = 3;
    private static final int WORKED_HOURS = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder result = new StringBuilder();
        int[] totalSalary = new int[names.length];
        int countOfSalary = 0;
        result.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            for (String dataEmployee : data) {
                if (dataEmployee.contains(name)) {
                    String[] employee = dataEmployee.split(" ");
                    LocalDate workDay = LocalDate.parse(employee[DATE], DATE_FORMAT);
                    if (startDate.compareTo(workDay) <= 0 && endDate.compareTo(workDay) >= 0) {
                        totalSalary[countOfSalary] += Integer.parseInt(employee[WORKED_HOURS])
                                * Integer.parseInt(employee[INCOME_PER_HOUR]);
                    }
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(totalSalary[countOfSalary])
                    .append("\n");
            countOfSalary++;
        }

        return result.toString().trim();
    }
}
