package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name: names) {
            int salary = 0;
            for (String record: data) {
                String[] currentRecord = record.split(SPACE);
                LocalDate currentDate = LocalDate
                        .parse(currentRecord[DATE_INDEX], DATE_FORMAT);
                if (name.equals(currentRecord[NAME_INDEX])
                        && (currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                        && (currentDate.isBefore(toDate) || currentDate.equals(toDate))) {
                    salary += Integer.parseInt(currentRecord[HOURS_INDEX])
                            * Integer.parseInt(currentRecord[SALARY_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
