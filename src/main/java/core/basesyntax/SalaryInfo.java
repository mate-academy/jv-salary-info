package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            LocalDate calendarFrom = convertDataToLocalDate(dateFrom);
            LocalDate calendarTo = convertDataToLocalDate(dateTo);

            StringBuilder employeeSalary = new StringBuilder();
            employeeSalary.append("Report for period " + dateFrom + " - " + dateTo);

            for (String name: names) {

                int calcSalary = 0;

                for (String eachLine : data) {

                    String[] parsedEchLine = eachLine.split(" ");

                    if (parsedEchLine[1].equals(name)) {

                        LocalDate currentDate = convertDataToLocalDate(parsedEchLine[0]);

                        if ((currentDate.isAfter(calendarFrom)
                                || currentDate.isEqual(calendarFrom))
                                && (currentDate.isBefore(calendarTo)
                                || currentDate.isEqual(calendarTo))) {

                            calcSalary += Integer.valueOf(parsedEchLine[2])
                                    * Integer.valueOf(parsedEchLine[3]);

                        }
                    }
                }

                employeeSalary.append("\n" + name + " - " + calcSalary);
            }

            return employeeSalary.toString();

        } catch (ParseException e) {
            System.out.println("Wrong format of date");
            throw new RuntimeException();
        }
    }

    private LocalDate convertDataToLocalDate(String date) throws ParseException {

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate localDate = LocalDate.parse(date, sdf);

        return localDate;

    }
}

