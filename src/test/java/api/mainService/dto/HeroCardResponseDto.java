package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO для карточки героя в списке.
 *
 * @param id              уникальный идентификатор героя
 * @param slug            URL-имя героя
 * @param name            имя героя
 * @param baseHeroId      идентификатор базового героя (для костюмов)
 * @param isCostume       является ли костюмом
 * @param costumeIndex    индекс костюма
 * @param imageUrl        URL основного изображения
 * @param previewUrl      URL превью-изображения
 * @param elementName     название стихии
 * @param rarityName      название редкости
 * @param rarityStars     количество звёзд редкости
 * @param heroClassName   название класса героя
 * @param manaSpeedName   название скорости маны
 * @param familyName      название семьи
 * @param alphaTalentName название альфа-таланта
 * @param baseAttack      базовая атака
 * @param baseArmor       базовая защита
 * @param baseHp          базовое здоровье
 * @param releaseDate     дата выхода героя
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCardResponseDto(
        Long id,
        String slug,
        String name,
        Long baseHeroId,
        Boolean isCostume,
        Integer costumeIndex,
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
        LocalDate releaseDate
) {
}