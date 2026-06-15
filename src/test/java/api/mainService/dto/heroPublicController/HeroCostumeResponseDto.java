package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для информации о костюме героя.
 *
 * @param id       идентификатор костюма
 * @param name     название костюма
 * @param imageUrl URL изображения
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroCostumeResponseDto(Long id, String name, String imageUrl) {
}
