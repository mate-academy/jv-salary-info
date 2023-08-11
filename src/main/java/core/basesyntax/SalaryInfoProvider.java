package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfoProvider {
    private static final String PERSONAL_DATA_DELIMITER = " ";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            result.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String personalData : data) {
                String[] splitPersonalData = personalData.split(PERSONAL_DATA_DELIMITER);
                String workingDate = splitPersonalData[0];
                String personName = splitPersonalData[1];
                String workingHours = splitPersonalData[2];
                String salaryPerHour = splitPersonalData[3];
                if (personName.equals(name) && isRelevantDate(workingDate, dateFrom, dateTo)) {
                    salary += Integer.parseInt(workingHours) * Integer.parseInt(salaryPerHour);
                }
            }
            result.append(salary);
        }
        return result.toString();
    }

    private boolean isRelevantDate(String dateStr, String dateFromStr, String dateToStr) {
        LocalDate date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
        LocalDate dateFrom = LocalDate.parse(dateFromStr, DATE_TIME_FORMATTER);
        LocalDate dateTo = LocalDate.parse(dateToStr, DATE_TIME_FORMATTER);
        return date.isAfter(dateFrom) && date.isBefore(dateTo)
                || date.isEqual(dateFrom) || date.isEqual(dateTo);
    }
}
