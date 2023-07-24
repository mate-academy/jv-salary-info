package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);

        for (int i = 0; i < names.length; i++) {

            String employeeName = names[i];
            int employeeTotalSalary = 0;

            for (int j = 0; j < data.length; j++) {
                String[] recordColumns = data[j].split(" ");
                LocalDate salaryDate = parseDate(recordColumns[0]);
                int hours = Integer.parseInt(recordColumns[2]);
                int amount = Integer.parseInt(recordColumns[3]);

                if (employeeName.equals(recordColumns[1])
                        && isDateInRange(startDate, endDate, salaryDate)) {
                    employeeTotalSalary += hours * amount;
                }
            }

            stringBuilder.append(System.lineSeparator())
                    .append(employeeName).append(" - ").append(employeeTotalSalary);
        }

        return stringBuilder.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.from(formatter.parse(date));
    }

    private boolean isDateInRange(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck) {
        return (dateToCheck.isAfter(startDate) && dateToCheck.isBefore(endDate))
                || dateToCheck.isEqual(startDate) || dateToCheck.isEqual(endDate);
    }
}
