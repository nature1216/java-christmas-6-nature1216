package christmas.domain;

import christmas.enumeration.BenefitType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitTest {
    @DisplayName("총혜택 금액을 반환한다.")
    @Test
    void getTotalBenefit() {
        Benefit benefit = new Benefit();
        benefit.update(BenefitType.GIFT_EVENT, 25000);
        benefit.update(BenefitType.SPECIAL_DISCOUNT, 1000);
        benefit.update(BenefitType.X_MAS_DISCOUNT,1200);

        assertThat(benefit.getTotalBenefit()).isEqualTo(27200);
    }

    @DisplayName("총 할인된 금액을 반환한다.")
    @Test
    void getToTalDiscount() {
        Benefit benefit = new Benefit();
        benefit.update(BenefitType.GIFT_EVENT, 25000);
        benefit.update(BenefitType.SPECIAL_DISCOUNT, 1000);
        benefit.update(BenefitType.X_MAS_DISCOUNT,1200);

        assertThat(benefit.getTotalDiscount()).isEqualTo(2200);
    }
}
