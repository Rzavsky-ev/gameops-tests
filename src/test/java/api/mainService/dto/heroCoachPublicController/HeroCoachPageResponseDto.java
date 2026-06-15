package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCoachPageResponseDto(
        LocalDate suggestedPreviousEventDate,
        List<HeroCoachHeroResponseDto> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
}
