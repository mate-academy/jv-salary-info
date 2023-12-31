package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATA_DATE = 0;
    public static final int DATA_NAME = 1;
    public static final int DATA_HOURS = 2;
    public static final int DATA_MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder results = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < names.length; i++) {
            results.append(System.lineSeparator()).append(names[i]);
            int salary = 0;
            for (int y = 0; y < data.length; y++) {
                String[] nameData = data[y].split(" ");
                if (nameData[DATA_NAME].equals(names[i])) {
                    LocalDate date = LocalDate.parse(nameData[DATA_DATE], FORMATTER);
                    if (date.isAfter(localDateFrom)
                            && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                        int hours = Integer.parseInt(nameData[DATA_HOURS]);
                        int money = Integer.parseInt(nameData[DATA_MONEY]);
                        salary += money * hours;
                    }
                }
            }
            results.append(" - ").append(salary);
        }
        return results.toString();
    }
}

