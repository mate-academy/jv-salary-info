package core.basesyntax;

import static core.basesyntax.DateUtils.parseDate;

import java.time.LocalDate;

public class DataRecord {
    private final String name;
    private final LocalDate date;
    private final int hours;
    private final int rate;

    public DataRecord(String row) {
        String[] temp = row.split(" ");
        this.date = parseDate(temp[0]);
        this.name = temp[1];
        this.hours = Integer.parseInt(temp[2]);
        this.rate = Integer.parseInt(temp[3]);
    }

    public int getHours() {
        return hours;
    }

    public int getRate() {
        return rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
