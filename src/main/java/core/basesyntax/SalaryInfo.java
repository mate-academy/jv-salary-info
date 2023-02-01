package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int USER_NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for(String name: names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String row = data[i];
                String [] rowData = row.split(" ");
                if(inRange(rowData[DATE_INDEX], dateFrom, dateTo) && name.equals(rowData[USER_NAME_INDEX])) {
                    salary += calculateSalary(Integer.parseInt(rowData[WORK_HOURS_INDEX]),
                            Integer.parseInt(rowData[INCOME_PER_HOUR_INDEX]));
                }
            }
            finalResult.append(System.getProperty("line.separator"))
                    .append(name)
                    .append(" - ")
                    .append(salary);

        }
        return finalResult.toString();
    }

    public boolean inRange(String date, String dateForm, String dateTo) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dFrom = LocalDate.parse(dateForm, FORMATTER);
        LocalDate dTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate currentDate = LocalDate.parse(date,FORMATTER);
        return (currentDate.equals(dFrom) || currentDate.isAfter(dFrom)) &&
                (currentDate.isEqual(dTo) || currentDate.isBefore(dTo));
    }

    public int calculateSalary(int hours, int incomePerHour) {
        return hours * incomePerHour;
    }
}
