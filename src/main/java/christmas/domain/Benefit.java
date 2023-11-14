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

    public int getAmount(BenefitType benefitType) {
        return benefits.get(benefitType);
    }

    public void update(BenefitType benefitType, int amount) {
        benefits.put(benefitType, amount);
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for(BenefitType benefitType : BenefitType.values()) {
            totalAmount += getAmount(benefitType);
        }

        return totalAmount;
    }
}
