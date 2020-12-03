package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        ArrayList<Person> people = new ArrayList<>();
        for (String name : names) {
            Person anonymous = new Person();
            anonymous.setName(name);
            people.add(anonymous);
        }
        for (String info : data) {
            String[] salary = info.split(" ");
            String date = salary[0];
            String name = salary[1];
            String hours = salary[2];
            String moneyPerHour = salary[3];
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (localDate.isAfter(from) && localDate.isBefore(to)
                    || localDate.equals(from) || localDate.equals(to)) {
                for (Person person : people) {
                    if (name.equals(person.getName())) {
                        person.addSalary(Integer.parseInt(hours)
                                * Integer.parseInt(moneyPerHour));
                    }
                }
            }
        }
        String reportStart = "Report for period " + dateFrom
                + " - " + dateTo;
        for (Person person : people) {
            reportStart += person.toString();
        }
        return reportStart;
    }
}
