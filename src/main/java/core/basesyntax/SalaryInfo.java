package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int AMOUNT_PER_HOUR = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String[][] personsInfo = getData(data);
        Person[] persons = getNames(names);
        LocalDate theDateFrom = getDate(dateFrom);
        LocalDate theDateTo = getDate(dateTo);

        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < persons.length; i++) {
            for (int j = 0; j < personsInfo.length; j++) {
                LocalDate date = getDate(personsInfo[j][DATE]);
                if (!date.isBefore(theDateFrom) && !date.isAfter(theDateTo)
                        && persons[i].getName().equals(personsInfo[j][NAME])) {
                    persons[i].setSalary(persons[i].getSalary()
                            + getSum(personsInfo[j][HOURS], personsInfo[j][AMOUNT_PER_HOUR]));
                }
            }

        }

        for (Person person: persons) {
            builder.append(person.toString()).append(System.lineSeparator());
        }
        System.out.println(builder.toString().trim());
        return builder.toString().trim();
    }

    private int getSum(String hours, String income) {
        return Integer.parseInt(hours) * Integer.parseInt(income);
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    private String[][] getData(String[] data) {
        String[][] personsData = new String[data.length][4];
        for (int i = 0; i < data.length; i++) {
            personsData[i] = data[i].split(" ");
        }
        return personsData;
    }

    private Person[] getNames(String[] names) {
        Person[] persons = new Person[names.length];
        for (int i = 0; i < names.length; i++) {
            Person person = new Person(names[i]);
            persons[i] = person;
        }
        return persons;
    }
}
