package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryByNames = new int[names.length];
        final LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        final LocalDate lastDate = LocalDate.parse(dateTo, formatter);
        final StringBuilder stringBuilder = new StringBuilder();

        for (String line : data) {
            final String[] words = line.split(" ");
            final LocalDate salaryDate = LocalDate.parse(words[0], formatter);

            //check if salaryDate is in the specified range
            boolean isFirstDateOk = salaryDate.isEqual(firstDate) || salaryDate.isAfter(firstDate);
            boolean isLastDateOk = salaryDate.isEqual(lastDate) || salaryDate.isBefore(lastDate);
            if (isFirstDateOk && isLastDateOk) {
                for (int i = 0; i < names.length; i++) {
                    if (words[1].equals(names[i])) {
                        salaryByNames[i] += Integer.parseInt(words[2]) * Integer.parseInt(words[3]);
                    }
                }
            }
        }
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(salaryByNames[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
