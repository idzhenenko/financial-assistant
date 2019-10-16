package ru.dzhenenko.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzhenenko.service.*;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class TerminalView {
    private static AccountService accountService;
    private static AccountTypeService accountTypeService;
    private static AuthService authService;
    private static ReportByCategoryService reportByCategoryService;
    private static TransactionService transactionService;

    public TerminalView(AccountService accountService, AccountTypeService accountTypeService, AuthService authService, ReportByCategoryService reportByCategoryService, TransactionService transactionService) {
        this.accountService = accountService;
        this.accountTypeService = accountTypeService;
        this.authService = authService;
        this.reportByCategoryService = reportByCategoryService;
        this.transactionService = transactionService;
    }

    public static void start(long userId1, UserDTO userDto) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");

        Scanner scanner = new Scanner(System.in);
        boolean menuStatus = true;
        while (menuStatus) {
            System.out.println("MENU:");
            System.out.println("1: Показать счета");
            System.out.println("2: Показать транзакции");
            System.out.println("3: Показать отчет по категориям");
            System.out.println("4: Операции со счетами");
            System.out.println("0: Выход");
            int input = scanner.nextInt();
            switch (input) {
                case 1:

                    System.out.println("Твои счета: ");

                    AccountService accountService1 = context.getBean(AccountService.class);
                    List<AccountDTO> userAccounts = accountService1.viewAccount(userId1);

                    userAccounts.forEach(System.out::println);

                    System.out.println("1: Создать счет");
                    System.out.println("2: Удалить счет");
                    System.out.println("3: Выход");
                    input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            // Создать счет
                            if (input == 1) {
                                System.out.println("Пожалуйста, введите детали счета:");
                                System.out.println("Имя:");
                                Scanner nameScan = new Scanner(System.in);
                                String name1 = nameScan.nextLine();

                                System.out.println("Баланс:");
                                Scanner balScan = new Scanner(System.in);
                                int balance1 = balScan.nextInt();
                                long testId4 = userDto.getId();

                                // метод создания счета
                                accountService = context.getBean(AccountService.class);
                                accountService.createAccount(name1, balance1, testId4);

                                System.out.println("Your account " + "'" + name1 + "'" + " is successfully created!");
                            }

                            break;
                        case 2:
                            System.out.println("**** УДАЛЕНИЕ СЧЕТА ****");
                            System.out.println("************************");
                            Scanner scanner2 = new Scanner(System.in);
                            System.out.println("Введите id счета: ");
                            int id = scanner2.nextInt();

                            System.out.println("Your account is successfully deleted!");

                            // метод удаления счета
                            accountService = context.getBean(AccountService.class);
                            accountService.removeAccount(id);

                            break;
                        case 3:
                            System.out.println("GOOD BYE!");
                            break;
                        // скобка была тут
                    }
                    break;
                // просмотр отчетов
                case 3:
                    System.out.println("*****************************");
                    String firstDate = request("Введите начальную дату YYYY-MM-DD");
                    String secondDate = request("Введите конечную дату YYYY-MM-DD");

                    long testId7 = userDto.getId();
                    System.out.println("Отчет: ");

                    reportByCategoryService = context.getBean(ReportByCategoryService.class);
                    List<ReportByCategoryDTO> list = reportByCategoryService.viewReportCategory(testId7, firstDate, secondDate);

                    list.forEach(System.out::println);

                    break;

                case 4:
                    // метод добавления транзакций (с учётом SQL транзакций)
                    System.out.println("*****************************");
                    long sourceAccount = requestId("Введите id счета списания:");
                    long targetAccount = requestId("Введите id счета зачисления:");
                    long idTypeTransaction = requestId("Введите тип транзакции(1.Приход, 2.Расход, 3.Перевод)");
                    long amount = requestId("Введите сумму:");
                    long idCategory = requestId("Введите id категории");
                    long idUser = userDto.getId();

                    System.out.println("Операция проведена успешно!");
                    transactionService = context.getBean(TransactionService.class);
                    transactionService.insertTransaction(sourceAccount, targetAccount, idTypeTransaction, amount, idCategory, idUser);


                    break;
                case 2:
                    System.out.println("**********************************");
                    System.out.println("====== КАТЕГОРИИ ТРАНЗАКЦИЙ ======");
                    System.out.println();
                    System.out.println("1: Создать транзакцию");
                    System.out.println("2: Удалить транзакцию");
                    System.out.println("3: Редактировать транзакцию");
                    System.out.println("0: Выход");
                    input = scanner.nextInt();

                    switch (input) {
                        case 1:
                            if (input == 1) {

                                System.out.println("*****************************");
                                System.out.println("==== СОЗДАНИЕ ТРАНЗАКЦИИ ====");

                                Scanner scanner3 = new Scanner(System.in);
                                System.out.println("Введите имя транзакции: ");
                                String name2 = scanner3.nextLine();
                                System.out.println("Транзакция с именем " + "'" + name2 + "'" + " успешно создана!");
                                // метод создания транзакции
                                AccountTypeService accountTypeService = context.getBean(AccountTypeService.class);
                                accountTypeService.createTypeAccount(name2);
                            }
                            break;
                        case 2:
                            System.out.println("*****************************");
                            System.out.println("==== УДАЛЕНИЕ ТРАНЗАКЦИИ ====");

                            Scanner scanner3 = new Scanner(System.in);
                            System.out.println("Введите id транзакции: ");
                            int id = scanner3.nextInt();
                            System.out.println("Транзакция с 'id = " + id + "' была успешно удалена");

                            // метод удаления транзакции
                            AccountTypeService accountTypeService = context.getBean(AccountTypeService.class);
                            accountTypeService.removeAccountType(id);

                            break;
                        case 3:
                            System.out.println("******************************");
                            System.out.println("= РЕДАКТИРОВАНИЕ ТРАНЗАКЦИИ ==");

                            String oldName = request("Введите имя для редактирования:");
                            int idBD = requestId("Ведите id:");
                            String newName = request("Введите новое имя:");
                            System.out.println("Транзакция успешно изменена c " + oldName + " на " + newName + " c id " + idBD + "!");

                            // метод редактирования транзакции
                            accountTypeService = context.getBean(AccountTypeService.class);
                            accountTypeService.editingAccountType(newName, idBD);
                            break;
                        case 0:
                            System.out.println("GOOD BYE!");
                            menuStatus = false;
                            break;
                        default:
                            System.out.println("Не понятный ввод!");
                    }
            }
        }
    }

    static String request(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextLine();
    }

    static Integer requestId(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextInt();
    }
}