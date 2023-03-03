package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder("Report for period ");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        report.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    String[] splitData = data[i].split(" ");
                    String[][] dat = new String[data.length][4];
                    // just to have possibility to call each element;
                    dat[i][ZERO] = splitData[ZERO];
                    dat[i][ONE] = splitData[ONE];
                    dat[i][TWO] = splitData[TWO];
                    dat[i][THREE] = splitData[THREE];
                    int hoursWorking = Integer.parseInt(dat[i][TWO]);
                    int moneyPerHour = Integer.parseInt(dat[i][THREE]);
                    LocalDate date = LocalDate.parse(dat[i][ZERO], formatter);
                    if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
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
