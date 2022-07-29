package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        for (String datum : data) {
            String[] temp = datum.split(" ");
            if (temp[0].length() < 10) {
                temp[0] = "0" + temp[0];
            }
            LocalDate date = LocalDate.parse(temp[0], formatter);
            if (date.compareTo(startDate) >= 0 && (date.compareTo(endDate) <= 0)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(temp[1])) {
                        salaries[i] = salaries[i] + Integer.parseInt(temp[2])
                                * Integer.parseInt(temp[3]);
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        report.append(System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
            report.append(names[j]).append(" - ").append(salaries[j])
                    .append(System.lineSeparator());

        }
        return report.toString().trim();
    }
}
