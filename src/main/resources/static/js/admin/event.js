// ################################# 무한 스크롤 페이징 처리 #################################
let page = 1;
let checkScroll = true;

itemService.getList(page,
    {
        type: type.value,
        keyword: keyword.value,
    }, postLayout.showList);

window.addEventListener("scroll", async (e) => {
    if(!checkScroll || !criteria.hasMore){
        return;
    }
    // 현재 스크롤 위치
    const scrollCurrentPosition = window.scrollY;
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 높이
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때
    if(scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        checkScroll = false;
        criteria = await postService.getList(++page, {type: type.value, keyword: keyword.value}, postLayout.showList);
    }

    setTimeout(() => {
        checkScroll = true;
    }, 1000)
});