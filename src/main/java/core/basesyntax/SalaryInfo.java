package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int EMPLOYEE_DAY = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int EMPLOYEE_HOURS = 2;
    private static final int EMPLOYEE_HOURS_SALARY = 3;
    private int totalSalaryEmployee = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            totalSalaryEmployee = 0;
            totalSalaryEmployee = getTotalSalory(names[i],data,dateFrom,dateTo);
            builder.append(System.lineSeparator()).append(names[i]);
            builder.append(" - ").append(totalSalaryEmployee);
        }
        return builder.toString();
    }

    public int getTotalSalory(String name, String[] data, String dateFrom, String dateTo) {
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split(" ");
            if (getPeriod(temp[EMPLOYEE_DAY], dateFrom, dateTo)
                    & name.equals(temp[EMPLOYEE_NAME])) {
                int hours = Integer.parseInt(temp[EMPLOYEE_HOURS]);
                int hoursSalary = Integer.parseInt(temp[EMPLOYEE_HOURS_SALARY]);
                totalSalaryEmployee += hoursSalary * hours;
            }
        }
        return totalSalaryEmployee;
    }

    public boolean getPeriod(String dateCheck, String dateFrom, String dateTo) {
        LocalDate dateBegin = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo,FORMATTER);
        dateBegin = dateBegin.minusDays(1);
        dateEnd = dateEnd.plusDays(1);
        boolean getResultPeriod = false;
        if (dateBegin.isBefore(LocalDate.parse(dateCheck, FORMATTER))
                & dateEnd.isAfter(LocalDate.parse(dateCheck, FORMATTER))) {
            getResultPeriod = true;
        }
        return getResultPeriod;
    }
}
