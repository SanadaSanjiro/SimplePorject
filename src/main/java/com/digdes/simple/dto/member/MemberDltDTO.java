package com.digdes.simple.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;



@Data
@Schema(description = "ДТО с данными для идентификации учатсника проекта")
public class MemberDltDTO {
    @Schema(description = "Код проекта")
    private String prjcode;
    @Schema (description = "id сотрудника")
    private Long empid;
}
