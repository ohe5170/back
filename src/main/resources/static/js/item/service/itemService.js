const itemService = (() => {
    const getCategories = async (callback) => {
        const response = await fetch(`/api/item/category`);
        const categories = await response.json();
        if(callback) {
            callback(categories);
        }
    }

    const getSubCategories = async (id, callback) => {
        const response = await fetch(`/api/item/category/${id}`);
        const subCategories = await response.json();
        if(callback) {
            callback(subCategories);
        }
    }

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

    return {
        getCategories: getCategories,
        getSubCategories: getSubCategories,
        getItemDescImages: getItemDescImages,
        getItemReviews: getItemReviews
    }
})();