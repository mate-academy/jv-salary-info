package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();

        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2022 John 4 50",
                "05.04.2022 Andrew 3 200",
                "10.04.2022 John 7 100",
                "22.04.2022 Kate 9 100",
                "25.06.2022 John 11 50",
                "26.04.2022 Andrew 3 150",
                "13.02.2022 John 7 100",
                "26.04.2022 Kate 9 100"
        };

        String dateFrom = "01.04.2022";
        String dateTo = "30.04.2022";

        String result = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(result);
    }
}
