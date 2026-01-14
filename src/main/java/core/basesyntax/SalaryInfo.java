package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryResult = new StringBuilder();

        for (String name : names) {
            int finalSalary = 0;
            for (String info : data) {
                String[] splitted = info.split(" ");
                if (name.equals(splitted[NAME_POSITION])) {
                    LocalDate splittedDate = LocalDate.parse(splitted[DATE_POSITION], FORMATTER);
                    if (!splittedDate.isBefore(firstDate) && !splittedDate.isAfter(lastDate)) {
                        finalSalary += Integer.parseInt(splitted[HOURS_POSITION])
                                * Integer.parseInt(splitted[SALARY_POSITION]);
                    }
                }
            }
            salaryResult
                    .append(name)
                    .append(" - ")
                    .append(finalSalary)
                    .append(System.lineSeparator());
        }

        String employeeData = salaryResult.toString().trim();
        String output = "Report for period "
                + dateFrom + " - " + dateTo;

        if (!employeeData.isEmpty()) {
            output += System.lineSeparator() + employeeData;
        }

        return output;
    }
}
