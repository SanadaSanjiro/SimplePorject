package com.digdes.simple.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Участник команды проекта")
public class MemberDTO {
    @Schema (description = "Код проекта")
    private String prjcode;
    @Schema (description = "id сотрудника")
    private Long empid;
    @Schema (description = "Роль в команде. Возможные значения: MANAGER, ANALYST, DEVELOPER, TESTER")
    private String role;
}
