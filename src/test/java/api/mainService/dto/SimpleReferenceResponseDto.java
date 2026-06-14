package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для простого справочного объекта.
 *
 * @param id       идентификатор
 * @param name     название
 * @param imageUrl URL изображения
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SimpleReferenceResponseDto(Long id, String name, String imageUrl) {
}