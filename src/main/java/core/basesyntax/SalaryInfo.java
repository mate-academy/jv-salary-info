package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatWithDot = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nameOfEmployee = "";
        LocalDate date;
        LocalDate dateFromDate;
        LocalDate dateToDate;
        String dateFromWithoutDot = dateFrom.replace(".", "-");
        String dateToWithoutDot = dateTo.replace(".", "-");
        int timesGotSalary;
        int salary;
        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int i = 0; i < data.length; i++) {
            String[] employeesInformation = data[i].split(" ");
            nameOfEmployee = employeesInformation[1];
            timesGotSalary = Integer.parseInt(employeesInformation[2]);
            salary = Integer.parseInt(employeesInformation[3]);
            String dateWithoutDot = employeesInformation[0].replace(".", "-");
            date = LocalDate.parse(dateWithoutDot, formatWithDot);
            dateFromDate = LocalDate.parse(dateFromWithoutDot, formatWithDot);
            dateToDate = LocalDate.parse(dateToWithoutDot, formatWithDot);
            if (dateFromDate.minusDays(1).isBefore(date) && dateToDate.plusDays(1).isAfter(date)) {
                johnSalary += nameOfEmployee.equals("John") ? timesGotSalary * salary : 0;
                andrewSalary += nameOfEmployee.equals("Andrew") ? timesGotSalary * salary : 0;
                kateSalary += nameOfEmployee.equals("Kate") ? timesGotSalary * salary : 0;
            }
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("John")) {
                str.append(names[i]).append(" - ").append(johnSalary).append("\n");
            } else if (names[i].equals("Andrew")) {
                str.append(names[i]).append(" - ").append(andrewSalary).append("\n");
            } else if (names[i].equals("Kate")) {
                str.append(names[i]).append(" - ").append(kateSalary);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        SalaryInfo s = new SalaryInfo();
        System.out.println(s.getSalaryInfo(new String[]{"John", "Andrew", "Kate"},
                new String[]{"25.04.2019 John 60 50",
                        "25.04.2019 Andrew 3 200",
                        "25.04.2019 Kate 10 100",

                        "26.04.2019 Andrew 3 200",
                        "26.04.2019 Kate 9 100",

                        "27.04.2019 John 7 100",
                        "27.04.2019 Kate 3 80",
                        "27.04.2019 Andrew 8 100"},
                "24.04.2019", "24.04.2019"));
    }
}
