package com.digio.market.application.port.in;

import java.util.List;

import com.digio.market.domain.model.CustomerPurchase;

public interface CustomerPurchaseUC {

    List<CustomerPurchase> orderedPurchase();

    CustomerPurchase getBiggestPurchase(final Integer year);
}
