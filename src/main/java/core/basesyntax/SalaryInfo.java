package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String separator = "\\s";
        Map<String, Integer> employeesSalary = new LinkedHashMap<>();
        Date startDate;
        Date endDate;

        try {
            startDate = FORMATTER.parse(dateFrom);
            endDate = FORMATTER.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (String name : names) {
            employeesSalary.put(name, 0);
        }

        for (String dataInfo : data) {
            Date date;
            String[] splitData = dataInfo.split(separator);
            String name = splitData[1];
            int hours = Integer.parseInt(splitData[2]);
            int incomePerHour = Integer.parseInt(splitData[3]);

            try {
                date = FORMATTER.parse(splitData[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                int salary = hours * incomePerHour;
                employeesSalary.computeIfPresent(name, (key, value) -> value + salary);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (Map.Entry<String, Integer> entry : employeesSalary.entrySet()) {
            stringBuilder.append(System.lineSeparator()).append(entry.getKey()).append(" - ");
            stringBuilder.append(entry.getValue());
        }

        return stringBuilder.toString();
    }
}
