package com.jiawablog.util;

import com.jiawablog.dto.OptionDto;

import java.util.ArrayList;
import java.util.List;

public class OptionUtil {

    public static List<OptionDto> getStatusOption() {
        List<OptionDto> optionDtoList = new ArrayList<>();
        OptionDto optionDto = new OptionDto();
        optionDto.setKey("P");
        optionDto.setValue("发布");
        optionDtoList.add(optionDto);
        optionDto = new OptionDto();
        optionDto.setKey("D");
        optionDto.setValue("草稿");
        optionDtoList.add(optionDto);
        return optionDtoList;
    }
}
