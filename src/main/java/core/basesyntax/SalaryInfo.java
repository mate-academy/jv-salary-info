package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DaysInfo daysInfo = new DaysInfo();
        SalaryPerPerson salaryPerPerson = new SalaryPerPerson();
        Print print = new Print();

        String[] days = daysInfo.getDaysFromTheRange(data, dateFrom, dateTo);
        String[] money = salaryPerPerson.getSalaryPerPerson(names, days);

        return print.getPrint(money, dateFrom, dateTo);

    }
}
