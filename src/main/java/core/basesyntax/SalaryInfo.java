package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int RATE_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateBegin = null;
        try {
            dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Date is incorrect", dateFrom, e.getErrorIndex());
        }
        LocalDate dateEnd = null;
        try {
            dateEnd = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Date is incorrect", dateTo, e.getErrorIndex());
        }
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] employeeInfo = record.split(" ");
            LocalDate currentDate = null;
            try {
                currentDate = LocalDate.parse(employeeInfo[DATE_POSITION], FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("Current date is incorrect",
                        employeeInfo[DATE_POSITION], e.getErrorIndex());
            }
            String name = employeeInfo[NAME_POSITION];
            int workHours = 0;
            try {
                workHours = Integer.parseInt(employeeInfo[HOURS_POSITION]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Hours is wrong");
            }
            int rate = 0;
            try {
                rate = Integer.parseInt(employeeInfo[RATE_POSITION]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Rate is wrong");
            }
            int salary = workHours * rate;
            for (int i = 0; i < names.length; i++) {
                if (!currentDate.isBefore(dateBegin) && !currentDate.isAfter(dateEnd)
                        && names[i].equals(name)) {
                    salaries[i] += salary;
                    break;
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        SalaryInfo info = new SalaryInfo();
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "26.04.2019 Andrew 3 150",
                "26.04.2019 Kate 9 100"
        };
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        System.out.println(info.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
