package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final int ONE_DAY = 1;
    private static final String LINE_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] splitDateFrom = dateFrom.split("\\.");
        String[] splitDateTo = dateTo.split("\\.");
        LocalDate dateBefore = LocalDate.of(Integer.parseInt(splitDateFrom[YEAR_INDEX]),
                Integer.parseInt(splitDateFrom[MONTH_INDEX]),
                Integer.parseInt(splitDateFrom[DAY_INDEX]));
        LocalDate dateAfter = LocalDate.of(Integer.parseInt(splitDateTo[YEAR_INDEX]),
                Integer.parseInt(splitDateTo[MONTH_INDEX]),
                Integer.parseInt(splitDateTo[DAY_INDEX]));

        for (String name : names) {
            int sumSalary = 0;
            for (String dataLine:data) {
                String[] dataLineParts = dataLine.split(LINE_SEPARATOR);
                String[] dataLinePartWithDate = dataLineParts[DATE_INDEX].split("\\.");
                LocalDate workDate = LocalDate.of(Integer
                        .parseInt(dataLinePartWithDate[YEAR_INDEX]),
                        Integer.parseInt(dataLinePartWithDate[MONTH_INDEX]),
                        Integer.parseInt(dataLinePartWithDate[DAY_INDEX]));
                if (dataLineParts[NAME_INDEX].equals(name)
                        && workDate.isAfter(dateBefore)
                        && workDate.isBefore(dateAfter.plusDays(ONE_DAY))) {
                    sumSalary = sumSalary + Integer.parseInt(dataLineParts[WORK_HOURS_INDEX])
                            * Integer.parseInt(dataLineParts[SALARY_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }
        return result.toString();
    }
}
