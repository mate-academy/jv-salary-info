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
        int[] salary = new int[names.length];
        int countOfSalary = 0;

        for (String name : names) {
            for (String dataEmployee : data) {
                if (dataEmployee.contains(name)) {
                    String[] employee = dataEmployee.split(" ");
                    LocalDate workDay = LocalDate.parse(employee[DATE], DATE_FORMAT);
                    if (startDate.compareTo(workDay) <= 0 && endDate.compareTo(workDay) >= 0) {
                        salary[countOfSalary] += Integer.parseInt(employee[WORKED_HOURS])
                                * Integer.parseInt(employee[INCOME_PER_HOUR]);
                    }
                }
            }
            countOfSalary++;
        }

        countOfSalary = 0;
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + "\n");

        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(salary[countOfSalary])
                    .append("\n");
            countOfSalary++;
        }

        return result.toString().trim();
    }
}
