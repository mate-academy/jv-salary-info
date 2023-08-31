package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATA_LENGTH = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate tempDate;
        String[] tempData;

        for (String datum : data) {
            tempData = datum.split(" ", DATA_LENGTH);
            tempDate = LocalDate.parse(tempData[0], formatter);

            if ((tempDate.isAfter(from) || tempDate.isEqual(from))
                    && (tempDate.isBefore(to) || tempDate.isEqual(to))) {
                for (int i = 0; i < names.length; i++) {
                    if (tempData[1].equals(names[i])) {
                        salaries[i] += Integer.parseInt(tempData[3])
                                * Integer.parseInt(tempData[2]);
                    }
                }
            }
        }

        StringBuilder resultString = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length - 1; i++) {
            resultString.append(names[i]).append(" - ")
                    .append(salaries[i]).append(System.lineSeparator());
        }
        resultString.append(names[names.length - 1])
                .append(" - ").append(salaries[names.length - 1]);
        return resultString.toString();
    }
}
