package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String REPORT = "Report for period ";
    private static final String EXCEPTION_MASSAGE = "The input data is incorrect";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Employee[] employees;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        try {
            dateStart = parseDate(dateFrom);
            dateEnd = parseDate(dateTo);
            for (String datum : data) {
                calculateSalary(datum);
            }
        } catch (ParsingDateException parsingDateException) {
            return parsingDateException.getMessage();
        }
        return makeReport();
    }

    private void calculateSalary(String data) throws ParsingDateException {
        String[] record = data.split(" ");
        LocalDate workingDay;
        try {
            workingDay = parseDate(record[0]);
            if (!workingDay.isBefore(dateStart) && !workingDay.isAfter(dateEnd)) {
                for (Employee employee : employees) {
                    if (record[1].equals(employee.getName())) {
                        employee.setSalary(Integer.parseInt(record[2])
                                * Integer.parseInt(record[3]));
                    }
                }
            }
        } catch (Exception e) {
            throw new ParsingDateException(EXCEPTION_MASSAGE);
        }
    }

    private LocalDate parseDate(String date) throws ParsingDateException {
        try {
            String[] partsOfDate = date.split("\\.");
            int year = Integer.parseInt(partsOfDate[2]);
            int month = Integer.parseInt(partsOfDate[1]);
            int day = Integer.parseInt(partsOfDate[0]);
            return LocalDate.of(year,month,day);
        } catch (Exception e) {
            throw new ParsingDateException(EXCEPTION_MASSAGE);
        }
    }

    private String makeReport() {
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT);
        builder.append(dateStart.format(formatter)).append(" - ")
                .append(dateEnd.format(formatter));
        for (Employee employee : employees) {
            builder.append(System.lineSeparator())
                    .append(employee.getName()).append(" - ")
                    .append(employee.getSalary());
        }
        return builder.toString();
    }
}
