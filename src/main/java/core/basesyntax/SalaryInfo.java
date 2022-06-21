package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
            if (getPeriod(temp[0], dateFrom, dateTo) & name.equals(temp[1])) {
                int hourSalary = Integer.parseInt(temp[2]);
                int hours = Integer.parseInt(temp[3]);
                totalSalaryEmployee += hourSalary * hours;
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
