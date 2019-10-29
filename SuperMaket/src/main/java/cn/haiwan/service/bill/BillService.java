package cn.haiwan.service.bill;


import java.util.List;

import cn.haiwan.entity.Bill;

public interface BillService {

    int addBill(Bill bill);

    Bill findById(Integer billId);

    int modifyBill(Bill bill);

    int delBill(Integer billId);

    List<Bill> checkIspayment(Integer providerId);

    int findRecordCount(String productName, Integer providerId, Integer isPayment);

    List<Bill> findBill(String productName, Integer providerId, Integer isPayment, int pageIndex, int pageSize);
}
