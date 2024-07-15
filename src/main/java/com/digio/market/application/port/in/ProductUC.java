package com.digio.market.application.port.in;

import com.digio.market.domain.model.CustomerPurchase;

public interface ProductUC {

    CustomerPurchase getWineTypeRecommendation(String cpf);
    
}
