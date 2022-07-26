package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        try {
            for (String name: names) {
                String salary = getSalaryEmployee(name, data, dateFrom, dateTo);
                stringBuilder.append(System.lineSeparator()).append(salary);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public String getSalaryEmployee(String name, String[] data, String dateFrom, String dateTo) {

        int salaryEmployeeByDay = 0;
        for (String workDate: data) {
            if (workDate.contains(name)) {
                String[] str = workDate.split(" ");
                String date = str[0];
                if (checkWorkingDay(date, dateFrom, dateTo)) {
                    salaryEmployeeByDay += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
        }
        return name + " - " + salaryEmployeeByDay;
    }

    public boolean checkWorkingDay(String workingDay, String dateFrom, String dateTo) {
        try {
            Date workDate = formatter.parse(workingDay);
            Date dateStart = formatter.parse(dateFrom);
            Date dateEnd = formatter.parse(dateTo);
            return workDate.after(dateStart) && workDate.before(dateEnd)
                    || workDate.equals(dateEnd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
