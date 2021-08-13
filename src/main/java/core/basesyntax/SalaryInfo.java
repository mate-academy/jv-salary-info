package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int numberOfColumns = 4;

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
        try {
            localDateFrom = LocalDate.parse(dateFrom, formatter);
            localDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new RuntimeException("Invalid dateFrom or dateTo");
        }

        if (localDateFrom.compareTo(localDateTo) > 0) {
            throw new RuntimeException("Invalid date range");
        }

        StringBuilder result = new StringBuilder();

        result.append("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());
        for (String itemData : data) {
            itemDataArray = itemData.split(" ");

            if (itemDataArray.length != numberOfColumns) {
                throw new RuntimeException("Invalid data row");
            }
            try {
                itemDate = LocalDate.parse(itemDataArray[0], formatter);
                numberOfDays = Integer.parseInt(itemDataArray[2]);
                itemSalary = Integer.parseInt(itemDataArray[3]);
            } catch (DateTimeParseException dateTimeParseException) {
                throw new RuntimeException("Invalid date in the row in the data");
            } catch (NumberFormatException numberFormatException) {
                throw new RuntimeException("Invalid format number of days or salary");
            }
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
            result.append(names[i])
                    .append(" - ")
                    .append(totalSalaries[i]);
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
