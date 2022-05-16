package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dataStart = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dataEnd = LocalDate.parse(dateTo, FORMAT);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int salary;
        for (String name: names) {
            salary = 0;
            for (String dataString: data) {
                String[] dataStringArray = dataString.split(" ");
                if (name.equals(dataStringArray[1])
                        && checkDate(dataStringArray[0], dataStart, dataEnd)) {
                    salary += getDaySalary(dataStringArray[2], dataStringArray[3]);
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public boolean checkDate(String date, LocalDate dateStart, LocalDate dateEnd) {
        LocalDate localDate = LocalDate.parse(date, FORMAT);
        return (localDate.isAfter(dateStart) || localDate.equals(dateStart))
                && (localDate.isBefore(dateEnd) || localDate.equals(dateEnd));
    }

    public int getDaySalary(String hours, String salaryPerHour) {
        return Integer.parseInt(hours) * Integer.parseInt(salaryPerHour);
    }
}
