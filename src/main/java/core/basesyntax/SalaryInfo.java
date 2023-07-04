package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int WORKED_INDEX = 2;
    public static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        if (data.length < 1 || names.length < 1) {
            return null;
        }
        StringBuilder resultString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        Map<String, Integer> salaryMap = new LinkedHashMap<>();
        for (int i = 0; i < names.length; ++i) {
            salaryMap.put(names[i], 0);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            for (String employeeData : data) {
                String[] tokensData = employeeData.split(" ");
                String dateStr = tokensData[DATE_INDEX];

                String nameEmployer = tokensData[NAME_INDEX];
                int hoursWorked = Integer.parseInt(tokensData[WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(tokensData[RATE_INDEX]);
                Date currentDate = dateFormat.parse(dateStr);

                if (currentDate.compareTo(fromDate) >= 0 && currentDate.compareTo(toDate) <= 0) {
                    for (String employeeName : names) {
                        if (nameEmployer.equals(employeeName)) {
                            int salary = hoursWorked * hourlyRate;
                            salaryMap.put(nameEmployer, (salaryMap.get(nameEmployer) + salary));
                            break;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (String employeeName : salaryMap.keySet()) {
            resultString.append(System.lineSeparator())
                    .append(employeeName).append(" - ").append(salaryMap.get(employeeName));
        }

        return resultString.toString();
    }
}
