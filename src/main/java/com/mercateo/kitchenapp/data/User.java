package com.mercateo.kitchenapp.data;

import java.util.Set;

import com.mercateo.kitchenapp.sso.roles.UserRole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private final Email email;

    private final Password password;

    private final Set<UserRole> userRoles;

}
