package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для бонусов костюма героя.
 *
 * @param attack бонус к атаке
 * @param armor  бонус к защите
 * @param hp     бонус к здоровью
 * @param mana   бонус к мане
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CostumeBonusJsonDto(
        Integer attack,
        Integer armor,
        Integer hp,
        Integer mana
) {
}
