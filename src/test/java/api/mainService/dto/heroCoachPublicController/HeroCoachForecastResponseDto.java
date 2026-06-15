package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для прогноза тренера героев.
 *
 * @param suggestedPreviousEventDate предполагаемая дата предыдущего события
 * @param effectivePreviousEventDate фактическая дата предыдущего события
 * @param targetDate                 целевая дата прогноза
 * @param newlyAvailableHeroes       список новых героев
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCoachForecastResponseDto(
        LocalDate suggestedPreviousEventDate,
        LocalDate effectivePreviousEventDate,
        LocalDate targetDate,
        List<HeroCoachHeroResponseDto> newlyAvailableHeroes
) {
}