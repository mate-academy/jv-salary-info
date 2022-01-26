package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int RATE_PER_HOURS_INDEX = 3;
    private static final int EMPLOYEE_NAME_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ");
        salaryInfo.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String[] lineElements = line.split("\\s");
                    if (dateCompare(dateFrom, dateTo, lineElements[EMPLOYEE_NAME_INDEX])) {
                        int workingHours = Integer.parseInt(lineElements[WORKING_HOURS_INDEX]);
                        int ratePerHour = Integer.parseInt(lineElements[RATE_PER_HOURS_INDEX]);
                        totalSalary += workingHours * ratePerHour;
                    }
                }
            }
            salaryInfo.append(System.lineSeparator());
            salaryInfo.append(name).append(" - ").append(totalSalary);
        }
        return salaryInfo.toString();
    }

    public boolean dateCompare(String dateFrom, String dateTo, String actualDate) {
        LocalDate actualLocalDate = LocalDate.parse(actualDate, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        return (actualLocalDate.isEqual(localDateTo)
                || actualLocalDate.isEqual(localDateFrom))
                || (actualLocalDate.isBefore(localDateTo)
                && actualLocalDate.isAfter(localDateFrom));
    }
}
