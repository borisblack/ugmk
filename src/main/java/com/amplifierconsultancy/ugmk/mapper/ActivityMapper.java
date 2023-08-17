package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.ActivityDto;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {DateTimeMapper.class}
)
public interface ActivityMapper {
    @Mapping(target = "wbsObjectId", expression = "java(activity.getWBSObjectId().toString())")
    @Mapping(target = "wbsCode", source = "WBSCode")
    @Mapping(target = "wbsName", source = "WBSName")
    @Mapping(target = "wbsNamePath", source = "WBSNamePath")
    ActivityDto toActivityDto(Activity activity) throws BusinessObjectException;
}
