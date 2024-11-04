package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    private final Pattern datePattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                if (record.contains(name)) {
                    LocalDate date = extractDate(record);
                    if (date != null && (date.isEqual(fromDate) || date.isEqual(toDate)
                            || (date.isAfter(fromDate) && date.isBefore(toDate)))) {

                        String[] parts = record.split(" ");
                        String recordName = parts[1];
                        if (recordName.equals(name)) {
                            int hoursWorked = Integer.parseInt(parts[2]);
                            int incomePerHour = Integer.parseInt(parts[3]);
                            int salary = hoursWorked * incomePerHour;
                            totalSalary += salary;
                        }
                    }
                }
            }
            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private LocalDate extractDate(String input) {
        Matcher matcher = datePattern.matcher(input);

        if (matcher.find()) {
            String dateString = matcher.group();
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Помилка формату дати: " + dateString);
            }
        }
        return null;
    }
}
