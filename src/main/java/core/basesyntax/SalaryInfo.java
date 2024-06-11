package core.basesyntax;

import java.time.format.DateTimeParseException;

public class SalaryInfo extends DataComparison {

    public String getSalaryInfo(String[] names, String[] dates, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        int[] employeeSalary = new int[names.length];

        try {
            for (String date : dates) {
                String[] record = date.split("\\s+");
                String recordDate = record[0];
                String recordName = record[1];
                int totalHours = Integer.parseInt(record[2]);
                int salaryPerHour = Integer.parseInt(record[3]);

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(recordName)
                            && isDateInRange(dateFrom, dateTo, recordDate)) {
                        employeeSalary[i] += totalHours * salaryPerHour;
                    }
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Invalid date format" + e);

        }

        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ").append(employeeSalary[i])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
