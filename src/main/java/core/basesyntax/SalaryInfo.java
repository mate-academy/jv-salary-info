package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {

        Employe[] everyOneEmploye = new Employe[data.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        if (LocalDate.parse(dateFrom, formatter).isAfter(LocalDate.parse(dateTo, formatter))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder builder
                = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");

        String[] buffer;
        for (int i = 0; i < data.length; ++i) {
            buffer = data[i].split(" ");
            everyOneEmploye[i] = new Employe(LocalDate.parse(buffer[0], formatter), buffer[1],
                    Integer.parseInt(buffer[2]), Integer.parseInt(buffer[3]));
        }
        for (String name : names) {
            int finishSalary = 0;
            for (int i = 0; i < everyOneEmploye.length; ++i) {
                if (name.equals(everyOneEmploye[i].getName())
                        && ((everyOneEmploye[i].getDate()
                        .isEqual(LocalDate.parse(dateFrom, formatter)))
                        || (everyOneEmploye[i].getDate()
                        .isEqual(LocalDate.parse(dateTo, formatter)))
                        || (everyOneEmploye[i].getDate()
                        .isAfter(LocalDate.parse(dateFrom, formatter))
                        && everyOneEmploye[i].getDate()
                        .isBefore(LocalDate.parse(dateTo, formatter))))) {
                    finishSalary += everyOneEmploye[i].getHours() * everyOneEmploye[i].getSalary();
                }
            }
            builder.append(name).append(" - ").append(finishSalary).append("\n");
        }
        return builder.toString();
    }
}
