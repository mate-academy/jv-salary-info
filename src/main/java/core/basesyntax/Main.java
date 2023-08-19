package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "14.07.2019 John 8 100",
                "15.07.2019 Andrew 10 120",
                "16.07.2019 Kate 7 200",
                // ... другие записи данных ...
        };
        String dateFrom = "14.07.2019";
        String dateTo = "10.08.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        String report = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(report);
    }
}
