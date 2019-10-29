package cn.haiwan.controller.provider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.haiwan.util.pager.PageModel;
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
public class ProviderController {

    @Resource
    private ProviderService providerService;
    @Resource
    private BillService billService;

    // 查询供应商
    @RequestMapping("/sys/provider")
    public String findProviderBy(
            Model model, HttpSession session, PageModel pageModel,
            @RequestParam(value = "queryProCode", required = false) String proCode,
            @RequestParam(value = "queryProName", required = false) String proName) {
        // System.out.println(proCode+" "+proName);
        int recordCount = providerService.findRecordCount(proCode, proName);
        pageModel.setRecordCount(recordCount);
        //pageIndex当前页面
        int pageIndex = pageModel.getPageIndex();
        //pageSize每页分多少条数据
        int pageSize = pageModel.getPageSize();
        List<Provider> proList = providerService.findProviderBy(proCode, proName, pageIndex, pageSize);
        model.addAttribute("proList", proList);
        session.setAttribute("queryProCode", proCode);
        session.setAttribute("queryProName", proName);
        return "pro/providerlist";
    }

    // 添加供应商
    @RequestMapping("/sys/provideradd")
    public String addProvider() {
        return "pro/provideradd";
    }

    @RequestMapping("/sys/provideraddsave")
    public String doAddProvider(
            @RequestParam(value = "proCode") String providerCode,
            @RequestParam(value = "proName") String providerName,
            @RequestParam(value = "proContact") String providerContact,
            @RequestParam(value = "proPhone") String providerPhone,
            @RequestParam(value = "proAddress") String providerAddress,
            @RequestParam(value = "proFax") String providerFax,
            @RequestParam(value = "proDesc") String providerDesc) {
        Date date = new Date();
        Provider provider = new Provider(null, providerCode, providerName,
                providerDesc, providerContact, providerPhone, providerAddress,
                providerFax, 1, date, null, null);
        int i = providerService.addProvider(provider);
        if (i > 0) {
            //添加成功
            return "redirect:/sys/provider";
        } else {
            return "redirect:/sys/provideradd";
        }
    }

    // 显示供应商详情
    @RequestMapping("/sys/proview")
    public String providerView(Model model, @RequestParam Integer proid) {
        Provider provider = providerService.findById(proid);
        model.addAttribute("provider", provider);
        return "pro/providerview";
    }

    // 修改供应商信息
    @RequestMapping("/sys/providermodify")
    public String modifyProvider(Model model, @RequestParam Integer proid) {
        Provider provider = providerService.findById(proid);
        model.addAttribute("provider", provider);
        return "pro/providermodify";
    }

    @RequestMapping("/sys/providermodifysave")
    public String providermodifysave(@RequestParam(value = "proid") Integer id,
                                     @RequestParam(value = "proCode") String providerCode,
                                     @RequestParam(value = "proName") String providerName,
                                     @RequestParam(value = "proContact") String providerContact,
                                     @RequestParam(value = "proPhone") String providerPhone,
                                     @RequestParam(value = "proAddress") String providerAddress,
                                     @RequestParam(value = "proFax") String providerFax,
                                     @RequestParam(value = "proDesc") String providerDesc) {
        Provider provider = new Provider(id, providerCode, providerName,
                providerDesc, providerContact, providerPhone, providerAddress,
                providerFax);
        // System.out.println(provider);
        int i = providerService.modifyProvider(provider);
        if (i > 0) {
            // 修改成功
            return "redirect:/sys/provider";
        } else {
            return "redirect:/sys/providermodify";
        }
    }

    // 删除供应商
    @RequestMapping("/sys/delprovider")
    @ResponseBody
    // proid该值是从ajxa中传递过来的
    public Object delProvider(HttpServletRequest request,
                              @RequestParam Integer proid) {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        //System.out.println("当前要删除的订单id为：" + proid);
        /**
         * 删除供应商之前检测供应商下是否有未付款订单
         * 通过供应商的id在bill表中查询isPayment
         */
        //List<Provider> list = providerService.checkIspayment(proid);
        List<Bill> list = billService.checkIspayment(proid);
        int n = 0;
        for (Bill bill : list) {
            if (bill.getIsPayment() == 1) {
                n++;
            }
        }
        if (n == 0) {
            int i = providerService.delProvider(proid);
            if (i == 1) {
                map.put("delResult", "true");
            } else if (i == 0) {
                map.put("delResult", "false");
            }
            return map;
        } else {
            //记录该供应商下有多少条未付款订单
            map1.put("delResult", n);
            return map1;
        }
    }
}
