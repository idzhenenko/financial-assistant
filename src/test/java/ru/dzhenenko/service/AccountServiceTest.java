package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dzhenenko.api.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.repository.ServiceAccountRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks AccountService accountService;

    @Mock ServiceAccountRepository serviceAccountRepository;
    @Mock AccountModelToAccountDtoConverter accountDtoConverter;

    @Mock ServiceAccountRepository accountRepository;

    @Test
    public void FoundByUserIdViewAccount() throws SQLException {

        Account account = new Account();
        account.setName("Иван Wallet");
        account.setBalance(50000);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        accountDTO.setName("Иван Wallet");
        accountDTO.setBalance(50000);
        lenient().when(accountDtoConverter.convert(account)).thenReturn(accountDTO);

        List<AccountDTO> accountDTO1 = accountService.viewAccount(1);

        assertNotNull(accountDTO1);
    }
    @Test
    public void NotFoundByUserIdViewAccount() throws SQLException {
        List<Account> account = new ArrayList<>();

        List<AccountDTO> accountDTO = accountService.viewAccount(1);

        List<AccountDTO> accountDTOList = new ArrayList<>();

        assertEquals(accountDTO, accountDTOList);

    }

    @Test
    public void creatAccount() throws SQLException {
        Account account = new Account();
        account.setName("Bank Of America");
        account.setBalance(1000000);
        lenient().when(serviceAccountRepository.save(any(Account.class))).thenReturn(account);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(10);
        accountDTO.setName("Bank Of America");
        accountDTO.setBalance(1000000);
        accountDTO.setUserId(10);
        lenient().when(accountDtoConverter.convert(account)).thenReturn(accountDTO);

    }
    @Test
    public void notCreatAccount() throws SQLException {
        Account account = new Account();
        account.setName("Bank Of Russia");
        account.setBalance(1000000);
        lenient().when(serviceAccountRepository.save(any(Account.class))).thenReturn(account);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(10);
        accountDTO.setName("Bank Of America");
        accountDTO.setBalance(666);
        accountDTO.setUserId(190);
        lenient().when(accountDtoConverter.convert(account)).thenReturn(accountDTO);

    }
    @Test
    public void removeAccount() throws SQLException {
        Account account = new Account();
        account.setName("Bank");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(100);
        accountDTO.setName("Bank");

        lenient().when(accountDtoConverter.convert(account)).thenReturn(accountDTO);

    }
}