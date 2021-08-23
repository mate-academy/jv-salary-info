package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] lineElements = line.split(" ");
                LocalDate actualDate = LocalDate.parse(lineElements[DATE_POSITION], DATE_FORMAT);
                if (lineElements[NAME_POSITION].equals(name)
                        && (actualDate.isAfter(localDateFrom)
                        || actualDate.equals(localDateFrom))
                        && (actualDate.isBefore(localDateTo)
                        || actualDate.equals(localDateTo))) {
                    salary += Integer.parseInt(lineElements[HOURS_POSITION])
                            * Integer.parseInt(lineElements[SALARY_POSITION]);
                }
            }
            report.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
