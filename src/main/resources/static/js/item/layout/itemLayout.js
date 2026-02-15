const itemLayout = (() => {
    const showItemDescImages = (itemImages) => {
        // 상품 설명 이미지 div
        const allInfoImageDiv = document.querySelector(".story-card-inner");
        // 상품 소개 ~ 교환/환불 div
        const descInfoDiv = allInfoImageDiv.querySelector("div[name=item-desc]");
        const sellerInfoDiv = allInfoImageDiv.querySelector("div[name=item-seller]");
        const refundInfoDiv = allInfoImageDiv.querySelector("div[name=item-refund]");

        const itemDescImages = itemImages.itemDescImages;
        const itemSellerImages = itemImages.itemSellerImages;
        const itemRefundImages = itemImages.itemRefundImages;

        let text = ``;
        // 상품 소개 이미지 뿌리기
        itemDescImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="desc" class="story-content-img">
            `;
        });
        descInfoDiv.innerHTML = text;
        text = ``;

        // 상품 판매자 이미지 뿌리기
        itemSellerImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="seller" class="story-content-img">
            `;
        });
        sellerInfoDiv.innerHTML = text;
        text = ``;

        // 상품 교환/환불 이미지 뿌리기
        itemRefundImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="refund" class="story-content-img">
            `;
        });
        refundInfoDiv.innerHTML = text;
        text = ``;
    }

    const showItemReviews = (reviews) => {
        const reviewScoreContainer = document.querySelector(".simple-reviews-container");
        const reviewContainer = document.querySelector(".detail-reviews-wrapper");

        console.log(reviews);
        let scoreText = ``;
        let reviewText = ``;
        let otherText = `
            <div class="divider-after-reviews"></div>
            <div class="show-all-review-btn-wrap">
                <button type="button" class="all-review-btn">후기 전체 보기</button>
            </div>
        `;
        if(reviews) {
            // TODO
        } else {
            reviewText = `
            <div class="empty-box">
                <div class="empty-box-wrapper">
                    <div class="empty-box-container">
                         <div class="empty-box-icon">
                             <div class="box-icon">
                                 <svg viewBox="0 0 48 48"><path d="M42.1181 14.5537C42.1557 14.5161 42.1544 14.454 42.1154 14.415L33.7715 6.07113C33.7325 6.03207 33.6704 6.03085 33.6329 6.06839L29.6905 10.0107C29.653 10.0483 29.6542 10.1103 29.6933 10.1494L38.0371 18.4933C38.0762 18.5323 38.1383 18.5335 38.1758 18.496L42.1181 14.5537Z"></path><path d="M36.134 20.5378C36.1715 20.5003 36.1703 20.4382 36.1312 20.3992C36.0922 20.3601 27.7874 12.0553 27.7874 12.0553C27.7483 12.0163 27.6862 12.015 27.6487 12.0526C27.6111 12.0901 9.6289 30.0723 9.6289 30.0723C9.61462 30.0866 9.60538 30.1052 9.6025 30.1255L8.21584 39.856C8.20646 39.9218 8.2647 39.9801 8.33053 39.9707L18.061 38.584C18.0813 38.5811 18.0999 38.5719 18.1142 38.5576C18.1142 38.5576 36.0964 20.5754 36.134 20.5378Z"></path></svg>
                             </div>
                         </div>
                    </div>
                    <div class="empty-box-text">
                    아직 해당 상품에 관한 리뷰가 없습니다.
                    </div>
                </div>
            </div>
            `;
        }

        reviewScoreContainer.innerHTML = scoreText;
        reviewContainer.innerHTML = reviewText;
    }

    return {
        showItemDescImages: showItemDescImages,
        showItemReviews: showItemReviews
    };
})();

// <!-- 하나의 리뷰 -->
// <div class="each-detail-review-wrap">
//     <div class="customer-comment-wrap">
//
//         <!-- 첫번째 이후 리뷰부터 구분선, 간격을 위한 이름없는 박스 -->
//         <div class="exist-after-first-review">
//             <div class="review-customer-profile-wrap">
//                 <div class="review-customer-profile">
//                     <img class="customer-image" src="https://cdn.tumblbug.com/profile/default_avatar.png">
//                         <div class="customer-info">
//
//                             <!-- 프로필 이름 누르면 상세페이지로 이동  -->
//                             <div class="profile-name">
//                                 <a href="https://www.naver.com" class="goto-customer-profile">망고</a>
//                             </div>
//                             <div class="count-buy-review">
//                                 <span class="count-buy">구매한 상품 11</span>
//                                 <span class="divider-in-count">·</span>
//                                 <span class="count-review">리뷰 2</span>
//                             </div>
//                         </div>
//                 </div>
//             </div>
//             <div class="review-comment-content-wrap">
//                 <div class="keywords-box-in-comment">
//                     <div class="keyword-in-comment">
//                         <p class="keyword-name">상품</p>
//                         <p class="keyword-content">보통이에요</p>
//                     </div>
//                     <div class="keyword-in-comment">
//                         <p class="keyword-name">주문/배송</p>
//                         <p class="keyword-content">만족해요</p>
//                     </div>
//
//                     <div class="keyword-in-comment">
//                         <p class="keyword-name">친절</p>
//                         <p class="keyword-content">친절했어요</p>
//                     </div>
//                 </div>
//                 <div class="review-comment">여기서 내용부분을 누르면 상세리뷰로 이동하고요. 밑에 사진을 누르면 사진 모달창이 나와요. 공백이 이상한건 pre-wrap때문에 그래요. 지금은 직접 작성할때 들여쓰기 때문에 이상한거고 서버 연결하고 사용자가 직접 작성하면 괜찮아진대요
//                     상품사진이 그 밑에 있구요. 리뷰에 사진을 안올렸으면이거 바로 밑에 상품사진 작게 나와요.
//                     그밑에 등록날짜랑 엄지있어요.여기서 내용부분을 누르면 상세리뷰로 이동하고요. 밑에 사진을 누르면 사진 모달창이 나와요. 상품사진이 그 밑에 있구요. 리뷰에 사진을 안올렸으면이거 바로 밑에 상품사진 작게 나와요. 그밑에 등록날짜랑 엄지있어요
//                 </div>
//             </div>
//
//             <!-- 리뷰 사진들 -->
//             <div class="review-image-wrap">
//                 <div class="image-list">
//                     <div>
//                         <img class="review-image" src="https://img.tumblbug.com/eyJidWNrZXQiOiJ0dW1ibGJ1Zy1pbWctcHJvZCIsImtleSI6InJldmlldy8yMTM1MTYzMS1hYjVhLTQ1M2ItOWRkMy03OGIwNzEzMzJiZTIucG5nIiwiZWRpdHMiOnsicmVzaXplIjp7IndpZHRoIjoxMjQwLCJoZWlnaHQiOm51bGwsIndpdGhvdXRFbmxhcmdlbWVudCI6dHJ1ZSwiZml0IjpudWxsfSwicm91bmRDcm9wIjpmYWxzZSwicm90YXRlIjpudWxsfX0=">
//                     </div>
//                     <div>
//                         <img class="review-image" src="https://img.tumblbug.com/eyJidWNrZXQiOiJ0dW1ibGJ1Zy1pbWctcHJvZCIsImtleSI6InJldmlldy8yMTM1MTYzMS1hYjVhLTQ1M2ItOWRkMy03OGIwNzEzMzJiZTIucG5nIiwiZWRpdHMiOnsicmVzaXplIjp7IndpZHRoIjoxMjQwLCJoZWlnaHQiOm51bGwsIndpdGhvdXRFbmxhcmdlbWVudCI6dHJ1ZSwiZml0IjpudWxsfSwicm91bmRDcm9wIjpmYWxzZSwicm90YXRlIjpudWxsfX0=">
//                     </div>
//                     <div>
//                         <img class="review-image" src="https://img.tumblbug.com/eyJidWNrZXQiOiJ0dW1ibGJ1Zy1pbWctcHJvZCIsImtleSI6InJldmlldy8yMTM1MTYzMS1hYjVhLTQ1M2ItOWRkMy03OGIwNzEzMzJiZTIucG5nIiwiZWRpdHMiOnsicmVzaXplIjp7IndpZHRoIjoxMjQwLCJoZWlnaHQiOm51bGwsIndpdGhvdXRFbmxhcmdlbWVudCI6dHJ1ZSwiZml0IjpudWxsfSwicm91bmRDcm9wIjpmYWxzZSwicm90YXRlIjpudWxsfX0=">
//                     </div>
//
//                 </div>
//             </div>
//
//             <!-- 리뷰한 상품임. 누르면 상품상세로 이동 -->
//             <div class="review-item-info">
//                 <a class="product-link-in-review" href="https://tumblbug.com/runwalk3">
//                     <img class="goto-product-from-review-image" src="https://img.tumblbug.com/eyJidWNrZXQiOiJ0dW1ibGJ1Zy1pbWctcHJvZCIsImtleSI6ImNvdmVyL2UwZGNiYTRmLTA1YTQtNDc4Yi05YjkyLTZmNmUwNzQ4NjdmMi9hYTY1YzJmOC0zZTE3LTRjNjYtOGQ1Yy1lMmQ5MDM0MDg3ODEuanBlZyIsImVkaXRzIjp7InJlc2l6ZSI6eyJ3aWR0aCI6NjIwLCJoZWlnaHQiOjQ2NSwid2l0aG91dEVubGFyZ2VtZW50Ijp0cnVlLCJmaXQiOm51bGx9LCJyb3VuZENyb3AiOmZhbHNlLCJyb3RhdGUiOm51bGx9fQ==">
//                         <div class="product-name-nextto-image">떡볶이</div>
//                 </a>
//             </div>
//             <div class="thumbs-wrap">
//                 <div class="thumbs-box">
//                     <div class="review-date">
//                         <span>2026.01.22</span>
//                     </div>
//                     <div class="review-thumbs">
//                         <div class="thumbs">
//                             <div class="each-thumb thumb-down">
//                                 <svg height="24" width="24" class="ThumbsUpButton" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M27.9591 9L18.1818 19.052V37H34.9682L40 24.96V18.8H28.6682L30.1955 11.268L27.9591 9ZM10 20.2H15.4545V37H10V20.2Z" fill="#3D3D3D"></path></svg>
//                             </div>
//                             <div class="each-thumb thumb-up">
//                                 <svg height="24" width="24" class="ThumbsUpButton" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M27.9591 9L18.1818 19.052V37H34.9682L40 24.96V18.8H28.6682L30.1955 11.268L27.9591 9ZM10 20.2H15.4545V37H10V20.2Z" fill="#3D3D3D"></path></svg>
//                                 <span class="thumb-up-count">3</span>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     </div>
//     <div class="seller-comment-wrap"></div>
// </div>