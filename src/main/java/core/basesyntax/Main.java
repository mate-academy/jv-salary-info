package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        String[] names = new String[]{"John", "Andrew", "Kate"};
        String[] data = new String[]{
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",
                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",
                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"};
        String dateFrom = "25.04.2019";
        String dateTo = "25.04.2019";
        System.out.println(salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
