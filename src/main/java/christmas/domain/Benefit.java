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

    public int getTotalBenefit() {
        int totalAmount = 0;
        for(BenefitType benefitType : BenefitType.values()) {
            totalAmount += getAmount(benefitType);
        }

        return totalAmount;
    }

    public int getTotalDiscount() {
        int totalDiscount = 0;
        for(BenefitType benefitType : BenefitType.values()) {
            if(benefitType.equals(BenefitType.GIFT_EVENT)) {
                continue;
            }
            totalDiscount += getAmount(benefitType);
        }

        return totalDiscount;
    }

    public void update(BenefitType benefitType, int amount) {
        benefits.put(benefitType, amount);
    }
}
