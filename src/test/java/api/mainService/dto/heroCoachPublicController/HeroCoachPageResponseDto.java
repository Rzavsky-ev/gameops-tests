package api.mainService.dto.heroCoachPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для страницы с героями тренера.
 *
 * @param suggestedPreviousEventDate предполагаемая дата предыдущего события
 * @param items                      список героев на текущей странице
 * @param page                       номер текущей страницы
 * @param size                       размер страницы
 * @param totalElements              общее количество героев
 * @param totalPages                 общее количество страниц
 * @param hasNext                    есть ли следующая страница
 */
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
