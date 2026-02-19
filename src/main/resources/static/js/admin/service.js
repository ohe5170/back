const itemService = (() => {

    // ===== 상품 목록 조회 =====
    const getList = async (page, keyword, type, callback) => {
        page = page || 1;
        keyword = keyword || "";
        type = type || "";

        let queryString = `?keyword=${encodeURIComponent(keyword)}`;
        queryString += `&type=${encodeURIComponent(type)}`;

        const response = await fetch(`/admin/api/items/${page}${queryString}`);
        const itemWithPaging = await response.json();

        if (callback) {
            return callback(itemWithPaging);
        }

        return itemWithPaging;
    };


    // ===== 상품 단건 조회 =====
    const getOne = async (id, callback) => {

        const response = await fetch(`/admin/api/items/${id}`);
        const item = await response.json();

        if (callback) {
            return callback(item);
        }

        return item;
    };


    // ===== 상품 수정 =====
    const update = async (itemData, callback) => {

        const response = await fetch(`/admin/api/items/${itemData.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(itemData)
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    // ===== 상품 상태 변경 =====
    const updateState = async (id, state, callback) => {

        const response = await fetch(`/admin/api/items/${id}/state`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ itemState: state })
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    return {
        getList,
        getOne,
        update,
        updateState
    };

})();

const marketService = (() => {

    // ===== 시장 목록 조회 =====
    const getList = async (page, keyword, region, type, state, callback) => {

        page = page || 1;
        keyword = keyword || "";
        region = region || "";
        type = type || "";
        state = state || "";

        let queryString = `?keyword=${encodeURIComponent(keyword)}`;
        queryString += `&region=${encodeURIComponent(region)}`;
        queryString += `&type=${encodeURIComponent(type)}`;
        queryString += `&state=${encodeURIComponent(state)}`;

        const response = await fetch(`/admin/api/markets/${page}${queryString}`);
        const marketWithPaging = await response.json();

        if (callback) {
            return callback(marketWithPaging);
        }

        return marketWithPaging;
    };


    // ===== 시장 단건 조회 =====
    const getOne = async (id, callback) => {

        const response = await fetch(`/admin/api/markets/${id}`);
        const market = await response.json();

        if (callback) {
            return callback(market);
        }

        return market;
    };


    // ===== 시장 수정 =====
    const update = async (marketData, callback) => {

        const response = await fetch(`/admin/api/markets/${marketData.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(marketData)
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    return {
        getList,
        getOne,
        update
    };

})();
