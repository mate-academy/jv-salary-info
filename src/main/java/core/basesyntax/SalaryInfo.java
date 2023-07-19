package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder res = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int amountOfEmployees = names.length;
        int[] salaries = new int[amountOfEmployees];
        for (int i = 0; i < amountOfEmployees; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataArray = data[j].split(" ");
                LocalDate dateToCompare = LocalDate.parse(dataArray[0], FORMATTER);
                if (data[j].contains(names[i]) && !dateToCompare.isBefore(dateStart)
                        && !dateToCompare.isAfter(dateFinish)) {
                    salaries[i] += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            res.append(LINE_SEPARATOR).append(names[i]).append(" - ").append(salaries[i]);
        }
        return res.toString();
    }
}
