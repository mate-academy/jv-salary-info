package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DAY_DATE = 0;
    private static final int WORKERS_NAME = 1;
    private static final int WORK_TIME = 2;
    private static final int PER_HOUR_SALARY = 3;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, dtf);
        LocalDate dateToFormat = LocalDate.parse(dateTo, dtf);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;
        for (String name : names) {
            for (String dataElement : data) {
                String[] newDataElement = dataElement.split(" ");
                LocalDate workDay = LocalDate.parse(newDataElement[WORKING_DAY_DATE], dtf);
                if ((newDataElement[WORKERS_NAME].equals(name))
                        && (workDay.isAfter(dateFromFormat)
                        || workDay.isEqual(dateFromFormat))
                        && (workDay.isBefore(dateToFormat)
                        || workDay.isEqual(dateToFormat))) {
                    salary += Integer.parseInt(newDataElement[WORK_TIME])
                            * Integer.parseInt(newDataElement[PER_HOUR_SALARY]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return result.toString();
    }
}
