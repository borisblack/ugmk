package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.WbsDto;
import com.primavera.integration.client.bo.object.WBS;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WbsMapper {
    WbsDto wbsToWbsDto(WBS wbs);
}
