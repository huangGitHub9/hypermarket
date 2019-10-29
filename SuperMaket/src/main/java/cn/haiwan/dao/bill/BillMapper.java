package cn.haiwan.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.haiwan.entity.Bill;
import org.apache.ibatis.session.RowBounds;

public interface BillMapper {
    //添加订单
    int addBill(Bill bill);

    //显示订单详情
    Bill findById(Integer billId);

    //修改订单
    int modifyBill(Bill bill);

    //删除订单
    int delBill(Integer billId);

    //检测供应商下面订单是否均已付款
    List<Bill> checkIspayment(Integer providerId);

    //查询商品个数
    int findRecordCount(@Param("productName") String productName, @Param("providerId") Integer ProviderId,
                        @Param("isPayment") Integer isPayment);

    List<Bill> findBill(@Param("productName") String productName, @Param("providerId") Integer ProviderId,
                        @Param("isPayment") Integer isPayment, RowBounds rowBounds);
}
