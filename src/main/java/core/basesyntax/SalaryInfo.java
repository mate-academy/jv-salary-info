package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public String getSalaryInfo(
            String[] names, String[] data,
            String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate from = dateParsing(dateFrom);
        LocalDate to = dateParsing(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate dateTempFromData = dateParsing(splittedLine[0]);
                String nameTemp = splittedLine[1];
                final int hours = Integer.parseInt(splittedLine[2]);
                final int salaryPerHour = Integer.parseInt(splittedLine[3]);

                if ((dateTempFromData.isAfter(from)
                        && dateTempFromData.isBefore(to)
                        || dateTempFromData.equals(from)
                        || dateTempFromData.equals(to))
                        && nameTemp.equals(name)) {
                    {
                        salary += hours * salaryPerHour;
                    }
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }

    public LocalDate dateParsing(String date) throws DateTimeParseException {
        LocalDate resultDate;
        resultDate = LocalDate.parse(date, formatter);
        return resultDate;
    }
}
