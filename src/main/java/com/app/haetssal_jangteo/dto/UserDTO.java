package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.SellerState;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.enumeration.User;
//import com.app.haetssal_jangteo.domain.SellerVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.domain.OAuthVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userEmail;
    @JsonIgnore
    private String userPassword;
    private String userPhone;
    private User userType;
    private String userName;
    private String userIntro;
    private int userVisitCount;
    private String userLatestLogin;
    private State userState;
    private String createdDatetime;
    private String updatedDatetime;
    private Provider authProvider;

    public UserVO toUserVO() {
        return UserVO.builder()
                .id(id)
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userPhone(userPhone)
                .userType(userType)
                .userName(userName)
                .userIntro(userIntro)
                .userVisitCount(userVisitCount)
                .userLatestLogin(userLatestLogin)
                .userState(userState)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
    public OAuthVO toOAuthVO() {
        return OAuthVO.builder()
                .id(id)
                .authProvider(authProvider)
                .build();
    }

}
