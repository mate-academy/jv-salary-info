package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    private static final int WORK_DAY = 0;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private static final int WORKER_NAME = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);
        SalaryInfo salaryInfo = new SalaryInfo();
        LocalDate fromDate = salaryInfo.toLocalDate(dateFrom);
        LocalDate toDate = salaryInfo.toLocalDate(dateTo);
        String[] splitedData;
        LocalDate workDayDate;
        int salary;
        for (String name : names) {
            salary = WORK_DAY;
            for (String datum : data) {
                splitedData = datum.split(" ");
                if (!name.equals(splitedData[WORKER_NAME])) {
                    continue;
                }
                workDayDate = salaryInfo.toLocalDate(splitedData[WORK_DAY]);
                if (isWorkDayValid(fromDate, toDate, workDayDate)) {
                    salary += Integer.parseInt(splitedData[HOURS])
                            * Integer.parseInt(splitedData[INCOME_PER_HOUR]);
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return info.toString();
    }

    private boolean isWorkDayValid(LocalDate fromDate, LocalDate toDate, LocalDate workDayDate) {
        return (toDate.isAfter(workDayDate) && fromDate.isBefore(workDayDate))
                || workDayDate.isEqual(fromDate)
                || workDayDate.isEqual(toDate);
    }

    private LocalDate toLocalDate(String date) {
        String[] splittedDate = date.split("\\.");
        if (splittedDate[WORK_DAY].charAt(WORK_DAY) == '0') {
            splittedDate[WORK_DAY] = String.valueOf(splittedDate[WORK_DAY].charAt(WORKER_NAME));
        }
        if (splittedDate[WORKER_NAME].charAt(WORK_DAY) == '0') {
            splittedDate[WORKER_NAME] = String.valueOf(splittedDate[WORKER_NAME]
                    .charAt(WORKER_NAME));
        }
        return LocalDate.of(Integer.parseInt(splittedDate[HOURS]),
                Integer.parseInt(splittedDate[WORKER_NAME]),
                Integer.parseInt(splittedDate[WORK_DAY]));
    }
}
