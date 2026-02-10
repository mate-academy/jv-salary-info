package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String NAME_SALARY_SEPARATOR = " - ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedStartDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parsedEndDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append(REPORT_HEADER)
                .append(dateFrom)
                .append(NAME_SALARY_SEPARATOR)
                .append(dateTo);

        for (String name: names) {
            int salary = 0;
            for (String info: data) {
                String[] parts = info.split(" ");
                String currentDate = parts[DATE_INDEX];
                if (name.equals(parts[NAME_INDEX]) && isDateInRange(parsedStartDate, parsedEndDate, currentDate)) {
                    if (this.isDateInRange(parsedStartDate, parsedEndDate, currentDate)) {
                        System.out.println("salary" + salary);
                        System.out.println("nteger.parseInt(parts[HOURS_INDEX])" + Integer.parseInt(parts[HOURS_INDEX]));
                        System.out.println("Integer.parseInt(parts[RATE_INDEX])" + Integer.parseInt(parts[RATE_INDEX]));
                        salary += Integer.parseInt(parts[HOURS_INDEX])
                                * Integer.parseInt(parts[RATE_INDEX]);
                        System.out.println("salary" + salary);
                    }
                }
            }
            result.append(LINE_SEPARATOR)
                    .append(name)
                    .append(NAME_SALARY_SEPARATOR)
                    .append(salary);
            System.out.println("salary" + salary);
            System.out.println("//////////////////////////////////////");
        }
        return result.toString();
    }
    private boolean isDateInRange(LocalDate startDate, LocalDate endDate, String currentDate) {
        LocalDate parsedCurrentDate = LocalDate.parse(currentDate, DATE_FORMATTER);
        return !parsedCurrentDate.isBefore(startDate)
                && !parsedCurrentDate.isAfter(endDate);
    }
}
