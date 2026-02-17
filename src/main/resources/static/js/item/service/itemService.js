const itemService = (() => {
    const getItemDescImages = async (id, callback) => {

        const response = await fetch(`/api/item/images/${id}`)
        const itemImages = await response.json();
        if(callback) {
            callback(itemImages);
        }
    }

    const getItemReviews = async (id, callback) => {
        const response = await fetch(`/api/item/reviews/${id}`)
        // const reviews = await response.json();
        const reviews = null;
        if(callback)
            callback(reviews);
    }

    return {getItemDescImages: getItemDescImages,
            getItemReviews: getItemReviews}
})();