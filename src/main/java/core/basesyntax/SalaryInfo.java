package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int LINE_FIRST = 0;
    private static final int LINE_SECOND = 1;
    private static final int LINE_THIRD = 2;
    private static final int LINE_FOURTH = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[LINE_FIRST], FORMATTER);
                if ((currentDate.isEqual(localDateFrom) || currentDate.isAfter(localDateFrom))
                        && (currentDate.isBefore(localDateTo) || currentDate.isEqual(localDateTo))
                        && splittedLine[LINE_SECOND].equals(name)) {
                    salary += (Integer.parseInt(splittedLine[LINE_FOURTH])
                            * Integer.parseInt(splittedLine[LINE_THIRD]));
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
