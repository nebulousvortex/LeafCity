package ru.vortex.leafcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.leafcity.model.shop.Promocode;
import ru.vortex.leafcity.repository.PromocodeRepository;

@Service
public class PromocodeService {
    private final PromocodeRepository promoCodeRepository;

    @Autowired
    public PromocodeService(PromocodeRepository promocodeRepository) {
        this.promoCodeRepository = promocodeRepository;
    }
    public void createPromocode(Promocode promocode){
        promoCodeRepository.save(promocode);
    }

    public float getDiscountByCode(String code) {
        Promocode promoCode = promoCodeRepository.findByCode(code);
        if (promoCode != null) {
            return promoCode.getDiscount();
        }
        return 0.0f; // Если промокод не найден, возвращаем 0 (без скидки)
    }

}

