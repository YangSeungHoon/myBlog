package com.sheep.sh.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T>{
    int status;
    T data;
}
