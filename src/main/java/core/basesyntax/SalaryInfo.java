package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] salary = new int[names.length];
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataSplit = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(dataSplit[DATE], FORMATTER);
                if (names[i].equals(dataSplit[NAME])) {
                    if (!currentDate.isBefore(parsedDateFrom)
                            && !currentDate.isAfter(parsedDateTo)) {
                        salary[i] += Integer.parseInt(dataSplit[HOURS])
                                * Integer.parseInt(dataSplit[INCOME]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }
}
