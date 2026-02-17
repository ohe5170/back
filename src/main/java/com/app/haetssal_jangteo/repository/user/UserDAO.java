package com.app.haetssal_jangteo.repository.user;

import com.app.haetssal_jangteo.domain.OAuthVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.SellerDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.mapper.SellerMapper;
import com.app.haetssal_jangteo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
//    주입!
    private final UserMapper userMapper;
    private final SellerMapper sellerMapper;

    //    이메일 검사
    public Optional<UserDTO> findByUserEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }

//    회원가입
    public void save(UserDTO userDTO){
        userMapper.insert(userDTO);
    }

    public void saveSeller(SellerDTO sellerDTO) {
        sellerMapper.insert(sellerDTO);
    }
//    oauth
    public void saveOAuth(OAuthVO oAuthVO){
        userMapper.insertOauth(oAuthVO);
    }

    // 로그인
    public Optional<UserVO> findForLogin(UserDTO userDTO) {
        return userMapper.selectUserForLogin(userDTO);
    }
}
