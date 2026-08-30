package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate localDate;
        String[] dataArray;
        String totalReport = REPORT + dateFrom + " - " + dateTo + System.lineSeparator();
        StringBuilder report = new StringBuilder();
        report.append(totalReport);
        int[] salary = new int[names.length];
        for (int i = 0; data.length > i; i++) {
            dataArray = data[i].split(" ");
            localDate = LocalDate.parse(dataArray[0], formatter);
            if (!localDate.isBefore(from) && !localDate.isAfter(to)) {
                for (int k = 0; k < names.length; k++) {
                    if (names[k].equals(dataArray[1])) {
                        salary[k] += Integer.parseInt(dataArray[2])
                                * Integer.parseInt(dataArray[3]);
                        break;
                    }
                }
            }
        }
        for (int k = 0; k < salary.length; k++) {
            report.append(names[k]).append(" - ").append(salary[k]);
            if (k < salary.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
