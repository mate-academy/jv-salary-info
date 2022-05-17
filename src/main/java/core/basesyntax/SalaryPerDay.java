package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryPerDay {
    private LocalDate date;
    private String name;
    private int hours;
    private int rate;
    private int cost;
    private final DateTimeFormatter dateFormatter;

    {
        dateFormatter = DateTimeFormatter.ofPattern(SalaryInfo.DATE_FORMAT);
    }

    public SalaryPerDay(String data)
            throws DateTimeParseException, NumberFormatException, SalaryPerDayException {
        if (data == null) {
            throw new SalaryPerDayException("Input data is null");
        }

        String[] parts = data.split(" ");
        if (parts.length != 4) {
            throw new SalaryPerDayException("Input data is wrong format. Split error");
        }
        setDate(parts[0]);
        setName(parts[1]);
        setHours(parts[2]);
        setRate(parts[3]);
    }

    public void setDate(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date, dateFormatter);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) throws SalaryPerDayException {
        if (name == null || name.isEmpty()) {
            throw new SalaryPerDayException("Input name is null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHours(String hours) throws NumberFormatException {
        this.hours = Integer.parseInt(hours);
        calculateCost();
    }

    public void setRate(String rate) throws NumberFormatException {
        this.rate = Integer.parseInt(rate);
        calculateCost();
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("SalaryPerDay{date=%s, name=%s, hours=%d, rate=%d, cost=%d",
                dateFormatter.format(date), name, hours, rate, cost);
    }

    private void calculateCost() {
        cost = hours * rate;
    }
}
