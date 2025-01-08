package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int date = 0,
            name = 1,
            hours = 2,
            salary = 3;

    public static String getSalaryInfo(String[] names, 
                                       String[] data, 
                                       String dateFrom, 
                                       String dateTo) {

        final LocalDate dataFrom = LocalDate
                .parse(dateFrom, formatter);
        final LocalDate dataTo = LocalDate
                .parse(dateTo, formatter);
        
        int[] salariesByName = new int[names.length];

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);

        for (String datum : data) {
            String[] splitData = datum.split(" ");
            LocalDate dataNow = LocalDate.parse(splitData[date], formatter);
            if ((dataFrom.isBefore(dataNow)
                    || dataFrom.isEqual(dataNow))
                    && (dataTo.isAfter(dataNow)
                    || dataTo.isEqual(dataNow))) {
                calculateSalariesByName(names, splitData, salariesByName);
            }
        }

        for (int k = 0; k < names.length; k++) {
            builder.append(System.lineSeparator())
                    .append(names[k])
                    .append(" - ")
                    .append(salariesByName[k]);
        }

        return builder.toString();
    }

    private static void calculateSalariesByName(String[] names,
                                       String[] splitData,
                                       int[] salariesByName) {
        for (int j = 0; j < names.length; j++) {
            if (splitData[name].equals(names[j])) {
                int countOfHours = Integer.parseInt(splitData[hours]);
                int hourSalary = Integer.parseInt(splitData[salary]);
                salariesByName[j] += countOfHours * hourSalary;
            }
        }
    }
}
