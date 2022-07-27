package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            if (name != null) {
                String salary = getSalaryEmployee(name, data, dateFrom, dateTo);
                report.append(System.lineSeparator()).append(salary);
            }
        }
        return report.toString();
    }

    private String getSalaryEmployee(String name, String[] data, String dateFrom, String dateTo) {
        int salaryEmployeeByDay = 0;
        for (String workDate: data) {
            String[] dataInfo = workDate.split(" ");
            String nameWorker = dataInfo[1];
            String workingDay = dataInfo[0];
            int workingHour = Integer.parseInt(dataInfo[2]);
            int incomePerHour = Integer.parseInt(dataInfo[3]);
            if (nameWorker.equals(name)) {
                salaryEmployeeByDay = checkWorkingDay(workingDay, dateFrom, dateTo) ? workingHour * incomePerHour
                        : salaryEmployeeByDay;
            }
        }
        return name + " - " + salaryEmployeeByDay;
    }

    private boolean checkWorkingDay(String workingDay, String dateFrom, String dateTo) {
        try {
            Date workDate = DATE_FORMAT.parse(workingDay);
            Date dateStart = DATE_FORMAT.parse(dateFrom);
            Date dateEnd = DATE_FORMAT.parse(dateTo);
            return workDate.after(dateStart) && workDate.before(dateEnd)
                    || workDate.equals(dateEnd);
        } catch (ParseException e) {
            throw new RuntimeException("Date format isn't correct (dd.MM.yyyy)", e);
        }
    }
}
