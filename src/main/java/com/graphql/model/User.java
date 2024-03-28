package com.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "`user`") //User h2 için yer ayıltımış olduğu için `` lari kullanarak yaziyoruz
public class User extends BaseEntity{
    private String username;
    private String mail;
    @Enumerated(EnumType.STRING)
    private Role role;

}
