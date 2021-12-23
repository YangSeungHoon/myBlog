package com.sheep.sh.myblog.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T>{
    int status;
    T data;
}
