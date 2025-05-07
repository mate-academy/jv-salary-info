package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int FIRST_STRING_INDEX = 0;
    private static final int NAME_INDEX = 0;
    private static final int HOURS_WORKED_INDEX = 1;
    private static final int INCOME_PER_HOUR_INDEX = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalaryArray = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String datum : data) {
            int separator = datum.indexOf(" ");
            LocalDate date =
                    LocalDate.parse(datum.substring(FIRST_STRING_INDEX, separator), FORMATTER);

            if ((date.isAfter(fromDate) || date.isEqual(fromDate))
                    && (date.isBefore(toDate) || date.isEqual(toDate))) {
                String[] nameAndSalaryArray = datum.substring(++separator).split(" ");

                totalSalaryArray[getTotalSalaryArrayIndex(nameAndSalaryArray[NAME_INDEX], names)] +=
                        getSalary(nameAndSalaryArray[HOURS_WORKED_INDEX],
                                nameAndSalaryArray[INCOME_PER_HOUR_INDEX]);
            }
        }

        return getInformativeData(names, totalSalaryArray, dateFrom, dateTo);
    }

    private String getInformativeData(String[] names, int[] totalSalaryArray,
                                      String dateFrom, String dateTo) {
        StringBuilder totalSalarySummary =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        
        for (int i = 0; i < names.length; i++) {
            totalSalarySummary
                    .append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(totalSalaryArray[i]);
        }
        return totalSalarySummary.toString();
    }

    private int getTotalSalaryArrayIndex(String name, String[] names) throws InvalidDataException {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        throw new InvalidDataException("Сould not find " + name + " in the array names");
    }

    private int getSalary(String hours, String incomePerHour) {
        return Integer.parseInt(hours) * Integer.parseInt(incomePerHour);
    }
}


