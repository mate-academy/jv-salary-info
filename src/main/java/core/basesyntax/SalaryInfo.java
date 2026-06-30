package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = 0;
            for (String datum : data) {
                String[] parts = datum.split("\\s+");
                LocalDate currentDate = LocalDate.parse(parts[DATE_INDEX], DATE_TIME_FORMATTER);
                if (name.equals(parts[NAME_INDEX])) {
                    if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                        salary += Integer.parseInt(parts[HOURS_INDEX])
                                * Integer.parseInt(parts[RATE_INDEX]);
                    }
                }
            }
            salaryInfo.append(name).append(" - ").append(salary);

            if (i < names.length - 1) {
                salaryInfo.append(System.lineSeparator());
            }
        }
        return salaryInfo.toString();
    }
}
