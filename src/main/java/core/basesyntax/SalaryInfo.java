package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            for (String info : data) {
                String[] arrayInfo = info.split(" ");
                LocalDate date = LocalDate.parse(arrayInfo[DATE_INDEX], formatter);
                if (name.equals(arrayInfo[NAME_INDEX])
                        && date.compareTo(fromDate) >= 0
                        && date.compareTo(toDate) <= 0) {
                    salary += Integer.parseInt(arrayInfo[HOURS_INDEX])
                            * Integer.parseInt(arrayInfo[SALARY_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }
}
