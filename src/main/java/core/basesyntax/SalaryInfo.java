package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ");
            int salary = 0;
            for (String dataElement : data) {
                String[] arrayOfDataElements = dataElement.split(" ");
                LocalDate parseParticularDate = LocalDate.parse(arrayOfDataElements[0],
                        DATE_TIME_FORMATTER);
                if (parseParticularDate.isAfter(parseDateFrom)
                        && parseParticularDate.isBefore(parseDateTo)
                        || parseParticularDate.isEqual(parseDateFrom)
                        || parseParticularDate.isEqual(parseDateTo)) {
                    int incomePerDay = Integer.parseInt(arrayOfDataElements[2])
                            * Integer.parseInt(arrayOfDataElements[3]);
                    if (names[i].equals(arrayOfDataElements[1])) {
                        salary += incomePerDay;
                    }
                }
            }
            builder.append(salary);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
