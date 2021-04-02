package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int DAYS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final int NEW_ARRAY_INDEX = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMAT).minusDays(1);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMAT).plusDays(1);
        StringBuilder finalList = new StringBuilder();

        finalList.append("Report for period").append(" ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int employeeSalary = 0;

            for (String datum : data) {
                String[] employeesData = datum.split(" ");

                LocalDate actualDate = LocalDate.parse(employeesData[DATE_INDEX], FORMAT);
                String employeeName = employeesData[NAME_INDEX];

                if (actualDate.isAfter(firstDate) && actualDate.isBefore(lastDate)
                        && name.equals(employeeName)) {
                    employeeSalary += Integer.parseInt(employeesData[DAYS_INDEX])
                            * Integer.parseInt(employeesData[INCOME_INDEX]);

                }
            }

            finalList.append("\n").append(name).append(" - ")
                    .append(Integer.toString(employeeSalary));
        }
        return finalList.toString();
    }
}
