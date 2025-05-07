package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arrayOfData = line.split(" ");
                LocalDate localDateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate localDateDateTo = LocalDate.parse(dateTo, FORMATTER);
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE_INDEX], FORMATTER);
                if (localDateDateFrom.compareTo(currentDate) <= 0
                        && localDateDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[NAME_INDEX].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[WORK_HOUR_INDEX]))
                            * Integer.parseInt(arrayOfData[SALARY_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
