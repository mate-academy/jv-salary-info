package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        // Sample data
        String[] names = {"John", "Alice"};
        String[] data = {
                "01.01.2025 John 20 8",
                "03.01.2025 Alice 15 10",
                "02.01.2025 John 20 4",
                "2001.01.2025 Alice 15 5"
        };
        String dateFrom = "01.01.2025";
        String dateTo = "01.02.2025";

        SalaryInfo salaryInfo = new SalaryInfo();
        String result = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);

        System.out.println(result);
    }
}


