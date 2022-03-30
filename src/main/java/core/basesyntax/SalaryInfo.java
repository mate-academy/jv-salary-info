package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX1 = 0;
    private static final int DATE_INDEX2 = 1;
    private static final int DATE_INDEX3 = 2;
    private static final int DATE_INDEX4 = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dat : data) {
                String[] line = dat.split(" ");
                LocalDate currentDate = LocalDate.parse(line[DATE_INDEX1], FORMATTER);
                if ((currentDate.isAfter(parsedDateFrom) || currentDate.isEqual(parsedDateFrom))
                        && (currentDate.isBefore(parsedDateTo) || currentDate.isEqual(parsedDateTo))
                        && name.equals(line[DATE_INDEX2])) {
                    salary += (Integer.parseInt(line[DATE_INDEX4])
                            * Integer.parseInt(line[DATE_INDEX3]));
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
