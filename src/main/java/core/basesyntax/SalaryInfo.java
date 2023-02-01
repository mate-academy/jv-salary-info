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

        for (String name: names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String row = data[i];
                String [] rowData = row.split(" ");
                if(inRange(rowData[DATE_INDEX], dateFrom, dateTo) &&
                        name.equals(rowData[USER_NAME_INDEX])) {
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

    public boolean inRange(String date, String dForm, String dTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFrom = LocalDate.parse(dForm, format);
        LocalDate dateTo = LocalDate.parse(dTo, format);
        LocalDate currentDate = LocalDate.parse(date,format);
        return (currentDate.equals(dateFrom) || currentDate.isAfter(dateFrom)) &&
                (currentDate.isEqual(dateTo) || currentDate.isBefore(dateTo));
    }

    public int calculateSalary(int hours, int incomePerHour) {
        return hours * incomePerHour;
    }
}
