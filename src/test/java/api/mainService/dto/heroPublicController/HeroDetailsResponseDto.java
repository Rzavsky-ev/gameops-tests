package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для полной информации о герое.
 *
 * @param id                    уникальный идентификатор героя
 * @param slug                  URL-имя героя
 * @param name                  имя героя
 * @param element               стихия
 * @param rarity                редкость
 * @param heroClass             класс героя
 * @param family                семья
 * @param manaSpeed             скорость маны
 * @param alphaTalent           альфа-талант
 * @param specialSkill          специальный навык
 * @param passiveSkills         список пассивных навыков
 * @param costumes              список костюмов
 * @param baseHeroId            идентификатор базового героя
 * @param baseAttack            базовая атака
 * @param baseArmor             базовая защита
 * @param baseHp                базовое здоровье
 * @param costumeBonusJson      бонусы костюма
 * @param imageUrl              URL основного изображения
 * @param previewUrl            URL превью-изображения
 * @param releaseDate           дата выхода героя
 * @param heroCoachDate         дата тренера героя
 * @param visitingOutfitterDate дата визита экипировщика
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroDetailsResponseDto(
        Long id,
        String slug,
        String name,
        SimpleReferenceResponseDto element,
        HeroRarityResponseDto rarity,
        HeroClassDetailsResponseDto heroClass,
        DescribedReferenceResponseDto family,
        DescribedReferenceResponseDto manaSpeed,
        DescribedReferenceResponseDto alphaTalent,
        SpecialSkillResponseDto specialSkill,
        List<HeroPassiveSkillResponseDto> passiveSkills,
        List<HeroCostumeResponseDto> costumes,
        Long baseHeroId,
        Integer baseAttack,
        Integer baseArmor,
        Integer baseHp,
        CostumeBonusJsonDto costumeBonusJson,
        String imageUrl,
        String previewUrl,
        LocalDate releaseDate,
        LocalDate heroCoachDate,
        LocalDate visitingOutfitterDate
) {
}