package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String REPORT_FORMAT = "%s - %d";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
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
            String[] parsedLine = line.split(" ");

            if (!name.equals(parsedLine[NAME_INDEX])) {
                continue;
            }

            LocalDate dateInDataLine = parseDate(parsedLine[DATE_INDEX]);
            boolean localDateFromCheck = dateInDataLine.isAfter(localDateFrom)
                    || dateInDataLine.equals(localDateFrom);
            boolean localDateToCheck = (dateInDataLine.isBefore(localDateTo)
                    || dateInDataLine.equals(localDateTo));
            if (localDateFromCheck && localDateToCheck) {
                salary += Integer.parseInt(parsedLine[SALARY_INDEX])
                        * Integer.parseInt(parsedLine[HOURS_INDEX]);
            }
        }
        return salary;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}
