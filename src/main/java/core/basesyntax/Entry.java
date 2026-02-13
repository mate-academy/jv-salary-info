package core.basesyntax;

import java.time.LocalDate;

public class Entry {
    private static final DateUtils utils = new DateUtils();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    private final LocalDate date;
    private final String name;
    private final int hours;
    private final int salary;

    public Entry(String line) {
        String[] parts = line.split(" ");
        this.date = utils.convertString(parts[DATE_INDEX]);
        this.name = parts[NAME_INDEX];
        this.hours = Integer.parseInt(parts[HOURS_INDEX]);
        this.salary = Integer.parseInt(parts[SALARY_INDEX]);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getSalary() {
        return salary;
    }
}
