package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCoachForecastResponseDto(
        LocalDate suggestedPreviousEventDate,
        LocalDate effectivePreviousEventDate,
        LocalDate targetDate,
        List<HeroCoachHeroResponseDto> newlyAvailableHeroes
) {
}