package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int totalSalory = 0;
            totalSalory = getTotalSalory(names[i],data,dateFrom,dateTo);
            builder.append("\n").append(names[i]);
            builder.append(" - ").append(totalSalory);
        }
        return builder.toString();
    }

    public int getTotalSalory(String name, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateBegin = LocalDate.parse(dateFrom,formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo,formatter);
        dateBegin = dateBegin.minusDays(1);
        dateEnd = dateEnd.plusDays(1);
        int totalSalary = 0;
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split(" ");
            if (dateBegin.isBefore(LocalDate.parse(temp[0],formatter))
                    & dateEnd.isAfter(LocalDate.parse(temp[0], formatter))
                    & name.equals(temp[1])) {
                int hourSalary = Integer.parseInt(temp[2]);
                int hours = Integer.parseInt(temp[3]);
                totalSalary += hourSalary * hours;
            }
        }
        return totalSalary;
    }
}
