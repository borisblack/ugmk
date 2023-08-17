package com.amplifierconsultancy.ugmk.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface DateTimeMapper {
    default OffsetDateTime toOffsetDateTime(Date date) {
        return date == null ? null : (new Date(date.getTime())).toInstant().atOffset(ZoneOffset.UTC);
    }

    default LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(new Date(date.getTime()).toInstant(), ZoneOffset.UTC);
    }
}
