package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate date;
    private String userName;
    private int countDays;
    private int salary;

    public Record(String data) {
        initFields(data);
    }

    private void initFields(String originalData) {
        String[] inputData = originalData.split(" ");
        this.date = LocalDate.parse(inputData[0], formatter);
        this.userName = inputData[1];
        this.countDays = Integer.parseInt(inputData[2]);
        this.salary = Integer.parseInt(inputData[3]);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
