import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0); // Inicjalizujemy zarobki na 0
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], DATE_FORMATTER);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (!salaryMap.containsKey(employeeName)) {
                continue; // Pomijamy osoby spoza listy
            }

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                int salary = hoursWorked * hourlyRate;
                salaryMap.put(employeeName, salaryMap.get(employeeName) + salary);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            report.append(name).append(" - ").append(salaryMap.get(name)).append("\n");
        }

        return report.toString();
    }
}
