package core.basesyntax;

public class SalaryCalculator {
    public int daySalary(int hours, int pay) {
        if (hours == 0 || pay == 0) {
            return 0;
        }
        return hours * pay;
    }
}
