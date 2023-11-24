package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATER);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String salaryItem : data) {
                String[] splitedData = salaryItem.split(" ");
                LocalDate tempDate = LocalDate.parse(splitedData[DATE_INDEX], DATE_FORMATER);
                if (splitedData[NAME_INDEX].equals(name)
                        && !tempDate.isBefore(fromDate)
                        && !tempDate.isAfter(toDate)) {
                    salary = salary + (Integer.parseInt(splitedData[HOURS_INDEX])
                            * Integer.parseInt(splitedData[SALARY_INDEX]));
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
