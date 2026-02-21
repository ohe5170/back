const itemLayout = (() => {
    const stateToLabel = { pending: '판매 대기', active: '판매 중', inactive: '판매 중단' };
    const typeToLabel  = { normal: '일반', deadline: '임박 마감' };

    const showList = (itemWithPaging) => {
        const listContainer = document.getElementById('item-list');
        const totalEl       = document.getElementById('item-total');
        const pagination    = document.getElementById('item-pagination');

        const items    = itemWithPaging.items || [];
        const criteria = itemWithPaging.criteria;

        if (totalEl) totalEl.textContent = itemWithPaging.total ?? 0;

        if (items.length === 0) {
            listContainer.innerHTML = '<p class="EmptyMessage">등록된 상품이 없습니다.</p>';
            if (pagination) pagination.innerHTML = '';
            return criteria;
        }

        let text = '';
        items.forEach((item) => {
            const stateVal = item.itemState || '';
            const typeVal  = item.itemType  || '';
            text += `
                <div class="MainContent-ItemList item-row"
                     data-id="${item.id}"
                     data-name="${item.itemName || ''}"
                     data-price="${item.itemPrice ?? ''}"
                     data-stock="${item.itemStock ?? ''}"
                     data-delivery-fee="${item.itemDeliveryFee ?? ''}"
                     data-type="${typeVal}"
                     data-category="${item.categoryName || ''}"
                     data-state="${stateVal}"
                     data-created="${item.createdDatetime || ''}"
                     data-updated="${item.updatedDatetime || ''}">
                    <div class="ItemList-MainRightInfo">
                        <div class="ItemList-MainInfoList">
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 번호</p>
                                <p class="MainInfoList-Value">${item.id}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 이름</p>
                                <p class="MainInfoList-Value">${item.itemName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">가격 (원)</p>
                                <p class="MainInfoList-Value">${item.itemPrice ?? '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 유형</p>
                                <p class="MainInfoList-Value">${typeToLabel[typeVal] || typeVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">카테고리</p>
                                <p class="MainInfoList-Value">${item.categoryName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상태</p>
                                <p class="MainInfoList-Value">${stateToLabel[stateVal] || stateVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">등록 일</p>
                                <p class="MainInfoList-Value">${item.createdDatetime || '-'}</p>
                            </div>
                        </div>
                        <button type="button" class="Row-EditBtn item-edit-btn">수정</button>
                    </div>
                </div>
            `;
        });

        if (criteria && criteria.page === 1) {
            listContainer.innerHTML = text;
        } else {
            listContainer.innerHTML += text;
        }

        return criteria;
    };

    return { showList };
})();







const marketLayout = (() => {
    const stateToLabel = { pending: '입점 대기', active: '운영 중', inactive: '운영 중단' };

    const showList = (marketWithPaging) => {
        const listContainer = document.getElementById('market-list');
        const totalEl       = document.getElementById('market-total');
        const pagination    = document.getElementById('market-pagination');

        const markets  = marketWithPaging.markets || [];
        const criteria = marketWithPaging.criteria;

        if (totalEl) totalEl.textContent = marketWithPaging.total ?? 0;

        if (markets.length === 0) {
            listContainer.innerHTML = '<p class="EmptyMessage">등록된 가게가 없습니다.</p>';
            if (pagination) pagination.innerHTML = '';
            return criteria;
        }

        let text = '';
        markets.forEach((market) => {
            const stateVal = market.marketState?.value || market.marketState || '';
            text += `
                <div class="MainContent-ItemList market-row"
                     data-id="${market.id}"
                     data-name="${market.marketName || ''}"
                     data-region="${market.marketRegion || ''}"
                     data-location="${market.marketLocation || ''}"
                     data-state="${stateVal}"
                     data-created="${market.createdDatetime || ''}"
                     data-updated="${market.updatedDatetime || ''}">
                    <div class="ItemList-MainRightInfo">
                        <div class="ItemList-MainInfoList">
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">가게 번호</p>
                                <p class="MainInfoList-Value">${market.id}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">가게 이름</p>
                                <p class="MainInfoList-Value">${market.marketName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">위치</p>
                                <p class="MainInfoList-Value">${market.marketLocation || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">지역</p>
                                <p class="MainInfoList-Value">${market.marketRegion || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상태</p>
                                <p class="MainInfoList-Value">${stateToLabel[stateVal] || stateVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">등록 일</p>
                                <p class="MainInfoList-Value">${market.createdDatetime || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">정보 수정일</p>
                                <p class="MainInfoList-Value">${market.updatedDatetime || '-'}</p>
                            </div>
                        </div>
                        <button type="button" class="Row-EditBtn market-edit-btn">수정</button>
                    </div>
                </div>
            `;
        });

        if (criteria && criteria.page === 1) {
            listContainer.innerHTML = text;
        } else {
            listContainer.innerHTML += text;
        }

        return criteria;
    };

    return { showList };
})();

// 현재 활성화된 섹션
let currentSection = 'home';

// 모든 메인 섹션 name 목록
const SECTIONS = ['home', 'user', 'market', 'item', 'report'];

// 사이드 메뉴 name → 섹션 name 매핑
const MENU_MAP = {
    'homeMenu':   'home',
    'userMenu':   'user',
    'marketMenu': 'market',
    'itemMenu':   'item',
    'reportMenu': 'report',
};

