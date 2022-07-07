package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE = 0;
    static final int NAME = 1;
    static final int HOURS = 2;
    static final int INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromdate = getDate(dateFrom);
        LocalDate dateTodate = getDate(dateTo);
        StringBuilder salaryInfo = new StringBuilder();
        for (String name: names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                WorkingDay day = getInfoAboutWork(data[i]);
                String nameFromData = day.getName();
                int workinghours = day.getWorkingHours();
                int income = day.getIncome();
                LocalDate date = day.getWorkingDay();
                if (name.equals(nameFromData) && (date.isAfter(dateFromdate)
                        || date.equals(dateFromdate)) && (date.isBefore(dateTodate)
                        || date.equals(dateTodate))) {
                    salary = salary + workinghours * income;
                }
            }
            salaryInfo = salaryInfo.append(System.lineSeparator())
                            .append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + salaryInfo;
    }

    private WorkingDay getInfoAboutWork(String data) {
        String[] day = data.split(" ");
        LocalDate date = getDate(day[DATE]);
        String name = day[NAME];
        int hours = Integer.parseInt(day[HOURS]);
        int income = Integer.parseInt(day[INCOME]);

        return new WorkingDay(date,name,hours,income);
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, FORMATTER);

    }
}

