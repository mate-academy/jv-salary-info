package core.basesyntax;

import java.time.LocalDate;

public class Employe {
    private LocalDate date;
    private String name;
    private int hours;
    private int salary;

    public Employe(LocalDate date, String name, int hours, int salary) {
        this.date = date;
        this.name = name;
        this.hours = hours;
        this.salary = salary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object != this.getClass()) {
            return false;
        }
        Employe employe = (Employe) object;
        return (date == employe.getDate())
                && ((name == employe.getName() || (name != null && name.equals(employe.getName())))
                && (hours == employe.getHours() && salary == employe.getSalary()));
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (hours >> 16);
        result = prime * result + (salary >> 32);
        return result;
    }

    @Override
    public String toString() {
        return "Show Employe ["
                + "date - " + date
                + "] [name - " + name
                + "] [hours - " + hours
                + "] [salary - " + salary
                + "] END";
    }
}
