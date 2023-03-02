package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        report.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    String[] b = data[i].split(" ");
                    String[][] dt = new String[data.length][4];
                    // just to have possibility to call each element;
                    dt[i][0] = b[0];
                    dt[i][1] = b[1];
                    dt[i][2] = b[2];
                    dt[i][3] = b[3];
                    int hoursWorking = Integer.parseInt(dt[i][2]);
                    int moneyPerHour = Integer.parseInt(dt[i][3]);
                    LocalDate date = LocalDate.parse(dt[i][0], formatter);
                    if (!date.isBefore(from) && !date.isAfter(to)) {
                        salary += hoursWorking * moneyPerHour;
                    }
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
