package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private final StringBuilder builder = new StringBuilder();
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
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
                if ((dateInDataLine.isAfter(localDateFrom) || dateInDataLine.equals(localDateFrom))
                        && (dateInDataLine.isBefore(localDateTo) || dateInDataLine.equals(localDateTo))) {
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
        String[] splitDate = date.split("\\.");
        return LocalDate.parse(splitDate[2] + "-"
                + splitDate[1] + "-" + splitDate[0]);
    }
}
