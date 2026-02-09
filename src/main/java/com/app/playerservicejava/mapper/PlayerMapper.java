package com.app.playerservicejava.mapper;

import com.app.playerservicejava.dto.PlayerDto;
import com.app.playerservicejava.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "playerId", ignore = true)
    void dtoToEntity(PlayerDto playerDto, @MappingTarget Player player);
}
