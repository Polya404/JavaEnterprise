package org.application.mapper;

import org.application.dto.UserRequestResponse;
import org.application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestResponseMapper extends Mappable<User, UserRequestResponse>{
}
