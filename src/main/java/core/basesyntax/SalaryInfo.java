package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_PER_HOUR_INDEX = 3;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder employeeSalaryInfo = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sumEmployeeSalary = 0;
            for (String dataInfo : data) {
                if (dataInfo.contains(name)) {
                    LocalDate localDate =
                            LocalDate.parse(dataInfo.split(" ")[DATE_INDEX], DATE_FORMAT);
                    if (!localDate.isBefore(firstDate) && !localDate.isAfter(lastDate)) {
                        sumEmployeeSalary += Integer.parseInt(dataInfo.split(" ")[HOURS_INDEX])
                                * Integer.parseInt(dataInfo.split(" ")[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            employeeSalaryInfo.append("\n").append(name).append(" - ").append(sumEmployeeSalary);
        }
        return employeeSalaryInfo.toString();
    }
}
