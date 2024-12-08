package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATES = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATES);
        } catch (Exception e) {
            throw new InvalidDataException(
                    "Invalid date format. Only dd.MM.yyyy format is accepted"
            );
        }
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new InvalidDataException("Invalid data: Please check your input");
        }

        LocalDate dateFromFormatted = parseDate(dateFrom);
        LocalDate dateToFormatted = parseDate(dateTo);

        StringBuilder output = new StringBuilder("Report for period "
                + DATES.format(dateFromFormatted)
                + " - "
                + DATES.format(dateToFormatted));

        for (String name : names) {
            int salary = 0;
            for (String part : data) {
                String[] dataPart = part.split(" ");
                LocalDate datePart = LocalDate.parse(dataPart[0], DATES);

                if (dataPart[1].equals(name)
                        && !datePart.isBefore(dateFromFormatted)
                        && !datePart.isAfter((dateToFormatted))) {
                    salary += Integer.parseInt(dataPart[2]) * Integer.parseInt(dataPart[3]);
                }
            }

            output.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);

        }

        return output.toString();
    }

}
