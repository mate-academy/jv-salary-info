package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_PERIOD = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_SALARY = 2;
    private static final int INDEX_OF_INCOME = 3;
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                if (name.equals(splittedLine[INDEX_OF_NAME])) {
                    LocalDate dateFromData = LocalDate.parse(splittedLine[INDEX_OF_PERIOD],
                            FORMATTER);
                    if ((dateFromData.isAfter(startDate) || dateFromData.isEqual(startDate))
                            && (dateFromData.isBefore(endDate) || dateFromData.isEqual(endDate))) {
                        salary += Integer.parseInt(splittedLine[INDEX_OF_SALARY])
                                * Integer.parseInt(splittedLine[INDEX_OF_INCOME]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        return reportBuilder.toString();
    }
}
