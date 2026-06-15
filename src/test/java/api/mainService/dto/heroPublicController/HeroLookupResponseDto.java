package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для краткой информации о герое (поиск, имена).
 *
 * @param id   идентификатор героя
 * @param slug URL-имя героя
 * @param name имя героя
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroLookupResponseDto(
        Long id,
        String slug,
        String name
) {
}