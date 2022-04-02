package core.basesyntax.model;

public class Employee extends Person {
    private String[] workday;
    private String[] hoursWork;
    private String[] salaryPerHour;
    private int moneyEarnedForCertainPeriod;

    public Employee(String name, int size, int moneyEarnedForCertainPeriod) {
        super(name);
        this.workday = new String[size];
        this.hoursWork = new String[size];
        this.salaryPerHour = new String[size];
        this.moneyEarnedForCertainPeriod = moneyEarnedForCertainPeriod;
    }

    public String[] getWorkday() {
        return workday;
    }

    public void setWorkday(String workday, int index) {
        this.workday[index] = workday;
    }

    public String[] getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(String hoursWork, int index) {
        this.hoursWork[index] = hoursWork;
    }

    public String[] getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(String salaryPerHour, int index) {
        this.salaryPerHour[index] = salaryPerHour;
    }

    public int getMoneyEarnedForCertainPeriod() {
        return moneyEarnedForCertainPeriod;
    }

    public void setMoneyEarnedForCertainPeriod(int moneyEarnedForCertainPeriod) {
        this.moneyEarnedForCertainPeriod = moneyEarnedForCertainPeriod;
    }
}
