package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dzhenenko.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.dao.AccountModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks AccountService accountService;

    @Mock AccountDao accountDao;
    @Mock AccountModelToAccountDtoConverter accountDtoConverter;

    @Test
    public void FoundByUserIdViewAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1);
        accountModel.setName("Иван Wallet");
        accountModel.setBalance(50000);
        when(accountDao.findByUserId(1)).thenReturn(accountModel);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        accountDTO.setName("Иван Wallet");
        accountDTO.setBalance(50000);
        when(accountDtoConverter.convert(accountModel)).thenReturn(accountDTO);

        List<AccountDTO> accountDTO1 = accountService.viewAccount(1);

        assertNotNull(accountDTO1);
        verify(accountDao, times(1)).viewAccountUser(1);
    }
    @Test
    public void NotFoundByUserIdViewAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        when(accountDao.findByUserId(1)).thenReturn(accountModel);
        List<AccountDTO> accountDTO = accountService.viewAccount(1);

        List<AccountDTO> accountDTOList = new ArrayList<>();

        assertEquals(accountDTO, accountDTOList);
        verify(accountDao, times(1)).viewAccountUser(1);
        verifyZeroInteractions(accountDao);

    }

    @Test
    public void creatAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(10);
        accountModel.setName("Bank Of America");
        accountModel.setBalance(1000000);
        accountModel.setUserId(10);
        when(accountDao.addAccount("Bank Of America",1000000,10)).thenReturn(accountModel);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(10);
        accountDTO.setName("Bank Of America");
        accountDTO.setBalance(1000000);
        accountDTO.setUserId(10);
        when(accountDtoConverter.convert(accountModel)).thenReturn(accountDTO);

        AccountDTO accountDTOList = accountService.createAccount("Bank Of America",1000000,10);

        assertEquals(accountDTOList, accountDTO);
        verify(accountDao, times(1)).addAccount("Bank Of America",1000000, 10);
    }
    @Test
    public void notCreatAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(10);
        accountModel.setName("Bank Of Russia");
        accountModel.setBalance(1000000);
        accountModel.setUserId(10);
        when(accountDao.addAccount("Bank Of Russia",1000000,10)).thenReturn(accountModel);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(10);
        accountDTO.setName("Bank Of America");
        accountDTO.setBalance(666);
        accountDTO.setUserId(190);
        when(accountDtoConverter.convert(accountModel)).thenReturn(accountDTO);

        AccountDTO accountDTO1 = accountService.createAccount("Bank Of America",666, 190);

        assertNotEquals(accountDTO1, accountDTO);
        verify(accountDao, times(1)).addAccount("Bank Of America",666, 190);
        verifyZeroInteractions(accountDtoConverter);
    }
    @Test
    public void removeAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(100);
        accountModel.setName("Bank");
        when(accountDao.deleteAccount(100)).thenReturn(accountModel);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(100);
        accountDTO.setName("Bank");

        when(accountDtoConverter.convert(accountModel)).thenReturn(accountDTO);

        AccountDTO accountDTO1 = accountService.removeAccount(100);

        verify(accountDao, times(1)).deleteAccount(100);
    }
}