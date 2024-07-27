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
        StringBuilder totalSalarySummary =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String datum : data) {
            int whiteSpaceIndex = datum.indexOf(" ");
            String date = datum.substring(FIRST_STRING_INDEX, whiteSpaceIndex);
            LocalDate parsedDate = LocalDate.parse(date, FORMATTER);

            if ((parsedDate.isAfter(parsedDateFrom) || parsedDate.isEqual(parsedDateFrom))
                    && (parsedDate.isBefore(parsedDateTo) || parsedDate.isEqual(parsedDateTo))) {
                String[] nameAndSalaryArray = datum.substring(++whiteSpaceIndex).split(" ");

                totalSalaryArray[getTotalSalaryArrayIndex(nameAndSalaryArray[NAME_INDEX], names)] +=
                        getSalary(nameAndSalaryArray[HOURS_WORKED_INDEX],
                                nameAndSalaryArray[INCOME_PER_HOUR_INDEX]);

            }
        }

        for (int i = 0; i < names.length; i++) {
            totalSalarySummary.append(System.lineSeparator())
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


