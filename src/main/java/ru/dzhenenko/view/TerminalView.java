package ru.dzhenenko.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.service.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TerminalView {
    private final AuthService authService;
    private final AccountService accountService;
    private final ReportByCategoryService reportByCategoryService;
    private final TransactionService transactionService;
    private final AccountTypeService accountTypeService;

    public void start() throws SQLException {
        while (true) {
            int userSelect = requestInt("=====Menu=====:\n1 - Регистрация \n2 - Вход\n0 - Выход\n введите цифру:\n");
            if (userSelect == 1) {

                String first_name = requestString("введите имя");
                String last1_name = requestString("введите фамилию");
                String phone = requestString("введите номер телефона");
                String email = requestString("введите email");
                String pass = requestString("введите пароль");
                UserDTO userDTO = authService.registration(first_name, last1_name, phone, email, pass);
                System.out.println("Успешная регистрация: " + userDTO);

            } else if (userSelect == 2) {

                String email = requestString("введите email");
                String pass = requestString("введите пароль");
                UserDTO userDTO = authService.auth(email, pass);
                if (userDTO != null) {
                    System.out.println("Hello, " + userDTO.getFirstName() + " " + userDTO.getLastName() +
                            " [id=" + userDTO.getId() + "]");
                    while (true) {
                        int userSelectAuth = requestInt(
                                "\n=====Menu=====:\n1 - Показать счета \n2 - Создать счет\n3 - Удалить счет\n4 - Редактировать счет\n" +
                                        "5 - Показать категории\n6 - Редактировать категорию\n7 - Добавить категорию\n" +
                                        "8 - Удалить категорию\n9 - Отчет по категориям\n10 - Добавить транзакцию\n0 - Выход\nвведите цифру:\n");


                        if (userSelectAuth == 1) {
                            //showAccount
                            List<AccountDTO> accountDTO = accountService.viewAccount(userDTO.getId());
                            for (AccountDTO dto : accountDTO) {
                                System.out.println("\t" + dto.getName() + " = " + dto.getBalance() + " [id=" + dto.getId() + "]");
                            }

                        }
                        //**********************
                        //test JPA User findById
                        //**********************
                        else if (userSelectAuth == 100) {
                            UserDTO tess = authService.getUserById(userDTO.getId());
                            System.out.println("\n==== JPA TEST ====\n" + tess);
                        }
                        //**********************
                        //test JPA User findById
                        //**********************
                        else if (userSelectAuth == 2) {
                            //addAccount
                            String name = requestString("введите имя счета:");
                            long balance = requestLong(requestString("введите начальный остаток: 0.00"));
                            AccountDTO accountDTO = accountService.createAccount(name, balance, userDTO.getId());
                            System.out.println("Успешно добавлен: " + accountDTO);

                        } else if (userSelectAuth == 3) {
                            //delAccount
                            long userSelectDel = requestLong("введите id для удаления, либо 0 для выхода");
                            if (userSelectDel == 0) {
                                continue;
                            }
                            accountService.removeAccount(userSelectDel);

                        } else if (userSelectAuth == 4) {
                            //editCategory
                            int userSelectUp = requestInt("введите id для редактирования, либо 0 для выхода");
                            if (userSelectUp == 0) {
                                continue;
                            }
                            accountTypeService.createTypeAccount(requestString("введите новое имя категории"), userSelectUp);

                        } else if (userSelectAuth == 5) {
                            //addCategory
                            AccountTypeDTO accountTypeDTO = accountTypeService
                                    .createTypeAccount(requestString("введите название категории:"), userDTO.getId());
                            System.out.println(accountTypeDTO + " - успешно добавлено");

                        } else if (userSelectAuth == 6) {
                            //delCategory
                            int userSelectDel = requestInt("введите id для удаления, либо 0 для выхода");
                            if (userSelectDel == 0) {
                                continue;
                            }
                            accountTypeService.removeAccountType(userSelectDel);

                        } else if (userSelectAuth == 7) {
                            //report
                            List<ReportByCategoryDTO> reportDTOS = reportByCategoryService.viewReportCategory(
                                    userDTO.getId(),
                                    requestString("Введите начальную дату YYYY-MM-DD"),
                                    requestString("Введите конечную дату YYYY-MM-DD")
                            );
                            for (ReportByCategoryDTO report : reportDTOS) {
                                System.out.println(report.getName() + " " + report.getAmount());
                            }


                        } else if (userSelectAuth == 8) {
                            //add transaction
                            TransactionDTO transactionDTO = transactionService.insertTransaction(
                                    requestLong("Откуда: "),
                                    requestLong("Куда: "),
                                    requestLong("Тип 1.Приход, 2.Расход, 3.Перевод:"),
                                    requestLong(requestString("Сумма:")),
                                    requestLong("Введите id категории"),
                                    userDTO.getId()
                            );

                        } else if (userSelectAuth == 0) {
                            break;
                        } else {
                            System.out.println("Не верное число");
                        }
                    }

                } else {
                    System.out.println("нет такого юзера");
                    break;
                }

            } else if (userSelect == 0) {
                break;
            } else {
                System.out.println("Не верное число");
            }
        }

    }

    static String requestString(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextLine();
    }

    static int requestInt(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextInt();
    }

    static long requestLong(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextLong();
    }
}