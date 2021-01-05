package com.fastcode.dvdrentalsample2.application.extended.payment;

import com.fastcode.dvdrentalsample2.application.core.payment.PaymentAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.customer.ICustomerRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.payment.IPaymentRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.rental.IRentalRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.staff.IStaffRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("paymentAppServiceExtended")
public class PaymentAppServiceExtended extends PaymentAppService implements IPaymentAppServiceExtended {

    public PaymentAppServiceExtended(
        IPaymentRepositoryExtended paymentRepositoryExtended,
        ICustomerRepositoryExtended customerRepositoryExtended,
        IRentalRepositoryExtended rentalRepositoryExtended,
        IStaffRepositoryExtended staffRepositoryExtended,
        IPaymentMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(
            paymentRepositoryExtended,
            customerRepositoryExtended,
            rentalRepositoryExtended,
            staffRepositoryExtended,
            mapper,
            logHelper
        );
    }
    //Add your custom code here

}
