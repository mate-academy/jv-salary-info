package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate parsedDateFrom;
    private LocalDate parsedDateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultInfo = new StringBuilder();

        // reading and parsing Strings dateFrom, dateTo
        try {
            parsedDateFrom = LocalDate.parse(dateFrom, dateFormatter);
            parsedDateTo = LocalDate.parse(dateTo, dateFormatter);
            resultInfo.append("Report for period ");
            resultInfo.append(dateFrom).append(" - ").append(dateTo)
                    .append(System.lineSeparator());
        } catch (NullPointerException | DateTimeException e) {
            System.out.println("Date should be not NULL! and be in \"dd.MM.yyyy\" format");
            System.out.println(e.getMessage());
        }

        // data and names not Null
        if (data == null || names == null) {
            throw new NullPointerException("Names and data arrays on input must be not null!");
        }

        // look for each name in names
        for (String name : names) {
            int salary = 0;
            for (String employee : data) {
                // creating array of Strings by each record
                String [] employeeInfo = employee.split(" ");
                if (employeeInfo.length != 4) {
                    throw new IndexOutOfBoundsException("Wrong input data! "
                            + "data must be the array of Strings such format :"
                            + " 25.04.2019 John 60 50");
                }

                // if our date between input dates then take calculate salary
                try {
                    LocalDate parsedEmployeeDate = LocalDate.parse(employeeInfo[0], dateFormatter);
                    if (parsedEmployeeDate.isBefore(parsedDateTo.plusDays(1))
                            && parsedEmployeeDate.isAfter(parsedDateFrom.minusDays(1))) {
                        if (name.equals(employeeInfo[1])) {
                            salary += Integer.parseInt(employeeInfo[2])
                                    * Integer.parseInt(employeeInfo[3]);
                        }
                    }
                } catch (NullPointerException | DateTimeException e) {
                    System.out.println("Date should be not NULL! and be in \"dd.MM.yyyy\" format");
                    System.out.println(e.getMessage());
                }

            }
            resultInfo.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return resultInfo.toString().trim();
    }
}

