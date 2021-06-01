package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String originalData;
    private LocalDate date;
    private String userName;
    private int countDays;
    private double salary;

    public Record(String data) {
        this.originalData = data;
        createFields();
    }

    private void createFields() {
        if (originalData != null) {
            String[] inputData = originalData.split(" ");
            this.date = LocalDate.parse(inputData[0], formatter);
            this.userName = inputData[1];
            this.countDays = Integer.parseInt(inputData[2]);
            this.salary = Double.parseDouble(inputData[3]);
        }
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
