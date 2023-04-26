package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[8];
        data[0] = "26.04.2019 John 4 50";
        data[1] = "05.04.2019 Andrew 3 200";
        data[2] = "10.04.2019 John 7 100";
        data[3] = "22.04.2019 Kate 9 100";
        data[4] = "25.06.2019 John 11 50";
        data[5] = "26.04.2019 Andrew 3 150";
        data[6] = "13.02.2019 John 7 100";
        data[7] = "26.04.2019 Kate 9 100";

        String[] names = {"John", "Andrew", "Kate"};

        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
