package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static String[] arrayOfDataElements;
    private static final LocalDate CURRENT_DATE
            = LocalDate.parse(arrayOfDataElements[0], FORMATTER);
    private static final String EMPLOYER_NAME = arrayOfDataElements[1];
    private static final int WORKING_HOURS = Integer.parseInt(arrayOfDataElements[2]);
    private static final int INCOME_PER_HOUR = Integer.parseInt(arrayOfDataElements[3]);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataElement : data) {
                arrayOfDataElements = dataElement.split(" ");
                if (localDateFrom.compareTo(CURRENT_DATE) <= 0
                        && localDateTo.compareTo(CURRENT_DATE) >= 0
                        && EMPLOYER_NAME.equals(name)) {
                    salary += WORKING_HOURS * INCOME_PER_HOUR;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
