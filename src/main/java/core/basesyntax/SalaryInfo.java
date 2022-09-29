package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final String DATE_FORM = "d.M.yyyy";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int COUNT_SALARY_INDEX = 2;
    public static final int SALARY_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORM);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(dateFrom, dateTimeFormatter);
            to = LocalDate.parse(dateTo, dateTimeFormatter);
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
        StringBuilder st = new StringBuilder("Report for period ");
        st.append(dateFrom).append(" - ").append(dateTo);
        for (String employer : names) {
            st.append(System.lineSeparator()).append(employer).append(" - ");
            int totalSalary = getSumSalary(employer, data, from, to);
            st.append(totalSalary);
        }
        return st.toString();
    }

    private int getSumSalary(String name, String[] date, LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        for (String employerDate : date) {
            String[] lineDateArray = employerDate.split(" ");
            if (!name.equals(lineDateArray[NAME_INDEX])) {
                continue;
            }
            LocalDate dateFromArray = LocalDate.parse(lineDateArray[DATE_INDEX], dateTimeFormatter);
            if (dateFromArray.isBefore(dateFrom) || dateFromArray.isAfter(dateTo)) {
                continue;
            }
            int count = Integer.parseInt(lineDateArray[COUNT_SALARY_INDEX]);
            int cash = Integer.parseInt(lineDateArray[SALARY_INDEX]);
            salary += count * cash;
        }
        return salary;
    }
}
