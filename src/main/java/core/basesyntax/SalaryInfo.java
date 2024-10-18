package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_POSITION = 0;
    private static final int NAME = 1;
    private static final int HOUR = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info: data) {
                String[] infoSplit = info.split(" ");
                LocalDate recordDate = LocalDate.parse(infoSplit[DAY_POSITION], DATE_FORMATTER);
                String employee = infoSplit[NAME];
                int hours = Integer.parseInt(infoSplit[HOUR]);
                int salaryHour = Integer.parseInt(infoSplit[SALARY_PER_HOUR]);

                if (employee.equals(name) && !recordDate.isBefore(fromDate)
                        && !recordDate.isAfter(toDate)) {
                    salary += hours * salaryHour;
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}
