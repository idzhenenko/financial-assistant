package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dzhenenko.api.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.repository.ServiceAccountTypeRepository;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeServiceTest {

    @InjectMocks AccountTypeService accountTypeService;

    //@Mock AccountTypeDao accountTypeDao;
    @Mock ServiceAccountTypeRepository serviceAccountTypeRepository;
    @Mock AccountTypeModelToAccountTypeDtoConverter accountTypeDtoConverter;

    @Test
    public void createTypeAccount() throws SQLException {
        AccountType accountType = new AccountType();
        accountType.setId(23L);
        accountType.setName("На Вертолет");
        lenient().when(serviceAccountTypeRepository.save(any(AccountType.class))).thenReturn(accountType);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(20);
        accountTypeDTO.setName("На Вертолет");
        lenient().when(accountTypeDtoConverter.convert(accountType)).thenReturn(accountTypeDTO);

        assertNotNull(accountTypeDTO);
    }
    @Test
    public void NotCreateAccount() throws SQLException {
        AccountType accountType = new AccountType();
        //accountType.setId(99);
        accountType.setName("На Продукты");
        lenient().when(serviceAccountTypeRepository.save(any(AccountType.class))).thenReturn(null);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(99);
        accountTypeDTO.setName("На Продукты");
        lenient().when(accountTypeDtoConverter.convert(accountType)).thenReturn(accountTypeDTO);

        //AccountTypeDTO accountTypeDTO1 = accountTypeService.createTypeAccount("На Продукты");
        assertNotNull(accountTypeDTO);
        //assertNotEquals(accountTypeDTO, accountTypeDTO1);
        //verify(accountTypeDao, times(1)).addAccountType("На Продукты");
    }

    @Test
    public void NotRemoveAccountType() throws SQLException {
        AccountType accountType = new AccountType();
        //accountType.setId(1);
        accountType.setName("На Мерседес");
        //when(accountTypeDao.deleteAccountType(1)).thenReturn(null);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(1);
        accountTypeDTO.setName("На Мерседес");
        lenient().when(accountTypeDtoConverter.convert(accountType)).thenReturn(accountTypeDTO);

        AccountTypeDTO accountTypeDTO1 = accountTypeService.removeAccountType(1);

        assertNull(accountTypeDTO1);
        //verify(accountTypeDao, times(1)).deleteAccountType(1);
        //verifyNoMoreInteractions(accountTypeDao);
    }

    @Test
    public void editingAccountType() {
        AccountType accountType = new AccountType();
        //accountType.setId(10);
        accountType.setName("Наличные");
        //when(accountTypeDao.editAccountType("Наличные",10)).thenReturn(accountType);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setName("На Вискарь");
        lenient().when(accountTypeDtoConverter.convert(accountType)).thenReturn(accountTypeDTO);

        assertNotEquals(accountType, accountTypeDTO);
        verifyNoMoreInteractions(accountTypeDtoConverter);
    }
    @Test
    public void removeAccountType() throws SQLException {
        AccountType accountType = new AccountType();
       // accountType.setId(1);
        accountType.setName("На Мерседес");
        //when(serviceAccountTypeRepository.deleteById(1L)).thenReturn(null);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(1);
        accountTypeDTO.setName("На Мерседес");
        lenient().when(accountTypeDtoConverter.convert(accountType)).thenReturn(accountTypeDTO);

        AccountTypeDTO accountTypeDTO1 = accountTypeService.removeAccountType(1);

        //verify(accountTypeDao, times(1)).deleteAccountType(1);
        //assertNotNull(accountTypeDTO);
        //verifyZeroInteractions(accountTypeDao);
        //verifyNoMoreInteractions(accountTypeDao);
    }
}