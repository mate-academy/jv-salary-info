package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format!" + e);
            return "Error: Incorrect date format.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalSalary = 0;

            for (String record : data) {
                String[] separateData = record.split(" ");
                LocalDate workDate = LocalDate.parse(separateData[0], DATE_FORMATTER);
                String employeeName = separateData[1];
                int hoursWorked = Integer.parseInt(separateData[2]);
                int hourlyRate = Integer.parseInt(separateData[3]);

                if (employeeName.equals(name)
                        && (workDate.isEqual(startDate) || workDate.isAfter(startDate))
                        && (workDate.isEqual(endDate) || workDate.isBefore(endDate))) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            report.append(name).append(" - ").append(totalSalary);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
