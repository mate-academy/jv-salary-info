package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeParseException, DateTimeException {
        final LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateFinish = LocalDate.parse(dateTo, formatter);
        LocalDate localDate;
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        if (data == null) {
            return "";
        }

        for (int j = 0; j < names.length; j++) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] record = data[i].split(" ");
                localDate = LocalDate.parse(record[DATE], formatter);
                if (names[j].equals(record[NAME])
                        && (localDate.isEqual(dateStart) || localDate.isAfter(dateStart))
                        && (localDate.isEqual(dateFinish)
                        || localDate.isBefore(dateFinish))) {
                    salary += Integer.parseInt(record[HOURS]) * Integer.parseInt(record[PAY]);
                }
            }
            report.append(System.lineSeparator())
                    .append(names[j]).append(" - ").append(salary);
        }
        return report.toString();
    }
}
