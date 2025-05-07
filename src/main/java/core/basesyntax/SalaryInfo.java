package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
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
                    String[][] matrixDate = new String[data.length][4];
                    // just to have possibility to call each element;
                    matrixDate[i][FIRST_COLUMN] = splitData[FIRST_COLUMN];
                    matrixDate[i][SECOND_COLUMN] = splitData[SECOND_COLUMN];
                    matrixDate[i][THIRD_COLUMN] = splitData[THIRD_COLUMN];
                    matrixDate[i][FOURTH_COLUMN] = splitData[FOURTH_COLUMN];
                    int hoursWorking = Integer.parseInt(matrixDate[i][FOURTH_COLUMN]);
                    int moneyPerHour = Integer.parseInt(matrixDate[i][THIRD_COLUMN]);
                    LocalDate date = LocalDate.parse(matrixDate[i][FIRST_COLUMN], formatter);
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
