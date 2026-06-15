package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

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
) {}