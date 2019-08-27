package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * names:
     * Сергей
     * Андрей
     * София
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 27.04.2019 Сергей 7 100
     * 28.04.2019 София 9 100
     * 28.04.2019 Сергей 11 50
     * 28.04.2019 Андрей 3 200
     * 29.04.2019 Сергей 7 100
     * 29.04.2019 София 9 100
     * 30.04.2019 Сергей 11 50
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        if (localDateTo.isBefore(localDateFrom)) {
            return null;
        }
        String list = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] stringData = data[i].split(" ");
                if (localDateTo.isBefore(LocalDate.parse(stringData[0], formatter))) {
                    break;
                }
                if (name.equals(stringData[1])) {
                    salary += Integer.valueOf(stringData[2]) * Integer.valueOf(stringData[3]);
                }
            }
            list += String.format("%s - %d\n", name, salary);
        }
        return list;
    }
}
