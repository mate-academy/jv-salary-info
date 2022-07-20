package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final byte DATE_INDEX = 0;
    private static final byte NAME_INDEX = 1;
    private static final byte WORKING_DAYS_INDEX = 2;
    private static final byte HOURLY_RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[DATE_INDEX], DATE_FORMATTER);
                if ((currentDate.isAfter(localDateFrom) && currentDate.isBefore(localDateTo)
                        || currentDate.equals(localDateFrom) || currentDate.equals(localDateTo))
                        && name.equals(splittedLine[NAME_INDEX])) {
                    salary += Integer.parseInt(splittedLine[WORKING_DAYS_INDEX])
                            * Integer.parseInt(splittedLine[HOURLY_RATE_INDEX]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
