package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDay = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDay = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataline : data) {
                String[] parsedData = dataline.split(" ");
                LocalDate currentDate = LocalDate.parse(parsedData[DATE_INDEX],
                        FORMATTER);
                if (name.equals(parsedData[NAME_INDEX])
                        && (currentDate.isAfter(fromDay) || currentDate.isEqual(fromDay))
                        && (currentDate.isBefore(toDay) || currentDate.isEqual(toDay))) {
                    int hours = Integer.parseInt(String.valueOf(parsedData[HOURS_INDEX]));
                    int salaryForHours = Integer.parseInt(String.valueOf(parsedData[SALARY_INDEX]));
                    salary = salary + hours * salaryForHours;
                }
            }
            result.append(System.lineSeparator()).append(name + " - " + salary);
        }
        return result.toString();
    }
}
