package com.fastcode.dvdrentalsample2.domain.core.store;

import com.fastcode.dvdrentalsample2.domain.core.abstractentity.AbstractEntity;
import com.fastcode.dvdrentalsample2.domain.core.address.AddressEntity;
import com.fastcode.dvdrentalsample2.domain.core.staff.StaffEntity;
import java.time.*;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STORE")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class StoreEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "STORE_ID", nullable = false)
    private Integer storeId;

    @Basic
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "MANAGER_STAFF_ID")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;
}
