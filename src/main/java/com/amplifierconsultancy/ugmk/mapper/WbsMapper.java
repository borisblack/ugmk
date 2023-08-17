package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.WbsDto;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.WBS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {DateTimeMapper.class}
)
public interface WbsMapper {
    @Mapping(target = "objectId", expression = "java(wbs.getObjectId().toString())")
    @Mapping(target = "parentObjectId", expression = "java(wbs.getParentObjectId().toString())")
    @Mapping(target = "guid", source = "wbs.GUID")
    @Mapping(target = "fullCode", expression = "java(prefix + wbs.getCode())")
    @Mapping(target = "obsObjectId", expression = "java(wbs.getOBSObjectId().toString())")
    @Mapping(target = "obsName", source = "wbs.OBSName")
    @Mapping(target = "wbsCategoryObjectId", expression = "java(wbs.getWBSCategoryObjectId() == null ? null : wbs.getWBSCategoryObjectId().toString())")
    @Mapping(target = "status", expression = "java(wbs.getStatus().toString())")
    @Mapping(target = "activities", ignore = true)
    @Mapping(target = "childWbsList", ignore = true)
    WbsDto toWbsDto(WBS wbs, String prefix) throws BusinessObjectException;
}
