package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> employeesSalaries = getEmployeesSalaries(data, dateFrom, dateTo);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + "\n");
        for (String name : names) {
            int totalSalary = employeesSalaries.getOrDefault(name,0);
            result.append(name).append(" - ").append(totalSalary);
            if (!name.equals(names[names.length - 1])) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    private Map<String,Integer> getEmployeesSalaries(String[] data,
                                                     String dateFrom, String dateTo) {
        Map<String,Integer> localMap = new HashMap<>();
        try {
            Date fromDate = simpleDateFormat.parse(dateFrom);
            Date toDate = simpleDateFormat.parse(dateTo);
            for (String entry : data) {
                String[] parts = entry.split(" ");
                String dateString = parts[0];
                String name = parts[1];
                int workingHours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);
                Date date = simpleDateFormat.parse(dateString);
                if (date.after(fromDate) && date.before(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    int totalIncome = workingHours * incomePerHour;
                    localMap.put(name, localMap.getOrDefault(name,0)
                            + totalIncome);
                }
            }
        } catch (ParseException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return localMap;
    }
}
