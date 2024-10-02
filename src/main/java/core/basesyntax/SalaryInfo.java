package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Формат дат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Перетворення вхідних дат
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        // Створюємо масив для зберігання заробітної плати кожного працівника
        int[] salaries = new int[names.length];
        // Обробка кожного запису у масиві data
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);
            // Перевіряємо, чи потрапляє дата у межі
            if (!workDate.isBefore(from) && !workDate.isAfter(to)) {
                // Шукаємо індекс працівника в масиві names
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        // Додаємо зароблені гроші до відповідного працівника
                        salaries[i] += hoursWorked * hourlyRate;
                    }
                }
            }
        }
        // Формування звіту
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        // Додаємо інформацію про зарплату кожного працівника
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ")
                    .append(salaries[i]).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
