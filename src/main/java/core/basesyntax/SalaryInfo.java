package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DAY_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String NEW_LINE = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromData = LocalDate.parse(dateFrom, DAY_FORMAT);
        LocalDate toData = LocalDate.parse(dateTo, DAY_FORMAT);
        StringBuilder salaryResult = new StringBuilder();
        salaryResult.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            salaryResult.append(NEW_LINE);
            int salary = 0;
            for (String dataDay : data) {
                String[] dataArray = dataDay.split(" ");
                LocalDate date = LocalDate.parse(dataArray[DATE_INDEX], DAY_FORMAT);
                int hours = Integer.parseInt(dataArray[HOURS_INDEX]);
                int perHour = Integer.parseInt(dataArray[SALARY_PER_HOUR]);
                if (dataArray[NAME_INDEX].equals(name) && (date.isAfter(fromData)
                        || date.isEqual(fromData)) && (date.isBefore(toData)
                        || date.isEqual(toData))) {
                    salary = salary + hours * perHour;
                }
            }
            salaryResult.append(name).append(" - ").append(salary);
        }
        return salaryResult.toString();
    }
}
