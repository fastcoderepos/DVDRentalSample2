package com.fastcode.dvdrentalsample2.restcontrollers.extended;

import com.fastcode.dvdrentalsample2.application.extended.customer.ICustomerAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.inventory.IInventoryAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.payment.IPaymentAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.rental.IRentalAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.staff.IStaffAppServiceExtended;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.restcontrollers.core.RentalController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental/extended")
public class RentalControllerExtended extends RentalController {

    public RentalControllerExtended(
        IRentalAppServiceExtended rentalAppServiceExtended,
        ICustomerAppServiceExtended customerAppServiceExtended,
        IInventoryAppServiceExtended inventoryAppServiceExtended,
        IPaymentAppServiceExtended paymentAppServiceExtended,
        IStaffAppServiceExtended staffAppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(
            rentalAppServiceExtended,
            customerAppServiceExtended,
            inventoryAppServiceExtended,
            paymentAppServiceExtended,
            staffAppServiceExtended,
            helper,
            env
        );
    }
    //Add your custom code here

}
