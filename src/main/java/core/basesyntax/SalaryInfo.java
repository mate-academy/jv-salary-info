package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.now();
        LocalDate localDateTo = LocalDate.now();
        try {
            localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong format of dateFrom!");
        }
        try {
            localDateTo = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong format of dateTo!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period " + localDateFrom.format(FORMATTER) + " - " + localDateTo.format(FORMATTER) + '\n');

        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] parsedData = element.split(" ");
                if (parsedData.length != 4) {
                    continue;
                }
                if (! parsedData[1].equals(name)) {
                    continue;
                }

                LocalDate workingDate;
                int workingHours = 0;
                int ratePerHour = 0;

                try {
                    workingDate = LocalDate.parse(parsedData[0], FORMATTER);
                    workingHours = Integer.valueOf(parsedData[2]);
                    ratePerHour = Integer.valueOf(parsedData[3]);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("There are some problems with data: " + element);
                    continue;
                }

                if ((workingDate.isAfter(localDateFrom) && workingDate.isBefore(localDateTo))
                    || workingDate.isEqual(localDateFrom) || workingDate.isEqual(localDateTo)) {
                    salary += workingHours * ratePerHour;
                }
            }
            sb.append(name + " - " + salary + '\n');
        }
        return sb.toString();
    }
}
