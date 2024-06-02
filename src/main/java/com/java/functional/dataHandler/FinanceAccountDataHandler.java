package com.java.functional.dataHandler;

import com.java.functional.dto.DataSet;
import com.java.functional.dto.FinanceAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinanceAccountDataHandler implements DataHandler {
    @Override
    public void handleData(DataSet dataSet) {
        /*
        If account number, check number, check date and check amount (regardless of the decimal point) are same then those accounts should be marked as duplicate.
        The target of this method is to find all those duplicate accounts.
         */
        List<FinanceAccount> financeAccounts = new ArrayList<>();
        dataSet.getRows()
                .forEach(map -> {
                    financeAccounts.add(new FinanceAccount(map.get("ACCT_NUM"), map.get("CHECK_NUM"), map.get("CHECK_AMT"), LocalDate.parse(map.get("CHECK_DATE"))));
                });

        ArrayList<FinanceAccount> accountsHavingDecimalPointInCheckAmount = financeAccounts.stream()
                .filter(financeAccount -> financeAccount.getCheckAmount().contains("."))
                .collect(Collectors.toCollection(ArrayList::new));

        List<FinanceAccount> result = new ArrayList<>();
        accountsHavingDecimalPointInCheckAmount
                .forEach(account -> {
                    ArrayList<FinanceAccount> filteredAcct = financeAccounts.stream()
                            .filter(fa ->
                                    fa.getCheckAmount().equals(account.getCheckAmount().replace(".", ""))
                                    && fa.getAcctNum().equals(account.getAcctNum())
                                    && fa.getCheckNum().equals(account.getCheckNum())
                                    && fa.getCheckDate().isEqual(account.getCheckDate())
                            )
                            .collect(Collectors.toCollection(ArrayList::new));
                    if (!filteredAcct.isEmpty()) {
                        result.add(account);
                        result.addAll(filteredAcct);
                    }
                });
        System.out.println(result);
    }
}
