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
        } catch (NullPointerException e) {
            throw new NullPointerException("Date should be not NULL!");
        } catch (DateTimeException e) {
            throw new DateTimeException("Date should be in \"dd.MM.yyyy\" format");
        }

        // data and names not Null
        if (data == null || names == null) {
            throw new NullPointerException("Names and data arrays on input must be not null!");
        }

        // creating array of String[] for our input data
        String [] employeeData;

        int i = 0;
        employeeData = new String[data.length];
        for (String dataRecord : data) {
            employeeData[i] = dataRecord;
            i++;
        }

        // look for each name in names
        for (String name : names) {
            int salary = 0;
            for (String employee : employeeData) {
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
                } catch (NullPointerException e) {
                    throw new NullPointerException("Date should be not NULL!");
                } catch (DateTimeException e) {
                    throw new DateTimeException("Date should be in \"dd.MM.yyyy\" format");
                }

            }
            resultInfo.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return resultInfo.toString().trim();
    }

    public LocalDate getParsedDateTo() {
        return parsedDateTo;
    }

    public void setParsedDateTo(LocalDate parsedDateTo) {
        this.parsedDateTo = parsedDateTo;
    }

    public LocalDate getParsedDateFrom() {
        return parsedDateFrom;
    }

    public void setParsedDateFrom(LocalDate parsedDateTo) {
        this.parsedDateFrom = parsedDateTo;
    }
}
