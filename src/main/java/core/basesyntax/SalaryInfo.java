package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int salary = 0;
            for (String lineOfData: data) {
                String[] lineData = lineOfData.split(" ");
                if (!lineOfData.contains(name)) {
                    continue;
                }
                LocalDate date = LocalDate.parse(lineData[DATE_INDEX], FORMATTER);
                if (date.isAfter(from) && date.isBefore(to)
                        || date.isEqual(from)
                        || date.isEqual(to)) {
                    salary += (Integer.parseInt(lineData[HOUR_INDEX])
                                * Integer.parseInt(lineData[SALARY_PER_HOUR_INDEX]));
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return reportBuilder.toString();
    }
}
