package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String REPORT_FORMAT = "%s - %d";
    private static final String REPORT_BEGINNING = "Report for period ";
    private static final String REPORT_DASH = " - ";
    private static final String REGEX_SPACE = " ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);

        builder.append(REPORT_BEGINNING)
                .append(dateFrom)
                .append(REPORT_DASH)
                .append(dateTo);

        for (String name : names) {
            int salary = getSalarySumFromData(data, name, localDateFrom, localDateTo);
            builder.append(System.lineSeparator())
                    .append(String.format(REPORT_FORMAT, name, salary));
        }
        return builder.toString();
    }

    private int getSalarySumFromData(String[] data, String name,
                                     LocalDate localDateFrom,
                                     LocalDate localDateTo) {
        int salary = 0;
        for (String line : data) {
            String[] parsedLine = line.split(REGEX_SPACE);
            if (!name.equals(parsedLine[NAME_INDEX])) {
                continue;
            }

            if (checkIfDateInPeriod(parsedLine,
                    localDateFrom, localDateTo)) {
                salary += Integer.parseInt(parsedLine[SALARY_INDEX])
                        * Integer.parseInt(parsedLine[HOURS_INDEX]);
            }
        }
        return salary;
    }

    private boolean checkIfDateInPeriod(String[] data,
                                        LocalDate localDateFrom,
                                        LocalDate localDateTo) {
        LocalDate dateInDataLine = parseDate(data[DATE_INDEX]);
        boolean localDateFromCheck = dateInDataLine.isAfter(localDateFrom)
                || dateInDataLine.equals(localDateFrom);
        boolean localDateToCheck = (dateInDataLine.isBefore(localDateTo)
                || dateInDataLine.equals(localDateTo));
        return localDateFromCheck && localDateToCheck;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}
