package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> salaries = new HashMap<>();

        for (String entry : data) {
            String[] parts = entry.split(" "); // Розділяємо рядок на частини
            LocalDate date = LocalDate.parse(parts[0], formatter); // Перетворюємо дату
            String name = parts[1]; // Ім'я працівника
            int hoursWorked = Integer.parseInt(parts[2]); // Кількість відпрацьованих годин
            int hourlyRate = Integer.parseInt(parts[3]); // Вартість години

            if ((date.isEqual(from) || date.isAfter(from)) && (date.isEqual(to)
                    || date.isBefore(to))) {
                salaries.put(name, salaries.getOrDefault(name, 0) + (hoursWorked * hourlyRate));
            }
        }

        // Формуємо результат
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salary = salaries.getOrDefault(name, 0);
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
