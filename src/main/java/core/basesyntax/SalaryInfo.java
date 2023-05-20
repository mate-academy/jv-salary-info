package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DAY_DATE_INDEX = 0;
    private static final int WORKERS_NAME_INDEX = 1;
    private static final int WORK_TIME_INDEX = 2;
    private static final int PER_HOUR_SALARY_INDEX = 3;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, dtf);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, dtf);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataElement : data) {
                String[] newDataElement = dataElement.split(" ");
                LocalDate workDay = LocalDate.parse(newDataElement[WORKING_DAY_DATE_INDEX], dtf);
                if ((newDataElement[WORKERS_NAME_INDEX].equals(name))
                        && (workDay.isAfter(dateFromFormatted)
                        || workDay.isEqual(dateFromFormatted))
                        && (workDay.isBefore(dateToFormatted)
                        || workDay.isEqual(dateToFormatted))) {
                    salary += Integer.parseInt(newDataElement[WORK_TIME_INDEX])
                            * Integer.parseInt(newDataElement[PER_HOUR_SALARY_INDEX]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }
}
