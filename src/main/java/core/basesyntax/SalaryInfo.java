package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_REGEX = "\\s";
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_WAGE = 2;
    private static final int INDEX_OF_HOURS_WORKED = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            reportBuilder.append(System.lineSeparator());
            reportBuilder.append(calculateSalaryForEmployee(data, name, startDate, endDate));
        }
        return reportBuilder.toString();
    }

    private String calculateSalaryForEmployee(String[] data, String name,
                                              LocalDate startDate, LocalDate endDate) {
        int salary = 0;
        for (String dataLine : data) {
            if (dataLine.contains(name)) {
                salary += getSalary(dataLine.split(SPACE_REGEX), startDate, endDate);
            }
        }
        return name + " - " + salary;
    }

    private int getSalary(String[] line, LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.parse(line[INDEX_OF_DATE], formatter);
        return ((currentDate.isEqual(startDate) || currentDate.isAfter(startDate))
                && (currentDate.isEqual(endDate) || currentDate.isBefore(endDate)))
                ? getSalaryForCurrentDate(line) : 0;
    }

    private int getSalaryForCurrentDate(String[] line) {
        return Integer.parseInt(line[INDEX_OF_WAGE])
                * Integer.parseInt(line[INDEX_OF_HOURS_WORKED]);
    }
}
