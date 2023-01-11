package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKED_DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKED_HOURS = 2;
    private static final int RATE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        String[] workSchedule;
        StringBuilder sb = new StringBuilder().append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String implName : names) {
            int totalSalary = 0;
            for (String implData : data) {
                workSchedule = implData.split(" ", 4);
                LocalDate checkDate = LocalDate.parse(workSchedule[WORKED_DATE], DATE_FORMAT);
                if (implName.equals(workSchedule[EMPLOYEE_NAME])
                        && !checkDate.isBefore(fromDate)
                        && !checkDate.isAfter(toDate)) {
                    totalSalary += (Integer.parseInt(workSchedule[WORKED_HOURS])
                            * Integer.parseInt(workSchedule[RATE_PER_HOUR]));
                }
            }
            sb.append(System.lineSeparator()).append(implName).append(" - ").append(totalSalary);
        }
        return sb.toString();
    }
}
