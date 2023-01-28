package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final StringBuilder STRING_BUILDER = new StringBuilder();
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate localDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);
        int salary = 0;
        STRING_BUILDER.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            for (String line : data) {
                localDate = getLocalDate(line.substring(0, 10));
                if ((localDateFrom.equals(localDate) || localDate.equals(localDateTo)
                        || (localDate.isAfter(localDateTo) && localDate.isBefore(localDateFrom)))
                        && line.contains(name)) {
                    salary += Integer.parseInt(line.substring(line
                                .lastIndexOf(' ') + 1, line.length()));
                }
            }
            if (salary > 0) {
                STRING_BUILDER.append(System.lineSeparator()).append(name)
                        .append(" - ").append(salary);
            }
        }
        return STRING_BUILDER.toString();
    }

    public LocalDate getLocalDate(String date) {
        return localDate = LocalDate.parse(date, FORMATTER);
    }
}
