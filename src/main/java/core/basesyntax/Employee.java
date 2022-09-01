package core.basesyntax;

import java.time.LocalDate;

public class Employee {
    private LocalDate startDataWork;
    private String name;
    private int dayOfWork;
    private int salaryOur;

    public LocalDate getStartDataWork() {
        return startDataWork;
    }

    public void setStartDataWork(LocalDate startDataWork) {
        this.startDataWork = startDataWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayOfWork() {
        return dayOfWork;
    }

    public void setDayOfWork(int dayOfWork) {
        this.dayOfWork = dayOfWork;
    }

    public int getSalaryOur() {
        return salaryOur;
    }

    public void setSalaryOur(int salaryOur) {
        this.salaryOur = salaryOur;
    }
}
