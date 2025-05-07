package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PERSONAL_DATA_DELIMITER = " ";
    private static final String REPORT_START = "Report for period ";
    private static final String HYPHEN = " - ";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append(REPORT_START).append(dateFrom).append(HYPHEN).append(dateTo);
        for (String name : names) {
            salaryInfo.append(System.lineSeparator()).append(name).append(HYPHEN);
            int salary = 0;
            for (String personalData : data) {
                String[] splitPersonalData = personalData.split(PERSONAL_DATA_DELIMITER);
                String workingDate = splitPersonalData[0];
                String personName = splitPersonalData[1];
                String workingHours = splitPersonalData[2];
                String salaryPerHour = splitPersonalData[3];
                if (personName.equals(name) && isDateBetween(workingDate, dateFrom, dateTo)) {
                    salary += calculateDailySalary(workingHours, salaryPerHour);
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }

    private boolean isDateBetween(String target, String from, String to) {
        LocalDate date = parse(target);
        LocalDate dateFrom = parse(from);
        LocalDate dateTo = parse(to);
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }

    private LocalDate parse(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private int calculateDailySalary(String workingHours, String salaryPerHour) {
        return Integer.parseInt(workingHours) * Integer.parseInt(salaryPerHour);
    }
}
