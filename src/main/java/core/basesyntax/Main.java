package core.basesyntax;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the dates ");
        String[] data = new String[]{
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"
        };
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[]{"John", "Andrew", "Kate"};
        String dataTo = scanner.nextLine();
        String dataFrom = scanner.nextLine();

        SalaryInfo info = new SalaryInfo();
        System.out.println(info.getSalaryInfo(names,data, dataFrom, dataTo));

    }
}
