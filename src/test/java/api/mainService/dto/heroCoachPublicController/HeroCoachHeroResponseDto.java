package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO для героя в ответах тренера.
 *
 * @param id              идентификатор героя
 * @param slug            URL-имя
 * @param name            имя
 * @param imageUrl        URL изображения
 * @param previewUrl      URL превью
 * @param elementName     стихия
 * @param rarityName      редкость
 * @param rarityStars     звёзды редкости
 * @param heroClassName   класс героя
 * @param manaSpeedName   скорость маны
 * @param familyName      семья
 * @param alphaTalentName альфа-талант
 * @param baseAttack      базовая атака
 * @param baseArmor       базовая защита
 * @param baseHp          базовое здоровье
 * @param releaseDate     дата выхода
 * @param heroCoachDate   дата тренера героя
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCoachHeroResponseDto(
        Long id,
        String slug,
        String name,
        String imageUrl,
        String previewUrl,
        String elementName,
        String rarityName,
        Integer rarityStars,
        String heroClassName,
        String manaSpeedName,
        String familyName,
        String alphaTalentName,
        Integer baseAttack,
        Integer baseArmor,
        Integer baseHp,
        LocalDate releaseDate,
        LocalDate heroCoachDate
) {
}