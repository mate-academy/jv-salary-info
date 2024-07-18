package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        int salary;
        LocalDate start = LocalDate.parse(dateFrom, formatter); // converting String to Date
        LocalDate end = LocalDate.parse(dateTo, formatter); // converting String to Date
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) { // take each name in turn from the array
            salary = 0; // reset variable after every exit from the loop
            for (int i = 0; i < data.length; i++) {
                /* split string by spaces and take first part as currentDate */
                LocalDate currentDate = LocalDate.parse(data[i].split(" ", 0)[0], formatter);
                /* split string data[i] by spaces and take second part as name of employee */
                String nameUser = data[i].split(" ", 0)[1];
                String workHours = data[i].split(" ", 0)[2];
                String incomePerHour = data[i].split(" ", 0)[3];
                if (currentDate.isAfter(start) && currentDate.isBefore(end)
                        && nameUser.equals(name)) {
                    salary += Integer.parseInt(workHours) * Integer.parseInt(incomePerHour);
                } else if (currentDate.isEqual(start) && nameUser.equals(name)
                        || currentDate.isEqual(end) && nameUser.equals(name)) {
                    salary += Integer.parseInt(workHours) * Integer.parseInt(incomePerHour);
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}
