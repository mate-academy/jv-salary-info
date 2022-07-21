package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate workday = LocalDate.parse(splittedLine[INDEX_OF_DATE], FORMATTER);
                if ((localDateFrom.isBefore(workday)
                        || localDateFrom.isEqual(workday))
                        && (localDateTo.isAfter(workday)
                        || localDateTo.isEqual(workday))) {
                    if (name.equals(splittedLine[INDEX_OF_NAME])) {
                        salary = salary + parseInt(splittedLine[INDEX_OF_INCOME_PER_HOUR])
                                * parseInt(splittedLine[INDEX_OF_HOUR]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
