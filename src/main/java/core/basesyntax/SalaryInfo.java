package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);
        int salary = 0;

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            for (String line : data) {
                String[] parsedLine = line.split(" ");

                if (!name.equals(parsedLine[1])) {
                    continue;
                }

                LocalDate dateInDataLine = parseDate(parsedLine[0]);
                boolean localDateFromCheck = dateInDataLine.isAfter(localDateFrom)
                        || dateInDataLine.equals(localDateFrom);
                boolean localDateToCheck = (dateInDataLine.isBefore(localDateTo)
                        || dateInDataLine.equals(localDateTo));
                if (localDateFromCheck && localDateToCheck) {
                    salary += Integer.parseInt(parsedLine[3]) * Integer.parseInt(parsedLine[2]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(String.format("%s - %d", name, salary));
            salary = 0;
        }

        return builder.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}
