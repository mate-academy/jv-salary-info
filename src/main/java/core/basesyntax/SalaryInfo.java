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
    private static final String SEPARATOR = " ";
    private static final String HYPHEN = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder(BEGIN_OF_STRING);
        salaryInfo.append(dateFrom).append(HYPHEN).append(dateTo);
        for (String name : names) {
            int moneyEarned = calculateSalary(name, data, dateFrom, dateTo);
            salaryInfo.append(System.lineSeparator())
                    .append(name).append(HYPHEN).append(moneyEarned);
        }
        return salaryInfo.toString();
    }

    private boolean compareName(String[] dataCurrentNameArray, String name) {
        return dataCurrentNameArray[INDEX_NAME].equals(name);
    }

    private boolean compareDateFrom(String[] dataCurrentNameArray, String dateFrom) {
        return LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .isEqual(LocalDate.parse(dateFrom, FORMATTER))
                || LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .isAfter(LocalDate.parse(dateFrom, FORMATTER));
    }

    private boolean compareDateTo(String[] dataCurrentNameArray, String dateTo) {
        return LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .isEqual(LocalDate.parse(dateTo, FORMATTER))
                || LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                .isBefore(LocalDate.parse(dateTo, FORMATTER));
    }

    private int calculateSalary(String name, String[] data, String dateFrom, String dateTo) {
        String[] dataCurrentNameArray;
        int moneyEarned = 0;
        for (String dataCurrentName : data) {
            dataCurrentNameArray = dataCurrentName.split(SEPARATOR);
            if (compareName(dataCurrentNameArray, name)
                    && compareDateFrom(dataCurrentNameArray, dateFrom)
                    && compareDateTo(dataCurrentNameArray, dateTo)) {
                moneyEarned += Integer.parseInt(dataCurrentNameArray[INDEX_HOURS])
                        * Integer.parseInt(dataCurrentNameArray[INDEX_MONEY_PER_HOUR]);
            }
        }
        return moneyEarned;
    }
}
