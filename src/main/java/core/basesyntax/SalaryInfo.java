package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[DATE_INDEX], FORMATTER);
                if ((currentDate.isAfter(parsedDateFrom) || currentDate.isEqual(parsedDateFrom))
                        && (currentDate.isBefore(parsedDateTo) || currentDate.isEqual(parsedDateTo))
                        && name.equals(splittedLine[NAME_INDEX])) {
                    salary += (Integer.parseInt(splittedLine[SALARY_INDEX])
                            * Integer.parseInt(splittedLine[HOUR_INDEX]));
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
