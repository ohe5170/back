package com.app.haetssal_jangteo.service.user;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.repository.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //주입!
@Transactional(rollbackFor = Exception.class)
public class UserService {
//    주입!
    private final UserDAO userDAO;


//    햇살로 회원가입
    public void haetssalJoin(UserDTO userDTO) {
//        아이디어떻게할지 논의
        userDTO.setAuthProvider(Provider.HAETSSAL);
        userDAO.save(userDTO);
        userDAO.saveOAuth(userDTO.toOAuthVO());
    }

//    카카오로 회원가입
//        아이디어떻게할지 논의
    public void kakaoJoin(UserDTO userDTO) {
        userDTO.setAuthProvider(Provider.SOCIAL);
        userDAO.save(userDTO);
        userDAO.saveOAuth(userDTO.toOAuthVO());
    }

//    이메일검사. 쓸숭있나요? => true
    public boolean checkEmail(String memberEmail) {
        return userDAO.findByUserEmail(memberEmail).isEmpty();
    }
}
