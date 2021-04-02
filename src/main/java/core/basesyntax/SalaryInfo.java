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
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMAT);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMAT).plusDays(1);
        String[] newData = new String[NEW_ARRAY_INDEX];
        StringBuilder finalList = new StringBuilder();

        finalList.append("Report for period").append(" ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (int y = 0; y < names.length; y++) {
            int employeeSalary = 0;

            for (int i = 0; i < data.length; i++) {
                String[] employeesData = data[i].split(" ");

                LocalDate actualDate = LocalDate.parse(employeesData[DATE_INDEX], FORMAT);
                String employeeName = employeesData[NAME_INDEX];

                if (actualDate.isAfter(firstDate) && actualDate.isBefore(lastDate)
                        && names[y].equals(employeeName)) {
                    employeeSalary += Integer.parseInt(employeesData[DAYS_INDEX])
                            * Integer.parseInt(employeesData[INCOME_INDEX]);

                }
            }

            finalList.append("\n").append(names[y]).append(" - ")
                    .append(Integer.toString(employeeSalary));
        }

        return finalList.toString();
    }
}
