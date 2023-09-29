package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        try {
            LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
            LocalDate dateFinish = LocalDate.parse(dateTo, formatter);
            int salary = 0;
            builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

            for (String name : names) {
                for (String str : data) {
                    String[] values = str.split(" ");
                    LocalDate dateWork = LocalDate.parse(values[0], formatter);
                    if (dateWork.isAfter(dateStart.minusDays(1))
                            && dateWork.isBefore(dateFinish.plusDays(1))
                            && name.equals(values[1])) {
                        salary += Integer.parseInt(values[2]) * Integer.parseInt(values[3]);
                    }
                }
                builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
                salary = 0;
            }
        } catch (DateTimeParseException exc) {
            System.out.printf("%s or %s is not parsable!%n", dateFrom, dateTo);
            throw exc;
        }
        return builder.toString();
    }
}
