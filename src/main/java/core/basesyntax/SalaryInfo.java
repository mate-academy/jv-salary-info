package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String REPORT_SEPARATOR = " - ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(dateFrom, formatter);
            endDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Wrong date formats.");
        }
        String reportHeader =
                String.format("Report for period %1$s - %2$s", startDate.format(formatter),
                        endDate.format(formatter));
        StringBuilder report = new StringBuilder(reportHeader);
        for (String name : names) {
            int employeeSalary = 0;
            for (String dataRecord : data) {
                if (!dataRecord.contains(name)) {
                    continue;
                }
                final int dataEmployeeWorkingHoursIndex = 2;
                final int dataEmployeeIncomePerHourIndex = 3;
                LocalDate parsedDate;
                String[] dataRecordSplit = dataRecord.split(" ");
                String employeeWorkingHours = dataRecordSplit[dataEmployeeWorkingHoursIndex];
                String employeeIncomePerHour = dataRecordSplit[dataEmployeeIncomePerHourIndex];
                try {
                    parsedDate = LocalDate.parse(dataRecordSplit[0], formatter);
                    if (parsedDate.toEpochDay() < startDate.toEpochDay()
                            || parsedDate.toEpochDay() > endDate.toEpochDay()) {
                        continue;
                    }
                    employeeSalary += Integer.parseInt(employeeWorkingHours) * Integer.parseInt(
                            employeeIncomePerHour);
                } catch (DateTimeParseException exception) {
                    throw new IllegalArgumentException("Wrong date formats in data.");
                } catch (NumberFormatException exception) {
                    throw new IllegalArgumentException("Wrong numeric values format in data.");
                }
            }
            report.append(System.lineSeparator());
            report.append(name).append(REPORT_SEPARATOR).append(employeeSalary);
        }
        return String.valueOf(report);
    }
}
