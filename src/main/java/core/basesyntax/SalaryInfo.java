package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORK_DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int SALARY_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        String infoSalary = null;
        for (String name : names) {
            int salary = 0;
            for (String infoData : data) {
                String[] splitData = infoData.split(" ");
                LocalDate localSplitDate = LocalDate.parse(splitData[WORK_DATE], FORMATTER);
                if ((localDateFrom.isBefore(localSplitDate)
                        || localDateTo.isEqual(localSplitDate))
                        && (localDateTo.isAfter(localSplitDate)
                        || localDateTo.isEqual(localSplitDate))
                        && name.equals(splitData[EMPLOYEE_NAME])) {
                    salary = salary
                            + Integer.parseInt(splitData[WORK_HOURS])
                            * Integer.parseInt(splitData[SALARY_HOUR]);
                }
            }
            infoSalary = builder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary).toString();
        }
        return builder.toString();
    }
}
