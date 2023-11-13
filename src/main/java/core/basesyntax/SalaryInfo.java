package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static String getSalaryInfo(
            String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(REPORT_HEADER).append(dateFrom.trim())
                .append(SEPARATOR).append(dateTo.trim()).append(LINE_SEPARATOR);

        // Create Employee objects
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }

        // Iterate through the data array and create WorkRecord objects
        for (String recordString : data) {
            WorkRecord record = new WorkRecord(recordString);
            LocalDate workDate = record.getDate();

            // Check if the work date is within the specified range and update earnings
            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (Employee employee : employees) {
                    if (employee.getName().equals(record.getEmployeeName())) {
                        employee.addEarnings(record.calculateEarnings());
                    }
                }
            }
        }

        for (Employee employee : employees) {
            reportBuilder.append(employee.getName()).append(" - ")
                    .append(employee.getTotalEarnings()).append(LINE_SEPARATOR);
        }
        return reportBuilder.toString().trim();
    }

    public static void main(String[] args) {
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };

        String report = SalaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(report);
    }
}
