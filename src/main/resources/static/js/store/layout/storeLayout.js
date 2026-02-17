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

   return {showMarketDropdown: showMarketDropdown};
})();