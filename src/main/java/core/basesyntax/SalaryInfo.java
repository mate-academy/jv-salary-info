package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] splitData = datum.split(" ");
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX], formatter);
                if (name.equals(splitData[NAME_INDEX])
                        && ((date.isAfter(dateF) && date.isBefore(dateT))
                        || (date.isEqual(dateF) || date.isEqual(dateT)))) {
                    salary += Integer.parseInt(splitData[HOURS_INDEX])
                            * Integer.parseInt(splitData[HOUR_SALARY_INDEX]);
                }
            }
            result.append("\n" + name + " - " + salary);
        }
        return result.toString();
    }
}
