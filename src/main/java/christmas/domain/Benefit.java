package christmas.domain;

import christmas.enumeration.BenefitType;

import java.util.EnumMap;

public class Benefit {
    EnumMap<BenefitType, Integer> benefits;

    public Benefit() {
        benefits = new EnumMap<>(BenefitType.class);

        for(BenefitType benefitType : BenefitType.values()) {
            benefits.put(benefitType, 0);
        }
    }

    public int getNum(BenefitType benefitType) {
        return benefits.get(benefitType);
    }

    public void update(BenefitType benefitType) {
        benefits.put(benefitType, benefits.get(benefitType) + 1);
    }
}
