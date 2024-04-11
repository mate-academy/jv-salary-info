package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        int[] totalSalaries = new int[names.length];

        StringBuilder report = new StringBuilder();
        try {
            LocalDate from = LocalDate.parse(dateFrom, formatter);
            LocalDate to = LocalDate.parse(dateTo, formatter);
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate date = LocalDate.parse(recordParts[0], formatter);
                String name = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                // Перевірка, чи дата запису входить у вказаний діапазон
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    // Знаходимо індекс імені працівника у масиві names
                    int index = -1;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            index = i;
                            break;
                        }
                    }

                    // Якщо ім'я знайдено, додаємо заробітну плату до відповідного індексу в масиві totalSalaries
                    if (index != -1) {
                        totalSalaries[index] += hoursWorked * hourlyRate;
                    }
                }
            }

            report.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());

            for (int i = 0; i < names.length; i++) {
                if (i == names.length - 1) {
                    report.append(names[i] + " - " + totalSalaries[i]);
                    break;
                }
                report.append(names[i] + " - " + totalSalaries[i] + System.lineSeparator());
            }

        } catch (DateTimeParseException e) {
            System.out.println("Помилка розпарсування дати: " + e.getMessage());
        }
        return report.toString();
    }
}
