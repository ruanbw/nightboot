package com.nightboot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    normal("正常",0),
    freeze("冻结",1),
    delete("删除",2);

    private final String name;
    private final Integer status;
}
