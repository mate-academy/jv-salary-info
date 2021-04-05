package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder employeeSalary = new StringBuilder();
        employeeSalary.append("Report for period ").append(dateTo).append("\n");
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String information1 : data) {
                String[] information = information1.split(" ");
                String takeDate = information[0];
                if (information1.contains(name)) {
                    LocalDate dateEmployee = LocalDate.parse(takeDate, FORMATTER);
                    if (dateEmployee.compareTo(dateStart) >= 0
                            && dateEmployee.compareTo(dateFinish) <= 0) {
                        String timeEmployee = information[2];
                        String priceForOneHour = information[3];
                        totalSalary = totalSalary + Integer.parseInt(timeEmployee)
                                * Integer.parseInt(priceForOneHour);

                    }
                }
            }
            employeeSalary.append(name).append("-").append(totalSalary).append("\n");
        }
        return employeeSalary.toString().trim();

    }
}
