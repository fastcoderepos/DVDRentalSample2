package com.fastcode.dvdrentalsample2.domain.extended.payment;

import com.fastcode.dvdrentalsample2.domain.core.payment.IPaymentRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("paymentRepositoryExtended")
public interface IPaymentRepositoryExtended extends IPaymentRepository {
    //Add your custom code here
}
