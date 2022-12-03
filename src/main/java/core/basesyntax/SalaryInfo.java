package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int OPENING_HOURS = 2;
    private static final int SALARY_PER_HOR = 3;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String dates : data) {
                String[] sortArrayData = dates.split(" ");
                LocalDate dateWhenWorked = LocalDate.parse(sortArrayData[DATE_INDEX], DATE_FORMATTER);
                if (sortArrayData[NAME_INDEX].equals(name) &&
                        ((dateWhenWorked.isAfter(startDate)) || (dateWhenWorked.isEqual(startDate)))
                        && (dateWhenWorked.isBefore(endDate) || dateWhenWorked.isEqual(endDate))) {
                    salary += Integer.parseInt(sortArrayData[OPENING_HOURS])
                            * Integer.parseInt(sortArrayData[SALARY_PER_HOR]);
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
