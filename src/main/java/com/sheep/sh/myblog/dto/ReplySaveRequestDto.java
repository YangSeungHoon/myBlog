package com.sheep.sh.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplySaveRequestDto {

    private Long userId;
    private Long boardId;
    private String content;
}
