package cn.haiwan.controller.bill;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.haiwan.util.pager.PageModel;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haiwan.entity.Bill;
import cn.haiwan.entity.Provider;
import cn.haiwan.service.bill.BillService;
import cn.haiwan.service.provider.ProviderService;

@Controller
public class BillController {

    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/sys/bill")
    public String billList(
            HttpSession session,
            Model model, PageModel pageModel,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "providerId", required = false) Integer providerId,
            @RequestParam(value = "isPayment", required = false) Integer isPayment) {
        /* 查询并保存供应商的信息 */
        List<Provider> providerList = providerService.findProvider();
        session.setAttribute("providerList", providerList);

        // System.out.println(proCode+" "+proName);
        int recordCount = billService.findRecordCount(productName, providerId, isPayment);
        pageModel.setRecordCount(recordCount);
        //System.out.println(recordCount+"1111111111111");
        //pageIndex当前页面
        int pageIndex = pageModel.getPageIndex();
        //pageSize每页分多少条数据
        int pageSize = pageModel.getPageSize();
        List<Bill> billList = billService.findBill(productName, providerId, isPayment, pageIndex, pageSize);
        session.setAttribute("billList", billList);
        session.setAttribute("productName", productName);
        session.setAttribute("providerId", providerId);
        session.setAttribute("isPayment", isPayment);
        return "bill/billlist";
    }

    @RequestMapping("/sys/billadd")
    public String billAdd() {
        return "bill/billadd";
    }

    //添加订单
    @RequestMapping("/sys/dobilladd")
    public String doBillAdd(@RequestParam() String billCode, @RequestParam() String productName,
                            @RequestParam() String productUnit, @RequestParam() Double productCount, @RequestParam() Double totalPrice,
                            @RequestParam() Integer providerId, @RequestParam() Integer isPayment) {
        Date createTime = new Date();
		/*billService.addBill(billCode, productName, "办公用品-电脑", productUnit,
				productCount, totalPrice, isPayment, providerId, date);*/
        Bill bill = new Bill(null, billCode, productName, "办公用品-电脑", productUnit,
                productCount, totalPrice, isPayment, 1, createTime, null, null, providerId);
        //System.out.println(bill);
        int i = billService.addBill(bill);
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billadd";
        }
    }

    @RequestMapping("/sys/billselect")
    @ResponseBody
    public String billselect(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Provider> list = (List<Provider>) session.getAttribute("providerList");
		/*for (Provider provider : list) {
			System.out.println(provider.getProviderName());
		}*/
        String json = JSONArray.toJSONString(list);
        return json;
    }

    //查看单个订单详情
    @RequestMapping("/sys/billview")
    public String billView(HttpSession session, HttpServletRequest request) {
        /*@RequestParam(value="billid")这种方式获取不到*/
        String billidStr = request.getParameter("billid");
        //System.out.println(billidStr);
        Integer billid = Integer.parseInt(billidStr);
        Bill bill = billService.findById(billid);
        session.setAttribute("bill", bill);
        //System.out.println(bill);
        return "bill/billview";
    }

    //修改订单
    private Integer billid = null;

    @RequestMapping("/sys/billmodify")
    public String billModify(HttpSession session, HttpServletRequest request) {
        String billidStr = request.getParameter("billid");
        billid = Integer.parseInt(billidStr);
        //System.out.println(billid);
        Bill bill = billService.findById(billid);
        session.setAttribute("bill", bill);
        //System.out.println(bill.getProvider().getProviderName());
        return "bill/billmodify";
    }

    @RequestMapping("/sys/dobillmodify")
    public String dobillmodify(String billCode, String productName, String productUnit, Double productCount,
                               Double totalPrice, Integer isPayment, Integer providerId) {
        //获取当前bill的id
        Integer id = billid;
        Bill bill = new Bill(id, billCode, productName, productUnit, productCount, totalPrice, isPayment, providerId);
        int i = billService.modifyBill(bill);
        //System.out.println("修改"+i+"条订单");
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billmodify";
        }
    }

    //删除订单
    @RequestMapping("/sys/billdel")
    @ResponseBody
    public Object billDelete(@RequestParam Integer billid, @RequestParam Integer isPayment) {
        HashMap<String, String> map = new HashMap<>();
        int i = 0;
        if (isPayment == 2) {
            i = billService.delBill(billid);
        }
        //System.out.println(i);
        if (i == 1 && isPayment == 2) {
            //删除成功后，保存true到map中
            map.put("delResult", "true");
        } else if (i == 0 && isPayment == 1) {
            map.put("delResult", "cannot");
        } else {
            map.put("delResult", "false");
        }
        //return "redirect:/sys/bill";
        return map;
    }

}
