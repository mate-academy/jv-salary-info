package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int DATE_OF_WORK = 0;
    private static final int NAME_WORKER = 1;
    private static final int WORKING_HOUR = 2;
    private static final int INCOME_PER_DAY = 3;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            String salary = getSalaryEmployee(name, data, dateFrom, dateTo);
            report.append(System.lineSeparator()).append(salary);
        }
        return report.toString();
    }

    private String getSalaryEmployee(String name, String[] data, String dateFrom, String dateTo) {
        int salaryEmployeeByDay = 0;
        for (String workDate: data) {
            String[] dataInfo = workDate.split(" ");
            String nameWorker = dataInfo[NAME_WORKER];
            String workingDay = dataInfo[DATE_OF_WORK];
            int workingHour = Integer.parseInt(dataInfo[WORKING_HOUR]);
            int incomePerHour = Integer.parseInt(dataInfo[INCOME_PER_DAY]);
            if (checkWorkingDay(workingDay, dateFrom, dateTo, nameWorker, name)) {
                salaryEmployeeByDay += workingHour * incomePerHour;
            }
        }
        return name + " - " + salaryEmployeeByDay;
    }

    private boolean checkWorkingDay(String workingDay, String dateFrom, String dateTo,
                                    String nameWorker, String name) {
        if (nameWorker.equals(name)) {
            try {
                Date workDate = DATE_FORMAT.parse(workingDay);
                Date dateStart = DATE_FORMAT.parse(dateFrom);
                Date dateEnd = DATE_FORMAT.parse(dateTo);
                return workDate.after(dateStart) && workDate.before(dateEnd)
                        || workDate.equals(dateEnd);
            } catch (ParseException e) {
                throw new RuntimeException("Date format isn't correct (dd.MM.yyyy)", e);
            }
        } else {
            return false;
        }
    }
}
