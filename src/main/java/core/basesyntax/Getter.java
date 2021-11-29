package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Getter {
    public static final int DATE = 0;
    public static final int NAME = 1;
    public static final int HOURS_WORKED = 2;
    public static final int RATE = 3;

    public LocalDate getDate(String name) {
        String[] dataArray = name.split(" ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dataArray[DATE], format);
        return date;
    }

    public String getName(String name) {
        String[] dataArray = name.split(" ");
        return dataArray[NAME];
    }

    public int getHours(String name) {
        String[] dataArray = name.split(" ");
        int hours = Integer.parseInt(dataArray[HOURS_WORKED]);
        return hours;
    }

    public int getRate(String name) {
        String[] dataArray = name.split(" ");
        int rate = Integer.parseInt(dataArray[RATE]);
        return rate;
    }

    public int getSalary(String name) {
        String[] dataArray = name.split(" ");
        int salary = Integer.parseInt(dataArray[RATE]) * Integer.parseInt(dataArray[HOURS_WORKED]);
        return salary;
    }
}
