package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateFinish = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] infoAboutActivity = data[j].split(" ");
                LocalDate dateToCompare = LocalDate.parse(dataArray[0], formatter);
                if (data[j].contains(names[i]) && !dateToCompare.isBefore(dateStart)
                        && !dateToCompare.isAfter(dateFinish)) {
                    salaries[i] += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            res.append(names[i]).append(" - ").append(salaries[i]).append(System.lineSeparator());
        }
        return res.substring(0, res.length() - System.lineSeparator().length());
    }
}
