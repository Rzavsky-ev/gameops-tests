package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для объекта с описанием.
 *
 * @param id          идентификатор
 * @param name        название
 * @param description описание
 * @param imageUrl    URL изображения
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DescribedReferenceResponseDto(Long id, String name, String description, String imageUrl) {
}