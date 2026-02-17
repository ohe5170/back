const storeService = (() => {
    const getMarkets = async (region, callback) => {
        const response = await fetch(`/api/store/region/${region}`);
        const markets = await response.json();
        if(callback) {
            callback(markets);
        }
    }

    return {getMarkets: getMarkets};
})();