// 특정 섹션만 보이게 하고 나머지 숨기기
function showSection(sectionName) {
    SECTIONS.forEach(name => {
        const el = document.querySelector(`[name="${name}"]`);
        if (el) {
            if (name === sectionName) {
                el.classList.remove('off');
            } else {
                el.classList.add('off');
            }
        }
    });
    currentSection = sectionName;
}

// 사이드 메뉴 활성화 스타일 적용
function setActiveMenu(menuName) {
    Object.keys(MENU_MAP).forEach(name => {
        const menuEl = document.querySelector(`[name="${name}"] .SideMenu-Icon`);
        if (menuEl) {
            if (name === menuName) {
                menuEl.classList.add('current');
            } else {
                menuEl.classList.remove('current');
            }
        }
    });
}

// 사이드 메뉴 클릭 이벤트 등록
function initSidebarMenu() {
    Object.entries(MENU_MAP).forEach(([menuName, sectionName]) => {
        const menuEl = document.querySelector(`[name="${menuName}"]`);
        if (menuEl) {
            menuEl.addEventListener('click', () => {
                showSection(sectionName);
                setActiveMenu(menuName);
            });
        }
    });
}

// 모달 열기
function openModal(modalName) {
    const modal = document.querySelector(`[name="${modalName}"]`);
    if (modal) {
        modal.classList.remove('off');
    }
}

// 모달 닫기
function closeModal(modalName) {
    const modal = document.querySelector(`[name="${modalName}"]`);
    if (modal) {
        modal.classList.add('off');
    }
}

// 모달 닫기 버튼 등록
function initModalClose() {
    const modals = ['user-edit', 'market-edit', 'item-edit', 'report-edit'];
    modals.forEach(modalName => {
        const modal = document.querySelector(`[name="${modalName}"]`);
        if (!modal) return;

        // X 버튼
        const closeBtn = modal.querySelector('.EditModal-CloseButton');
        if (closeBtn) {
            closeBtn.addEventListener('click', () => closeModal(modalName));
        }

        // 모달 바깥(레이아웃) 클릭 시 닫기
        modal.addEventListener('click', (e) => {
            if (e.target === modal) closeModal(modalName);
        });
    });
}

// 수정 모달 내 input 활성화/비활성화 토글
function initEditModalToggle() {
    const modals = ['user-edit', 'market-edit', 'item-edit'];
    modals.forEach(modalName => {
        const modal = document.querySelector(`[name="${modalName}"]`);
        if (!modal) return;

        const editBtn = modal.querySelector('.EditModal-Button.Edit');
        if (!editBtn) return;

        editBtn.addEventListener('click', () => {
            const inputs = modal.querySelectorAll('.EditModal-Input:not([id$="-id"]):not([id$="-createdAt"]):not([id$="-updatedAt"])');
            const selects = modal.querySelectorAll('.EditModal-SelectList');

            inputs.forEach(input => {
                if (input.disabled) {
                    input.disabled = false;
                    input.classList.remove('disabled');
                } else {
                    input.disabled = true;
                    input.classList.add('disabled');
                }
            });
        });
    });
}

// 드롭다운 select 옵션 토글
function initSelectDropdowns() {
    document.querySelectorAll('.EditModal-SelectContent').forEach(selectContent => {
        selectContent.addEventListener('click', () => {
            const list = selectContent.nextElementSibling;
            if (list && list.classList.contains('EditModal-SelectList')) {
                list.classList.toggle('off');
            }
        });
    });

    // 옵션 선택
    document.querySelectorAll('.EditModal-Option').forEach(option => {
        option.addEventListener('click', () => {
            const list = option.closest('.EditModal-SelectList');
            const selectText = list?.previousElementSibling?.querySelector('.EditModal-SelectText');
            if (selectText) {
                selectText.textContent = option.querySelector('p')?.textContent || '';
            }
            list?.classList.add('off');
        });
    });
}

// 초기 레이아웃 설정
function initLayout() {
    // 홈 제외 모든 섹션 숨기기
    showSection('home');
    setActiveMenu('homeMenu');

    initSidebarMenu();
    initModalClose();
    initEditModalToggle();
    initSelectDropdowns();
}
/**/


const itemLayout = (() => {

    const stateToLabel = { pending: '판매 대기', active: '판매 중', inactive: '판매 중단' };
    const typeToLabel  = { normal: '일반', deadline: '임박 마감' };


    const showList = (itemWithPaging) => {
        const tbody = document.querySelector("#post-list tbody");

        const posts = itemWithPaging.items;
        const criteria = itemWithPaging.criteria;

        let text = ``;
        posts.forEach((item) => {
            text += `
                <tr>
                    <td>${item.id}</td>
                    <td><a href="/post/detail?id=${item.id}">${item.itemTitle}</a></td>
                    <td>
                 `
            post.tags.forEach((tag) => {
                text += `${tag.tagName}, `
            });
            text = text.substring(0, text.length - 2);
            text += `
                </td>
                <td>${post.memberName}</td>
                <td>${post.createdDatetime}</td>
                <td>${post.postReadCount}</td>
                <td>
            `
            text += `</td>`;
        });
        console.log(criteria.page);
        if(criteria.page === 1) {
            tbody.innerHTML = text;
        }else{
            tbody.innerHTML += text;
        }
        return criteria;
    }

    return {showList: showList};
})();