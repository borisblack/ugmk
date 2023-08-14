package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.WbsDto;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.WBS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WbsMapper {
    @Mapping(target = "children", ignore = true)
    WbsDto wbsToWbsDto(WBS wbs) throws BusinessObjectException;
}
