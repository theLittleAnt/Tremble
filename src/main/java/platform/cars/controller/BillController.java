package platform.cars.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.anotation.DataCheckAnotation;
import platform.cars.domain.Bill;
import platform.cars.service.IBillService;
import platform.cars.service.IUserService;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/bill")
@CrossOrigin
public class BillController {

    @Autowired
    private IBillService billService;

    @Autowired
    private IUserService userService;


    /**
     * 保存订单信息
     * @param bill
     * @param authToken
     * @return
     * @throws ParseException
     */
    @RequestMapping("/save")
    @DataCheckAnotation
    public String saveBillInfo(Bill bill,String authToken){
        String msg = "fail";
        if(billService.saveBillInfo(bill)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 根据订单id获取订单信息
     * @param billId
     * @return
     */
    @RequestMapping("/single")
    @DataCheckAnotation
    public Bill getSingleBill(String billId,String authToken){
        return billService.findOneBill(billId);
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
    public Map<String,Object> getBuyerPaginatedBillInfo(Integer page, Integer size,String authToken){
        return billService.findBuyerPaginatedBill(page,size,authToken);
    }

    /**
     * 修改订单的信息
     * @param bill
     * @param authToken
     * @return
     */
    @RequestMapping("/alter")
    @DataCheckAnotation
    public String alterBill(Bill bill,String authToken){
        String msg = "fail";
        if(billService.alterBillStatus(bill)){
            msg="success";
        }
        return msg;
    }

    @RequestMapping("/paginated-seller")
    @DataCheckAnotation
    Map<String,Object> findSellerPaginatedBill(Integer page, Integer size, String authToken){
        return billService.findSellerPaginatedBill(page,size,authToken);
    }
}
