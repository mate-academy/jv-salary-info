package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int totalSalary = calculateTotalSalaryForEmployee(name, data, fromDate, toDate);
                result.append("\n").append(name).append(" - ").append(totalSalary);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Something went wrong");
        }
        return result.toString();
    }

    private int calculateTotalSalaryForEmployee(String employeeName, String[] data,
                                                Date fromDate, Date toDate) {
        int totalSalary = 0;

        for (String inception : data) {
            String[] parts = inception.split(" ");
            String entryDateOfEmployee = parts[0];
            String entryName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int income = Integer.parseInt(parts[3]);
            try {
                Date entryDate = new SimpleDateFormat("dd.MM.yyyy").parse(entryDateOfEmployee);

                if (entryName.equals(employeeName) && entryDate.compareTo(fromDate) >= 0
                        && entryDate.compareTo(toDate) <= 0) {
                    totalSalary += (hours * income);
                }
            } catch (ParseException e) {
                throw new RuntimeException("Something went wrong");
            }
        }

        return totalSalary;
    }
}
