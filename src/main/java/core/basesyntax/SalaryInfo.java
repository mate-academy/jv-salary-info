package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int FIRST_SPACE_INDEX = 10;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(calculateSalaryForEmployee(data, name, startDate, endDate));
        }
        return stringBuilder.toString();
    }

    private String calculateSalaryForEmployee(String[] data, String name,
                                              LocalDate startDate, LocalDate endDate) {
        int salary = 0;
        for (String dataLine : data) {
            if (dataLine.contains(name)) {
                salary += calculateIfApplicable(dataLine, startDate, endDate);
            }
        }
        return name + " - " + salary;
    }

    private int calculateIfApplicable(String dataLine, LocalDate startDate, LocalDate endDate) {
        String currentDateString = dataLine.substring(0, FIRST_SPACE_INDEX);
        LocalDate currentDate = LocalDate.parse(currentDateString, formatter);
        return (currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0)
                ? salaryForCurrentDate(dataLine) : 0;
    }

    private int salaryForCurrentDate(String dataLine) {
        String[] digitsStrings = dataLine.split("[A-Z,a-z]+\\s");
        String[] hoursAndWage = digitsStrings[digitsStrings.length - 1].split("\\s");
        return Integer.parseInt(hoursAndWage[0]) * Integer.parseInt(hoursAndWage[1]);
    }
}
