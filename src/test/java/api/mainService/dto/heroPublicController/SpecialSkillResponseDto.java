package api.mainService.dto.heroPublicController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO для специального навыка героя.
 *
 * @param name        название навыка
 * @param description описание навыка
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SpecialSkillResponseDto(String name, String description) {
}