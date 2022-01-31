package core.basesyntax;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        int[] salaryArr = new int [names.length];

        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(" ");
            LocalDate dataDate = LocalDate.parse(splitData[0], FORMATTER);
            int parsedHours = Integer.parseInt(splitData[2]);
            int parsedMoney = Integer.parseInt(splitData[3]);
            if (dataDate.isAfter(parsedDateFrom) && dataDate.isBefore(parsedDateTo)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(splitData[1])) {
                        salaryArr[j] = salaryArr[j] + parsedHours * parsedMoney;
                    }
                }
            }
        }

        StringBuilder header = new StringBuilder("Report for period ");
        header.append(parsedDateFrom.format(FORMATTER))
                .append("  - ")
                .append(parsedDateTo.format(FORMATTER));
        for (int i = 0; i < names.length; i++) {
            header.append(System.lineSeparator())
                  .append(names[i])
                    .append(" - ")
                    .append(salaryArr[i]);
        }

        return header.toString();





































    }
}
