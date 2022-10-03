package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATE_FORM = "d.M.yyyy";
    private static final String SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int COUNT_SALARY_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern(DATE_FORM);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(dateFrom, dateTimeForm);
            to = LocalDate.parse(dateTo, dateTimeForm);
            if (from.isAfter(to)) {
                throw new DateTimeErrorPosition("Date from is bigger than date to");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Date format error. Needed - " + DATE_FORM);
            return "";
        } catch (DateTimeErrorPosition e1) {
            System.out.println(e1.getMessage());
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(SEPARATOR).append(dateTo);
        for (String employer : names) {
            stringBuilder.append(System.lineSeparator()).append(employer).append(" - ");
            int totalSalary = getSumSalary(employer, data, from, to);
            stringBuilder.append(totalSalary);
        }
        return stringBuilder.toString();
    }

    private int getSumSalary(String name, String[] date, LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        for (String employerDate : date) {
            String[] lineDateArray = employerDate.split(" ");
            LocalDate dateFromArray = LocalDate.parse(lineDateArray[DATE_INDEX], dateTimeForm);
            if (!name.equals(lineDateArray[NAME_INDEX]) || dateFromArray.isBefore(dateFrom)
                    || dateFromArray.isAfter(dateTo)) {
                continue;
            }
            int count = Integer.parseInt(lineDateArray[COUNT_SALARY_INDEX]);
            int cash = Integer.parseInt(lineDateArray[SALARY_INDEX]);
            salary += count * cash;
        }
        return salary;
    }
}
