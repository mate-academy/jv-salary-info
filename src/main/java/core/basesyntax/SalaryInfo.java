package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataSplit = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(dataSplit[DATE_INDEX], FORMATTER);
                if (names[i].equals(dataSplit[NAME_INDEX])) {
                    if (!currentDate.isBefore(parsedDateFrom)
                            && !currentDate.isAfter(parsedDateTo)) {
                        salary += Integer.parseInt(dataSplit[HOURS_INDEX])
                                * Integer.parseInt(dataSplit[INCOME_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
