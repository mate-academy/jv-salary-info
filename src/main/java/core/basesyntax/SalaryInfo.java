package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        try {
            for (int i = 0; i < names.length; ++i) {
                int totalSalary = 0;
                for (String record : data) {
                    totalSalary += calculateSalaryForNameAndDate(names[i],
                            record, dateFrom, dateTo);
                }
                result.append(names[i]).append(" - ").append(totalSalary);

                if (i != names.length - 1) {
                    result.append(System.lineSeparator());
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Is not parsable: " + e.getMessage());
            throw e;
        }

        return result.toString();
    }

    private int calculateSalaryForNameAndDate(String name, String record,
                                              String dateFrom, String dateTo) {
        String[] recordParts = record.split(" ");
        String recordDate = recordParts[0];

        if (isDateInRange(recordDate, dateFrom, dateTo) && record.contains(name)) {
            int hoursWorked = Integer.parseInt(recordParts[2]);
            int hourlyRate = Integer.parseInt(recordParts[3]);
            return hoursWorked * hourlyRate;
        }
        return 0;
    }

    private boolean isDateInRange(String recordDate, String dateFrom, String dateTo) {
        LocalDate recordLocalDate = LocalDate.parse(recordDate, dateFormatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        return !recordLocalDate.isBefore(fromDate) && !recordLocalDate.isAfter(toDate);
    }
}
