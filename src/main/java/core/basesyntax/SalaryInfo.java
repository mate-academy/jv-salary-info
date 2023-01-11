package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKED_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int RATE_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        String[] workSchedule;
        StringBuilder sb = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                workSchedule = line.split(" ", 4);
                LocalDate checkDate = LocalDate.parse(workSchedule[WORKED_DATE_INDEX], DATE_FORMAT);
                if (name.equals(workSchedule[EMPLOYEE_NAME_INDEX])
                        && !checkDate.isBefore(fromDate)
                        && !checkDate.isAfter(toDate)) {
                    totalSalary += (Integer.parseInt(workSchedule[WORKED_HOURS_INDEX])
                            * Integer.parseInt(workSchedule[RATE_PER_HOUR_INDEX]));
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return sb.toString();
    }
}