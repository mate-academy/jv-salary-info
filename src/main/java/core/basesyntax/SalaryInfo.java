package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int COLUMNS_NUMBER = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom;
        LocalDate localDateTo;
        String[] itemDataArray;
        LocalDate itemDate;
        String itemName;
        int numberOfDays;
        int[] totalSalaries = new int[names.length];
        int itemSalary;
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new RuntimeException("Null argument");
        }

        localDateFrom = LocalDate.parse(dateFrom, formatter);
        localDateTo = LocalDate.parse(dateTo, formatter);

        if (localDateFrom.compareTo(localDateTo) > 0) {
            throw new RuntimeException("Invalid date range");
        }

        StringBuilder result = new StringBuilder();

        result.append("Report for period "
                + dateFrom
                + " - "
                + dateTo);
        for (String itemData : data) {
            itemDataArray = itemData.split(" ");

            if (itemDataArray.length != COLUMNS_NUMBER) {
                throw new RuntimeException("Invalid data row");
            }

            itemDate = LocalDate.parse(itemDataArray[0], formatter);
            numberOfDays = Integer.parseInt(itemDataArray[2]);
            itemSalary = Integer.parseInt(itemDataArray[3]);

            if (numberOfDays < 0) {
                throw new RuntimeException("Invalid value of number of days");
            } else if (itemSalary < 0) {
                throw new RuntimeException("Invalid value of salary");
            }
            itemName = itemDataArray[1];
            if (itemDate.compareTo(localDateFrom) >= 0 && itemDate.compareTo(localDateTo) <= 0) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(itemName)) {
                        totalSalaries[i] += numberOfDays * itemSalary;
                    }
                }
            }

        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            result.append(names[i])
                    .append(" - ")
                    .append(totalSalaries[i]);
        }
        return result.toString();
    }
}
