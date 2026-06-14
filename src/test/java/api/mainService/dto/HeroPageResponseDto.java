package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * DTO для страницы с героями.
 *
 * @param items         список героев на текущей странице
 * @param page          номер текущей страницы
 * @param size          размер страницы
 * @param totalElements общее количество героев
 * @param totalPages    общее количество страниц
 * @param hasNext       есть ли следующая страница
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroPageResponseDto(
        List<HeroCardResponseDto> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
}