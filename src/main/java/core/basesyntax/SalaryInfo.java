package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int HOUR_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name: names) {
            int resultSalary = 0;
            for (String line: data) {
                String[] arrayOfData = line.split(" ");
                LocalDate currentDate
                        = LocalDate.parse(arrayOfData[DATE_INDEX], DATE_TIME_FORMATTER);
                if (currentDate.isAfter(dateStart)
                        && currentDate.isBefore(dateEnd)
                        || currentDate.equals(dateStart)
                        || currentDate.equals(dateEnd)) {
                    String nameFromLine = line.split(" ")[NAME_INDEX];
                    if (nameFromLine.equals(name)) {
                        String dataEmployee = line.substring(line.indexOf(name));
                        String[] hourSalary = dataEmployee.split(" ");
                        int hour = Integer.parseInt(hourSalary[HOUR_INDEX]);
                        int salaryValue = Integer.parseInt(hourSalary[SALARY_INDEX]);
                        int salary = hour * salaryValue;
                        resultSalary += salary;
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(resultSalary);
        }

        return result.toString();
    }
}
