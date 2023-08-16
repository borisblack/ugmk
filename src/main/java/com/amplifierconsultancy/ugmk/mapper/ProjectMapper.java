package com.amplifierconsultancy.ugmk.mapper;

import com.amplifierconsultancy.ugmk.dto.ProjectDto;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target = "objectId", expression = "java(project.getObjectId().toString())")
    @Mapping(target = "guid", source = "GUID")
    @Mapping(target = "obsObjectId", expression = "java(project.getOBSObjectId().toString())")
    @Mapping(target = "obsName", expression = "java(project.getOBSName())")
    @Mapping(target = "wbsObjectId", expression = "java(project.getWBSObjectId().toString())")
    @Mapping(target = "wbsHierarchyLevels", expression = "java(project.getWBSHierarchyLevels())")
    @Mapping(target = "wbsCodeSeparator", expression = "java(project.getWBSCodeSeparator())")
    @Mapping(target = "status", expression = "java(project.getStatus().toString())")
    @Mapping(target = "wbsList", ignore = true)
    ProjectDto projectToProjectDto(Project project) throws BusinessObjectException;
}
