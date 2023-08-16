package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.ActivityDto;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(target = "wbsObjectId", expression = "java(activity.getWBSObjectId().toString())")
    @Mapping(target = "wbsCode", expression = "java(activity.getWBSCode())")
    @Mapping(target = "wbsName", expression = "java(activity.getWBSName())")
    @Mapping(target = "wbsNamePath", expression = "java(activity.getWBSNamePath())")
    ActivityDto activityToActivityDto(Activity activity) throws BusinessObjectException;
}
