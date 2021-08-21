package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String separator = System.lineSeparator();
        LocalDate localDateFrom;
        LocalDate localDateTo;
        LocalDate period;
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        try {
            localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println(dateFrom + " is not parsable!");
            throw e;
        }
        try {
            localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            System.out.println(dateTo + " is not parsable!");
            throw e;
        }

        for (String name : names) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] arrayFromData = data[j].split(" ");
                period = LocalDate.parse(arrayFromData[0], TIME_FORMATTER);
                if ((name.equals(arrayFromData[1]))
                        && ((period.isAfter(localDateFrom) && period.isBefore(localDateTo))
                        || period.isEqual(localDateFrom)
                        || period.isEqual(localDateTo))) {
                    salary += Integer.valueOf(arrayFromData[2]) * Integer.valueOf(arrayFromData[3]);
                }
            }
            stringBuilder.append(separator).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
