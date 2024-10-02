package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Формат дат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Перетворення вхідних дат
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        // Створюємо карту для зберігання загальної суми заробітку для кожного працівника
        Map<String, Integer> salaries = new HashMap<>();
        for (String name : names) {
            salaries.put(name, 0); // Ініціалізуємо зарплату нулем для кожного працівника
        }

        // Обробка кожного запису у масиві data
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            // Якщо дата знаходиться в межах періоду
            if (!workDate.isBefore(from) && !workDate.isAfter(to)) {
                if (salaries.containsKey(employeeName)) {
                    int currentSalary = salaries.get(employeeName);
                    int earned = hoursWorked * hourlyRate;
                    salaries.put(employeeName, currentSalary + earned);
                }
            }
        }

        // Формування звіту
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        // Додаємо інформацію про зарплату кожного працівника
        for (String name : names) {
            report.append(name).append(" - ").append(salaries.get(name)).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
