package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private Map<String, Integer> employees = new HashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        for (String name : names) {
            employees.put(name, 0);
        }
        StringBuilder res = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String employeeData : data) {
            String[] details = employeeData.split(" ");
            Date date = getDate(details[0]);
            String nameOfEmployee = details[1];
            int dailySalary;
            if (date.compareTo(getDate(dateFrom)) >= 0 && date.compareTo(getDate(dateTo)) <= 0) {
                dailySalary = Integer.parseInt(details[2]) * Integer.parseInt(details[3]);
                employees.put(nameOfEmployee, employees.get(nameOfEmployee) + dailySalary);
            }

        }
        for (String name : names) {
            res.append("\r\n").append(name).append(" - ").append(employees.get(name));
        }
        return res.toString();
    }

    private Date getDate(String input) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
