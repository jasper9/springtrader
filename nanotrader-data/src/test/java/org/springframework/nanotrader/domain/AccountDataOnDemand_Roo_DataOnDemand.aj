// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.springframework.nanotrader.domain;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.nanotrader.domain.Account;
import org.springframework.nanotrader.domain.AccountDataOnDemand;
import org.springframework.nanotrader.domain.Accountprofile;
import org.springframework.nanotrader.domain.AccountprofileDataOnDemand;
import org.springframework.nanotrader.repository.AccountRepository;
import org.springframework.nanotrader.service.AccountService;
import org.springframework.stereotype.Component;

privileged aspect AccountDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AccountDataOnDemand: @Component;
    
    private Random AccountDataOnDemand.rnd = new SecureRandom();
    
    private List<Account> AccountDataOnDemand.data;
    
    @Autowired
    private AccountprofileDataOnDemand AccountDataOnDemand.accountprofileDataOnDemand;
    
    @Autowired
    AccountService AccountDataOnDemand.accountService;
    
    @Autowired
    AccountRepository AccountDataOnDemand.accountRepository;
    
    public Account AccountDataOnDemand.getNewTransientAccount(int index) {
        Account obj = new Account();
        setBalance(obj, index);
        setCreationdate(obj, index);
        setLastlogin(obj, index);
        setLogincount(obj, index);
        setLogoutcount(obj, index);
        setOpenbalance(obj, index);
        setProfileProfileid(obj, index);
        return obj;
    }
    
    public void AccountDataOnDemand.setBalance(Account obj, int index) {
        BigDecimal balance = BigDecimal.valueOf(index);
        if (balance.compareTo(new BigDecimal("999999999999.99")) == 1) {
            balance = new BigDecimal("999999999999.99");
        }
        obj.setBalance(balance);
    }
    
    public void AccountDataOnDemand.setCreationdate(Account obj, int index) {
        Date creationdate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationdate(creationdate);
    }
    
    public void AccountDataOnDemand.setLastlogin(Account obj, int index) {
        Date lastlogin = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLastlogin(lastlogin);
    }
    
    public void AccountDataOnDemand.setLogincount(Account obj, int index) {
        Integer logincount = new Integer(index);
        obj.setLogincount(logincount);
    }
    
    public void AccountDataOnDemand.setLogoutcount(Account obj, int index) {
        Integer logoutcount = new Integer(index);
        obj.setLogoutcount(logoutcount);
    }
    
    public void AccountDataOnDemand.setOpenbalance(Account obj, int index) {
        BigDecimal openbalance = BigDecimal.valueOf(index);
        if (openbalance.compareTo(new BigDecimal("999999999999.99")) == 1) {
            openbalance = new BigDecimal("999999999999.99");
        }
        obj.setOpenbalance(openbalance);
    }
    
    public void AccountDataOnDemand.setProfileProfileid(Account obj, int index) {
        Accountprofile profileProfileid = accountprofileDataOnDemand.getRandomAccountprofile();
        obj.setProfileProfileid(profileProfileid);
    }
    
    public Account AccountDataOnDemand.getSpecificAccount(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Account obj = data.get(index);
        Integer id = obj.getAccountid();
        return accountService.findAccount(id);
    }
    
    public Account AccountDataOnDemand.getRandomAccount() {
        init();
        Account obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getAccountid();
        return accountService.findAccount(id);
    }
    
    public boolean AccountDataOnDemand.modifyAccount(Account obj) {
        return false;
    }
    
    public void AccountDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = accountService.findAccountEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Account' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Account>();
        for (int i = 0; i < 10; i++) {
            Account obj = getNewTransientAccount(i);
            try {
                accountService.saveAccount(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            accountRepository.flush();
            data.add(obj);
        }
    }
    
}
