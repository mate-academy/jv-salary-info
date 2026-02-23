package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        String[] names = {"John", "dawid"};
        String[] data = {"11.11.2000 John 4 50",
                "12.11.2000 dawid 4 100",
                "12.11.2000 dawid 4 100"};
        String dateFrom = "01.11.2000 ";
        String dateTo = "30.11.2000  ";
        System.out.println(salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
