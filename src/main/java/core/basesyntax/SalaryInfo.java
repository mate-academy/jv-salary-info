package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < salary.length; i++) {
            salary[i] = calculateSalary(names[i], data, startDate, endDate);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            builder.append("\n").append(names[i]).append(" - ").append(salary[i]);
        }

        return builder.toString();
    }

    public int calculateSalary(String name, String[] data, LocalDate startDate, LocalDate endDate) {
        int totalPayment = 0;
        for (int i = 0; i < data.length; i++) {
            String[] dateInformation = data[i].split(" ");
            LocalDate currentDate = LocalDate.parse(dateInformation[0], DATE_FORMAT);
            String employeeName = dateInformation[1];
            int workedHours = Integer.parseInt(dateInformation[2]);
            int paymentPerHour = Integer.parseInt(dateInformation[3]);

            if (name.equals(employeeName) && compareDate(startDate, endDate, currentDate)) {
                totalPayment += workedHours * paymentPerHour;
            }
        }
        return totalPayment;
    }

    public boolean compareDate(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate)
                || currentDate.equals(startDate) || currentDate.equals(endDate);
    }
}
