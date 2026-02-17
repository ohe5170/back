package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.domain.OAuthVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    public void insert(UserDTO userDTO);

    public void insertOauth(OAuthVO oAuthVO);

    public Optional<UserDTO> selectByEmail(String userEmail);

    public Optional<UserVO> selectUserForLogin(UserDTO userDTO);
}
