const userService = (() => {
    const checkEmail = async (userEmail, callback) => {
        const response = await fetch(`/login/check-email?userEmail=${userEmail}`)
        const isAvaliable = await response.text() === "true"

        if(callback){
            callback(isAvaliable);
        }
    }

    return {checkEmail: checkEmail};
})()