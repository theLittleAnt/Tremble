package platform.cars.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.domain.Bill;
import platform.cars.service.IBillService;
import platform.cars.service.IUserService;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IBillService billService;

    @Autowired
    private IUserService userService;

    /**
     * 根据订单id获取订单信息
     * @param billId
     * @return
     */
    @RequestMapping("/single")
    public Bill getSingleBill(String billId,String authToken) throws ParseException {
        Bill bill = null;
        if(userService.checkToken(authToken)){
            bill = billService.findOneBill(billId);
        }
        return bill;
    }

    /**
     * 返回分页的订单信息
     * @param page
     * @param size
     * @param authToken
     * @return
     */
    @RequestMapping("/paginated")
    public Map<String,Object> getPaginatedBillInfo(int page,int size,String authToken) throws ParseException {
        Map<String,Object> billMap = null;
        if(userService.checkToken(authToken)){
            billMap = billService.findPaginatedBill(page,size,authToken);
        }
        return billMap;
    }

    /**
     * 修改订单的信息
     * @param bill
     * @param authToken
     * @return
     */
    @RequestMapping("/alter")
    public String alterBill(Bill bill,String authToken) throws ParseException {
        String msg = "fail";
        if(userService.checkToken(authToken) && billService.alterBillStatus(bill)){
            msg="success";
        }
        return msg;
    }
}
