package com.app.haetssal_jangteo.service.review;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.repository.PaymentDAO;
import com.app.haetssal_jangteo.repository.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor //주입!
@Transactional(rollbackFor = Exception.class)
public class ReviewService {
    private final ReviewDAO reviewDAO;
    private final PaymentDAO paymentDAO;

//    5번탭에 거래완료된 애들 뿌리기
    public List<PaymentDTO> getCompletes(Long userId) {
        return paymentDAO.findCompletesByUserId(userId);
    }

//    리뷰쓰기
    public void addReview(ReviewDTO reviewDTO) {
        reviewDAO.saveReview(reviewDTO);

    }

//    2번탭에 작성한 후기 개수 카운트
    public int getReivewCountByUserId(Long userId) {
        return reviewDAO.findReviewCount(userId);
    }

//    2번탭에 리뷰목록 뿌리기
    public List<ReviewDTO> getReviewListsByUserId(Long userId) {
        return reviewDAO.findReviewsByUserId(userId);

    }

//
}
