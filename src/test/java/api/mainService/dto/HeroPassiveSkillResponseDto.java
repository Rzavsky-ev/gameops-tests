package api.mainService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для пассивного навыка героя.
 *
 * @param name        название навыка
 * @param description описание навыка
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HeroPassiveSkillResponseDto(String name, String description) {
}