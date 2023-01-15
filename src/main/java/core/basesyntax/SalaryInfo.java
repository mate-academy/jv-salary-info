
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final String WHITE_SPACE = " ";
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate currentDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate currentDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salaryCount = 0;
            for (String line : data) {
                String[] splitData = line.split(WHITE_SPACE);
                final LocalDate currentDate = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                if (splitData[NAME_INDEX].equals(name)
                        && ((currentDate.isAfter(currentDateFrom))
                        || (currentDate.isEqual(currentDateFrom)))
                        && (currentDate.isBefore(currentDateTo)
                        || currentDate.isEqual(currentDateTo))) {
                    salaryCount = salaryCount + Integer.parseInt(splitData[HOURS_INDEX])
                            * Integer.parseInt(splitData[SALARY_INDEX]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salaryCount);
        }
        return report.toString();
    }
}
