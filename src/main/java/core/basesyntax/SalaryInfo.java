package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultListBuilder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());

        try {
            Date firstDate = dateFormatter.parse(dateFrom);
            Date secondDate = dateFormatter.parse(dateTo);

            for (String name : names) {
                int totalSalary = calculateTotalSalary(data, firstDate, secondDate, name);
                resultListBuilder
                        .append(name)
                        .append(" - ")
                        .append(totalSalary)
                        .append(System.lineSeparator());
            }

        } catch (ParseException e) {
            System.out.println("Error occurred while parsing date: " + e.getMessage());
        }

        String result = resultListBuilder.toString();
        return removeTrailingLineSeparator(result);
    }

    private int calculateTotalSalary(String[] data, Date firstDate, Date secondDate, String name)
            throws ParseException {
        int totalSalary = 0;
        for (String element : data) {
            Date workDate = dateFormatter.parse(element.split(" ")[0]);
            String employeeName = element.split(" ")[1];
            int workingHours = Integer.parseInt(element.split(" ")[2]);
            int moneyPerHourEarned = Integer.parseInt(element.split(" ")[3]);

            if ((workDate.equals(firstDate) || (workDate.after(firstDate)
                    && workDate.before(secondDate) || workDate.equals(secondDate)))
                    && name.equals(employeeName)) {
                totalSalary += moneyPerHourEarned * workingHours;
            }
        }
        return totalSalary;
    }

    private String removeTrailingLineSeparator(String result) {
        if (result.isEmpty()) {
            return result;
        }
        return result.substring(0, result.length() - System.lineSeparator().length());
    }
}
