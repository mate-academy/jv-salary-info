package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    private static final int WORK_DAY_INDEX = 0;
    private static final int DAY_INDEX = 0;
    private static final int WORKER_NAME_INDEX = 1;
    private static final int MONTH_INDEX = 1;
    private static final int HOURS_IDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final int YEAR_INDEX = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        SalaryInfo salaryInfo = new SalaryInfo();
        LocalDate fromDate = salaryInfo.toLocalDate(dateFrom);
        LocalDate toDate = salaryInfo.toLocalDate(dateTo);
        String[] splitedData;
        LocalDate workDayDate;
        int salary;
        for (String name : names) {
            salary = 0;
            for (String datum : data) {
                splitedData = datum.split(" ");
                if (!name.equals(splitedData[WORKER_NAME_INDEX])) {
                    continue;
                }
                workDayDate = salaryInfo.toLocalDate(splitedData[WORK_DAY_INDEX]);
                if (isWorkDayValid(fromDate, toDate, workDayDate)) {
                    salary += Integer.parseInt(splitedData[HOURS_IDEX])
                            * Integer.parseInt(splitedData[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private boolean isWorkDayValid(LocalDate fromDate, LocalDate toDate, LocalDate workDayDate) {
        return (toDate.isAfter(workDayDate) && fromDate.isBefore(workDayDate))
                || workDayDate.isEqual(fromDate)
                || workDayDate.isEqual(toDate);
    }

    private LocalDate toLocalDate(String date) {
        String[] splittedDate = date.split("\\.");
        trimZeros(splittedDate, DAY_INDEX);
        trimZeros(splittedDate, MONTH_INDEX);
        return LocalDate.of(Integer.parseInt(splittedDate[YEAR_INDEX]),
                Integer.parseInt(splittedDate[MONTH_INDEX]),
                Integer.parseInt(splittedDate[DAY_INDEX]));
    }

    private void trimZeros(String[] splittedDate, int index) {
        if (splittedDate[index].charAt(0) == '0') {
            splittedDate[index] = String.valueOf(splittedDate[index].charAt(1));
        }
    }
}
