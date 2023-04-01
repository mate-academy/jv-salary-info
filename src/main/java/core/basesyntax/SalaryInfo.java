package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int MONEY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        Date dateFromData = convertStringToDate(dateFrom);
        Date dateToData = convertStringToDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        for (Employee employee : employees) {
            for (String dataString : data) {
                String[] dataArray = dataString.split(" ");
                Date date = convertStringToDate(dataArray[DATE_POSITION]);
                if (employee.getName().equalsIgnoreCase(dataArray[NAME_POSITION])
                        && (date.after(dateFromData) || date.equals(dateFromData))
                        && (date.before(dateToData) || date.equals(dateToData))) {
                    employee.addEarnedMoney(Integer.parseInt(dataArray[HOURS_POSITION])
                            * Integer.parseInt(dataArray[MONEY_POSITION]));
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (Employee employee : employees) {
            stringBuilder.append("\n");
            stringBuilder.append(employee.getName()).append(" - ")
                    .append(employee.getEarnedMoney());
        }
        return stringBuilder.toString();
    }

    private Date convertStringToDate(String date) {
        Date dateConverted;
        try {
            dateConverted = SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Date can't be converted", e);
        }
        return dateConverted;
    }
}
