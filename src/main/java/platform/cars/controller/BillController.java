package platform.cars.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.anotation.DataCheckAnotation;
import platform.cars.domain.Bill;
import platform.cars.service.IBillService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bill")
//@CrossOrigin
public class BillController {

    @Autowired
    private IBillService billService;

    /**
     * 保存订单信息
     * @param bill
     * @return
     * @throws ParseException
     */
    @RequestMapping("/save")
    @DataCheckAnotation
    public Map<String,Object> saveBillInfo(Bill bill){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(billService.saveBillInfo(bill)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 根据订单id获取订单信息
     * @param billId
     * @return
     */
    @RequestMapping("/single")
    @DataCheckAnotation
    public Map<String,Object> getSingleBill(String billId){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",billService.findOneBill(billId));
        return map;
    }

    /**
     * 返回分页的订单信息
     * @param page
     * @param size
     * @param authToken
     * @return
     */
    @RequestMapping("/paginated-buyer")
    @DataCheckAnotation
    public Map<String,Object> getBuyerPaginatedBillInfo(Integer page, Integer size,@CookieValue("authToken") String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",billService.findBuyerPaginatedBill(page,size,authToken));
        return map;
    }

    /**
     * 修改订单的信息
     * @param bill
     * @return
     */
    @RequestMapping("/alter")
    @DataCheckAnotation
    public Map<String,Object> alterBill(Bill bill){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(billService.alterBillStatus(bill)){
            code = 200;
            msg="success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取卖家收到的的订单
     * @param page
     * @param size
     * @param authToken
     * @return
     */
    @RequestMapping("/paginated-seller")
    @DataCheckAnotation
    public Map<String,Object> findSellerPaginatedBill(Integer page, Integer size, @CookieValue("authToken") String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",billService.findSellerPaginatedBill(page,size,authToken));
        return map;
    }
}
