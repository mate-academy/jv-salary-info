package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_PART = 0;
    private static final int NAME_PART = 1;
    private static final int HOURS_PART = 2;
    private static final int INCOMING_PER_PART = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> mapOfName = putNameInMap(names);
        Date startDate = getStartDate(dateFrom);
        Date endDate = getEndDate(dateTo);
        putSalaryInMap(mapOfName, data, startDate, endDate);
        return getFormatedString(mapOfName, names, dateFrom, dateTo);
    }

    private Map<String, Integer> putNameInMap(String[] names) {
        Map<String, Integer> salaries = new HashMap<>();
        for (String name : names) {
            salaries.put(name, 0);
        }
        return salaries;
    }

    private Date getStartDate(String dateFrom) {
        Date startDate;
        try {
            startDate = DATE_FORMAT.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
        return startDate;
    }

    private Date getEndDate(String dateTo) {
        Date endDate;
        try {
            endDate = DATE_FORMAT.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
        return endDate;
    }

    private void putSalaryInMap(Map<String,Integer> mapOfName,
                                String[] data,
                                Date startDate, Date endDate) {

        Date entryDate;
        for (String entry : data) {
            String[] entryParts = entry.split(" ");
            String dateStr = entryParts[DATE_PART];
            String employeeName = entryParts[NAME_PART];
            int hoursWorked = Integer.parseInt(entryParts[HOURS_PART]);
            int incomePerHour = Integer.parseInt(entryParts[INCOMING_PER_PART]);

            try {
                entryDate = DATE_FORMAT.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid entry date format", e);
            }
            putSalary(mapOfName, entryDate, startDate, endDate,
                    employeeName, hoursWorked, incomePerHour);
        }
    }

    private void putSalary(Map<String,Integer> mapOfName,
                           Date entryDate, Date startDate,
                           Date endDate, String employeeName,
                           int hoursWorked, int incomePerHour) {

        if (!entryDate.before(startDate)
                && !entryDate.after(endDate)
                && mapOfName.containsKey(employeeName)) {
            int currentSalary = mapOfName.get(employeeName);
            currentSalary += hoursWorked * incomePerHour;
            mapOfName.put(employeeName, currentSalary);
        }
    }

    private String getFormatedString(Map<String, Integer> mapOfName,
                                     String[] names, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(mapOfName.get(name))
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
