const storeLayout = (() => {
   const showMarketDropdown = (markets) => {
      const marketList = document.querySelector(".category-select-dropdown.market-select");
      const ul = marketList.firstElementChild;

      let text = ``;
      if(markets) {
         markets.forEach(market => {
            text += `
            <li class="each-category-item market-id" value="${market.id}">${market.marketName}</li>
            `;
         })
      }

      ul.innerHTML = text;
   }

   const showMarketList = (markets) => {
      const currentRegionDiv = document.querySelector(".category-each-box.clicked");
      const marketContainer = currentRegionDiv.querySelector(".tab-button");
      const currentRegionText = currentRegionDiv.querySelector(".region-text");

      if(currentRegionText.innerHTML === '전체') {
         return;
      }

      let text = `<span class="dropdown-market all-seoul" value="">전체보기</span>`;
      if(markets) {
         markets.forEach(market => {
            text += `
            <span class="dropdown-market" value="${market.id}">${market.marketName}</span>
            `;
         });
      }

      marketContainer.innerHTML = text;
   }

   const showCategories = (categories) => {
      const categoryBox = document.querySelector(".dd-filter-box") || document.querySelector(".category-select-dropdown");

      let text = ``;
      if(categories) {
         categories.forEach(category => {
            if(categoryBox.classList.contains("dd-filter-box")) {
               text += `
               <li>
                   <button type="button" class="dd-filter-item" value="${category.id}">${category.categoryName}</button>
               </li>
               `
            } else {
               text += `
               <li>
                   <button type="button" class="each-category-item item-category" value="${category.id}">${category.categoryName}</button>
               </li>
               `
            }
         })
      }
      categoryBox.innerHTML = text;
   }

   const showList = (storeWithPaging) => {
      const storeContainer = document.querySelector(".card-list-wrapper");
      const storeCount = document.querySelector(".count-contents");

      const stores = storeWithPaging.stores;
      const criteria = storeWithPaging.criteria;
      const total = storeWithPaging.total;

      let count = ``;
      let text = ``;
      if(stores) {
         count = `
         <span>${total}</span>개의 상점이 있습니다.
         `;

         stores.forEach(store => {
            let srcPath = ``;
            if(store.fileSavedPath && store.fileName) {
               srcPath = `/api/files/display?filePath=${encodeURIComponent(store.fileSavedPath)}&fileName=${encodeURIComponent(store.fileName)}`;
            } else {
               srcPath = "/images/TempItem-Image.png";
            }

            text +=
                `
               <div class="market-card-wrapper">
                    <div class="each-card">
                        <a href="/store/detail?id=${store.id}" class="market-card">
                            <div class="card-image-wrap">
                                <img src="${srcPath}" class="card-image">
                            </div>
                            <!-- 이미지 아래에 정보 -->
                            <!-- 카테고리, 이름, 간단설명, 줄 긋고, 위치, 영업시간 -->
                            <div class="card-info">
                                <div class="card-market-category">${store.storeCategoryName}</div>
                                <div class="card-market-name">${store.storeName}</div>
                                <div class="card-description">${store.storeIntro}</div>
                                <div class="card-info-line"></div>
                                <div class="card-guide">
                                    <div class="guide-location">
                                        <div class="location">위치</div>
                                        <div class="market-location">${store.storeAddress}</div>
                                    </div>
                                    <div class="guide-hour">
                                        <div class="hours">상태</div>
                                        <div class="card-business-hour">${stateToString(store.storeState)}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="vertical-card-divider"></div>
                        </a>
                    </div>
                </div>
               `
         })
      } else {
         // 가게가 없다는 카드 출력
      }
      storeCount.innerHTML = count;

      console.log("page : " + criteria.page);
      if(criteria.page === 1) {
         storeContainer.innerHTML = text;
      } else {
         storeContainer.innerHTML += text;
      }

      return criteria;
   }

   const showItems = (itemWithPaging) => {
      const itemContainer = document.querySelector(".products-content-container");
      const itemCountText = itemContainer.querySelector(".products-count-box span");
      const itemBox = itemContainer.querySelector(".products-list-wrap");

      const items = itemWithPaging.items;
      const criteria = itemWithPaging.criteria;
      const total = itemWithPaging.total;

      let text = ``;
      if(items) {
         items.forEach(item => {
            const itemFiles = item.itemFiles;

            let srcPath = ``;
            if(itemFiles.length != 0) {
               srcPath = `/api/files/display?filePath=${encodeURIComponent(itemFiles[0].fileSavedPath)}&fileName=${encodeURIComponent(itemFiles[0].fileName)}`;
            } else {
               srcPath = "/images/TempItem-Image.png";
            }

            text += `
               <div class="product-card-wrapper">
                  <div class="product-each-card">
                      <!-- 바로밑에 문장은 상품상세로 이동해야함 -->
                      <div class="product-card">
                          <div class="card-image-wrap">
                              <img src="${srcPath}" class="card-image">
                              <button class="like-button">
                                  <svg class="black-heart" width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M19.463 15.3087L19.9993 15.7933L20.5356 15.3087L22.2643 13.747C22.2643 13.747 22.2643 13.7469 22.2643 13.7469C23.615 12.5269 25.9852 12.7532 27.3097 14.2118L27.3156 14.2182L27.3216 14.2246C28.9912 15.9843 29.0145 19.0158 27.2167 20.9218L19.9995 27.9864L12.7818 20.9218C10.9839 19.0158 11.0075 15.984 12.6769 14.2246L12.6829 14.2182L12.6888 14.2118C14.0133 12.7532 16.3836 12.5269 17.7343 13.7469C17.7343 13.7469 17.7343 13.7469 17.7344 13.747L19.463 15.3087Z"fill="currentColor" fill-opacity="0.25" stroke="white"stroke-width="1.6"></path><g filter="url(#filter0_dd_909_4706012)"><path fill-rule="evenodd" clip-rule="evenodd"d="M19.9992 17.111L17.0789 14.4725C17.0789 14.4725 17.079 14.4726 17.0789 14.4725C16.1903 13.67 14.4337 13.7447 13.4126 14.8691L13.3995 14.8835L13.3861 14.8976C12.0903 16.2633 12.039 18.6949 13.4805 20.2374L19.9994 26.6181L26.518 20.2374C27.9595 18.6949 27.9084 16.2637 26.6123 14.8976L26.5989 14.8835L26.5859 14.8691C25.5648 13.7447 23.8085 13.6699 22.9198 14.4725C22.9198 14.4725 22.9198 14.4724 22.9198 14.4725L19.9992 17.111ZM21.728 13.1533C23.4567 11.5918 26.3291 11.942 27.902 13.6739C29.8776 15.7563 29.8775 19.2796 27.7872 21.4827L19.9995 29.1058L12.2112 21.4827C10.1209 19.2796 10.121 15.7559 12.0965 13.6739C13.6694 11.942 16.5419 11.5917 18.2706 13.1533L19.9993 14.7151L21.728 13.1533Z"fill="white"></path></g><defs><filter id="filter0_dd_909_4706012" x="8.62891" y="10.1571"width="22.7422" height="20.9487"filterUnits="userSpaceOnUse"color-interpolation-filters="sRGB"><feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood><feColorMatrix in="SourceAlpha" type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="1"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"></feColorMatrix><feBlend mode="normal" in2="BackgroundImageFix"result="effect1_dropShadow_909_4706012"></feBlend><feColorMatrix in="SourceAlpha" type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="0.5"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.2 0"></feColorMatrix><feBlend mode="normal" in2="effect1_dropShadow_909_4706012"result="effect2_dropShadow_909_4706012"></feBlend><feBlend mode="normal" in="SourceGraphic"in2="effect2_dropShadow_909_4706012" result="shape"></feBlend></filter></defs></svg>
                                  <svg class="red-heart" width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg"><g filter="url(#filter0_dd_909_4706251)"><path fill-rule="evenodd" clip-rule="evenodd"d="M19.9993 14.7154L18.2706 13.1536C16.5419 11.592 13.6694 11.9423 12.0965 13.6742C10.121 15.7562 10.1209 19.2799 12.2112 21.483L19.9995 29.1061L27.7872 21.483C29.8775 19.2799 29.8776 15.7566 27.902 13.6742C26.3291 11.9423 23.4567 11.5921 21.728 13.1536L19.9993 14.7154Z"fill="#F86453"></path></g><defs><filter id="filter0_dd_909_4706251" x="-2" y="-2" width="44"height="44" filterUnits="userSpaceOnUse"color-interpolation-filters="sRGB"><feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood><feColorMatrix in="SourceAlpha" type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="1"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"></feColorMatrix><feBlend mode="normal" in2="BackgroundImageFix"result="effect1_dropShadow_909_4706251"></feBlend><feColorMatrix in="SourceAlpha" type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="0.5"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix"values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.2 0"></feColorMatrix><feBlend mode="normal" in2="effect1_dropShadow_909_4706251"result="effect2_dropShadow_909_4706251"></feBlend><feBlend mode="normal" in="SourceGraphic"in2="effect2_dropShadow_909_4706251" result="shape"></feBlend></filter></defs></svg>
                              </button>
                          </div>
                          <!-- 이미지 아래에 정보 -->
                          <!-- 카테고리, 이름, 간단설명, 줄 긋고, 위치, 영업시간 -->
                          <div class="card-info">
                              <div class="info-box">
                                  <div class="card-product-creator">
                                      <div class="card-creator">
                                          <div>${item.storeName}</div>
                                      </div>
                                      <div class="card-product-category">${item.itemSubCategoryName ? item.itemSubCategoryName : item.itemCategoryName}</div>
                                  </div>
                                  <div class="card-product-info">
                                      <a class="card-product-name" href="/item/detail?id=${item.id}">${item.itemName}</a>
                                      <div class="card-description">${item.itemContent}</div>
                                  </div>
                              </div>
                              <div class="card-product-price">${item.itemPrice + "원"}</div>
                              <div class="vertical-card-divider"></div>
                          </div>
                      </div>
                  </div>
               </div>
            `;
         })
      } else {
         // 상품이 없으면 없다는 카드 출력
      }
      itemCountText.innerHTML = total;

      console.log("page : " + criteria.page);
      if(criteria.page === 1) {
         itemBox.innerHTML = text;
      } else {
         itemBox.innerHTML += text;
      }

      return criteria;
   }

   const stateToString = (state) => {
      switch (state) {
         case "PENDING":
            return "심사 중"
            break;
         case "DENIED":
            return "심사 반려 됨"
            break;
         case "OPEN":
            return "운영 중"
            break;
         case "CLOSE":
            return "운영 중단"
            break;
      }
   }

   return {
      showMarketDropdown: showMarketDropdown,
      showCategories: showCategories,
      showMarketList: showMarketList,
      showList: showList,
      showItems:showItems
   };
})();