package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для блока характеристик героя.
 *
 * @param attack значение атаки
 * @param armor  значение защиты
 * @param hp     значение здоровья
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroStatBlockResponseDto(
        Integer attack,
        Integer armor,
        Integer hp
) {
}