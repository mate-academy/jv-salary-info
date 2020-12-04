package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMAT);
        LocalDate to = LocalDate.parse(dateTo, FORMAT);
        StringBuilder infoAboutPeople = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int additionalSalary = 0;
            for (String info : data) {
                String[] salary = info.split(" ");
                String date = salary[0];
                String personName = salary[1];
                String hours = salary[2];
                String moneyPerHour = salary[3];
                LocalDate localDate = LocalDate.parse(date, FORMAT);
                if (name.equals(personName)
                        && (localDate.isAfter(from) && localDate.isBefore(to)
                        || localDate.equals(from) || localDate.equals(to))) {
                    additionalSalary += Integer.parseInt(hours)
                            * Integer.parseInt(moneyPerHour);
                }
            }
            infoAboutPeople.append("\n").append(name).append(" - ")
                    .append(additionalSalary);
        }
        return infoAboutPeople.toString();
    }
}
