package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryCounter = new int[names.length];
        try {
            LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
            LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

            for (String record : data) {
                String[] splitRecord = record.split(" ");
                LocalDate date = LocalDate.parse(splitRecord[0], formatter);

                if (!localDateFrom.isAfter(date) && !localDateTo.isBefore(date)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(splitRecord[1])) {
                            salaryCounter[i] +=
                              Integer.parseInt(splitRecord[2]) * Integer.parseInt(splitRecord[3]);
                            break;
                        }
                    }

                }
            }

        } catch (DateTimeParseException e) {
            System.out.println("Can not parse date");
        } catch (NumberFormatException e) {
            System.out.println("Can not parse salary values");
        }

        StringBuilder builder = new StringBuilder("Report for period ")
                  .append(dateFrom)
                  .append(" - ")
                  .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            builder
              .append(System.lineSeparator())
              .append(names[i])
              .append(" - ").append(salaryCounter[i]);
        }

        return builder.toString();
    }
}
