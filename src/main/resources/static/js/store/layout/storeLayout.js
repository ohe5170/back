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

      if(currentRegionText.innerHTML == '전체') {
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
      const categoryBox = document.querySelector(".dd-filter-box");

      let text = ``;
      if(categories) {
         categories.forEach(category => {
            text += `
            <li>
                <button type="button" class="dd-filter-item" value="${category.id}">${category.categoryName}</button>
            </li>
            `
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

   return {showMarketDropdown: showMarketDropdown,
            showCategories: showCategories,
            showMarketList: showMarketList,
            showList: showList};
})();