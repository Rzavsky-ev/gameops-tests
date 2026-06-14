package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для детальной информации о классе героя.
 *
 * @param id                идентификатор класса
 * @param name              название класса
 * @param imageUrl          URL изображения
 * @param baseName          название базовой версии
 * @param baseDescription   описание базовой версии
 * @param masterName        название мастерской версии
 * @param masterDescription описание мастерской версии
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroClassDetailsResponseDto(Long id, String name, String imageUrl,
                                          String baseName, String baseDescription,
                                          String masterName, String masterDescription) {
}
