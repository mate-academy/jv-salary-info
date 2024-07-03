package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " - ";
    private static final String REGEX = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOURS_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startWorkDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endWorkDate = LocalDate.parse(dateTo.trim(), formatter);

        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;
            report.append(name).append(SEPARATOR);

            for (String currentData : data) {
                String[] splitData = currentData.split(REGEX);
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX].trim(), formatter);
                String nameEmployee = splitData[NAME_INDEX].trim();
                int workHours = Integer.parseInt(splitData[WORK_HOURS_INDEX].trim());
                int salaryPerHours = Integer.parseInt(splitData[SALARY_PER_HOURS_INDEX].trim());

                if (!date.isBefore(startWorkDate) && !date.isAfter(endWorkDate)
                        && name.equals(nameEmployee)) {
                    salary += workHours * salaryPerHours;
                }
            }
            report.append(salary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
