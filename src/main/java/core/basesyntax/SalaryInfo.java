package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_MONEY_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String BEGIN_OF_STRING = "Report for period ";

    private boolean compareName(String[] dataCurrentNameArray, String name) {
        return dataCurrentNameArray[INDEX_NAME].equals(name);
    }

    private boolean compareDateFrom(String[] dataCurrentNameArray, String dateFrom) {
        return LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .compareTo(LocalDate.parse(dateFrom, FORMATTER)) >= 0;
    }

    private boolean compareDateTo(String[] dataCurrentNameArray, String dateTo) {
        return LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .compareTo(LocalDate.parse(dateTo, FORMATTER)) <= 0;
    }

    private int calculateSalary(String name, String[] data, String dateFrom, String dateTo) {
        String[] dataCurrentNameArray;
        int moneyEarned = 0;
        for (String dataCurrentName : data) {
            dataCurrentNameArray = dataCurrentName.split(" ");
            if (compareName(dataCurrentNameArray, name)
                    && compareDateFrom(dataCurrentNameArray, dateFrom)
                    && compareDateTo(dataCurrentNameArray, dateTo)) {
                moneyEarned += Integer.parseInt(dataCurrentNameArray[INDEX_HOURS])
                        * Integer.parseInt(dataCurrentNameArray[INDEX_MONEY_PER_HOUR]);
            }
        }
        return moneyEarned;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(BEGIN_OF_STRING);
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int moneyEarned = calculateSalary(name, data, dateFrom,dateTo);
            builder.append(name).append(" - ").append(moneyEarned).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
