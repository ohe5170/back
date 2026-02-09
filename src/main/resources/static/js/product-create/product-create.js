// 헤더 상품 등록버튼
const submitButton = document.querySelector(".application-btn");

// 상품 이름 input
const itemNameInput = document.getElementById("itemName");
// 상품 내용 input
const itemContentInput = document.getElementById("itemContent");
// 상품 금액 input
const itemPriceInput = document.getElementById("itemPrice");
// 상품 이미지 input
const itemImagesInput = document.getElementById("itemImages");
// 상품 설명 이미지 input
const itemDescImagesInput = document.getElementById("itemDescImages");
// 상품 판매자 설명 이미지 input
const itemSellerImagesInput = document.getElementById("itemSellerImages");
// 상품 환불 설명 이미지 input
const itemRefundImagesInput = document.getElementById("itemRefundImages");

// 상품 카테고리
const itemCategory = document.getElementById("item-category");
const itemSubCategory = document.getElementById("item-sub-category");

// 상품 카테고리 선택창
const categoryA = document.querySelector(
    ".category-select-dropdown.category-a",
);
const categoryB = document.querySelector(
    ".category-select-dropdown.category-b",
);

// 상품 이미지 프리뷰 부분
const itemImageContainer = document.querySelector(".img-container");

// 상품 정보 값
let itemData = {
    itemMarketId: "",
    itemCategoryId: "",
    itemName: "",
    itemContent: "",
    itemPrice: "",
    itemImages: [],
    itemDescImages: [],
    itemSellerImages: [],
    itemRefundImages: [],
    itemOptions: [],
};

// 상품 이름 input Event
itemNameInput.addEventListener("input", (e) => {
    const formWrapper = itemNameInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;
    const valueCount = guideDiv.lastElementChild;

    let value = e.target.value;
    if (value) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        valueCount.innerHTML = `${value.length}/20`;

        itemData.itemName = value;
    } else {
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
        valueCount.innerHTML = "0/20";
    }
});

// 상품 카테고리 Event
itemCategory.addEventListener("click", (e) => {
    const categoryList = categoryA.querySelectorAll(
        ".each-category-item.item-a",
    );

    itemCategory.nextElementSibling.classList.toggle("clicked");
    categoryA.classList.toggle("off");

    categoryList.forEach((category) => {
        category.addEventListener("click", (e) => {
            itemCategory.parentElement.classList.add("selected");
            itemCategory.value = category.innerHTML;
            itemData.itemCategoryId = category.value;
            categoryA.classList.add("off");
            itemSubCategory
                .closest(".category-select-box")
                .classList.remove("disabled");
        });
    });
});

// 상세 카테고리 Event
itemSubCategory.addEventListener("click", (e) => {
    if (!itemData.itemCategoryId) return;

    const categoryList = categoryB.querySelectorAll(
        ".each-category-item.item-b",
    );

    itemSubCategory.nextElementSibling.classList.toggle("clicked");
    categoryB.classList.toggle("off");

    categoryList.forEach((category) => {
        category.addEventListener("click", (e) => {
            itemSubCategory.parentElement.classList.add("selected");
            itemSubCategory.value = category.innerHTML;
            itemData.itemCategoryId = category.value;
            categoryB.classList.add("off");
        });
    });
});

// 상품 소개 Event
itemContentInput.addEventListener("input", (e) => {
    const formWrapper = itemContentInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;
    const valueCount = guideDiv.lastElementChild;

    let value = e.target.value;
    if (value) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        valueCount.innerHTML = `${value.length}/50`;

        itemData.itemContent = value;
    } else {
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
        valueCount.innerHTML = "0/50";
    }
});

// 상품 금액 Event
itemPriceInput.addEventListener("input", (e) => {
    const formWrapper = itemPriceInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        itemPriceInput.value = parseInt(numericValue).toLocaleString();
        itemData.itemPrice = numericValue;
    } else {
        itemPriceInput.value = 0;
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
    }
});

// 상품 이미지 Event
const MAX_ITEM_IMAGES = 5;
itemImagesInput.addEventListener("change", (e) => {
    const files = Array.from(e.target.files);

    // 최대 5장 제한 체크
    const remainingSlots = MAX_ITEM_IMAGES - itemData.itemImages.length;
    const addImages = files.slice(0, remainingSlots);

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_ITEM_IMAGES}장 까지만 등록할 수 있습니다.`);
    }

    itemData.itemImages.push(...addImages);
    itemImageContainer.nextElementSibling.classList.remove("off");

    renderImageCard();

    itemImagesInput.value = "";
});

// 상품 이미지 Preview 카드 뿌리기
function renderImageCard() {
    itemImageContainer.innerHTML = "";

    if (itemData.itemImages.length === 0) {
        itemImageContainer.nextElementSibling.classList.add("off");
    }

    itemData.itemImages.forEach((img, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box";
            imgBox.dataset.index = i;

            imgBox.innerHTML = `
                <button class="img-delete-btn" data-index="${i}">
                    <svg width="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                        <path d="M504.6 148.5C515.9 134.9 514.1 114.7 500.5 103.4C486.9 92.1 466.7 93.9 455.4 107.5L320 270L184.6 107.5C173.3 93.9 153.1 92.1 139.5 103.4C125.9 114.7 124.1 134.9 135.4 148.5L278.3 320L135.4 491.5C124.1 505.1 125.9 525.3 139.5 536.6C153.1 547.9 173.3 546.1 184.6 532.5L320 370L455.4 532.5C466.7 546.1 486.9 547.9 500.5 536.6C514.1 525.3 515.9 505.1 504.6 491.5L361.7 320L504.6 148.5z"/>
                    </svg>
                </button>
                <img src="${e.target.result}" alt="preview-${i}">
            `;

            itemImageContainer.appendChild(imgBox);
        };

        reader.readAsDataURL(img);
    });
}

// 상품 프리뷰 이미지 삭제 버튼
itemImageContainer.addEventListener("click", (e) => {
    const deleteBtn = e.target.closest(".img-delete-btn");

    if (deleteBtn) {
        const index = parseInt(deleteBtn.dataset.index);

        itemData.itemImages.splice(index, 1);

        renderImageCard();
    }
});

// 상품 설명 이미지 Event 부분들
const MAX_DESC_IMAGES = 3;

// 각 input의 preview 컨테이너 선택
const descPreviewContainer = itemDescImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");
const sellerPreviewContainer = itemSellerImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");
const refundPreviewContainer = itemRefundImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");

// 공통 이미지 처리 함수
function handleImageUpload(files, targetArray, previewContainer) {
    const remainingSlots = MAX_DESC_IMAGES - targetArray.length;
    const filesToAdd = Array.from(files).slice(0, remainingSlots);

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_DESC_IMAGES}장까지만 업로드 가능합니다.`);
    }

    targetArray.push(...filesToAdd);
    renderDescImage(targetArray, previewContainer);
}

// 공통 렌더링 함수
function renderDescImage(imageArray, container) {
    container.innerHTML = "";

    imageArray.forEach((file, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box";
            imgBox.dataset.index = i;
            imgBox.style.order = i;

            imgBox.innerHTML = `
                <img src="${e.target.result}" alt="preview-${i}">
                <button class="img-delete-btn" data-index="${i}">
                    <svg width="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                        <path d="M504.6 148.5C515.9 134.9 514.1 114.7 500.5 103.4C486.9 92.1 466.7 93.9 455.4 107.5L320 270L184.6 107.5C173.3 93.9 153.1 92.1 139.5 103.4C125.9 114.7 124.1 134.9 135.4 148.5L278.3 320L135.4 491.5C124.1 505.1 125.9 525.3 139.5 536.6C153.1 547.9 173.3 546.1 184.6 532.5L320 370L455.4 532.5C466.7 546.1 486.9 547.9 500.5 536.6C514.1 525.3 515.9 505.1 504.6 491.5L361.7 320L504.6 148.5z"/>
                    </svg>
                </button>
            `;

            container.appendChild(imgBox);
        };

        reader.readAsDataURL(file);
    });

    // input 초기화
    const input = container
        .closest(".uploader-container")
        .querySelector('input[type="file"]');
    if (input) input.value = "";
}

// 공통 삭제 함수
function deletDescImages(e, imageArray, previewContainer) {
    const deleteBtn = e.target.closest(".img-delete-btn");

    if (deleteBtn) {
        const index = parseInt(deleteBtn.dataset.index);
        imageArray.splice(index, 1);
        renderDescImage(imageArray, previewContainer);
    }
}

// 상품 설명 이미지 이벤트
itemDescImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemData.itemDescImages,
        descPreviewContainer,
    );
});

descPreviewContainer.addEventListener("click", (e) => {
    deletDescImages(e, itemData.itemDescImages, descPreviewContainer);
});

// 판매자 소개 이미지 이벤트
itemSellerImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemData.itemSellerImages,
        sellerPreviewContainer,
    );
});

sellerPreviewContainer.addEventListener("click", (e) => {
    deletDescImages(e, itemData.itemSellerImages, sellerPreviewContainer);
});

// 환불/교환 정책 이미지 이벤트
itemRefundImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemData.itemRefundImages,
        refundPreviewContainer,
    );
});

refundPreviewContainer.addEventListener("click", (e) => {
    deletDescImages(e, itemData.itemRefundImages, refundPreviewContainer);
});

// 이미지들 누르면 원본 크기로 보이게 하기
const imageViewer = document.querySelector(".item-image-viewer");
const viewerBackdrop = document.querySelector(".viewer-backdrop");
const viewerContainer = document.querySelector(".viewer-container");
const viewerCloseBtn = document.querySelector(".viewer-close-btn");

// 해당 이미지 더클클릭하면 원본 이미지 보이기
document.addEventListener("dblclick", (e) => {
    const imgBox = e.target.closest(".img-box");

    if (imgBox) {
        const img = imgBox.querySelector("img");

        if (img && img.src) {
            openImageViewer(img.src);
        }
    }
});

// 모달 열기
function openImageViewer(imageSrc) {
    viewerContainer.innerHTML = `<img src="${imageSrc}" alt="원본 이미지">`;
    imageViewer.classList.remove("off");

    // body 스크롤 방지
    document.body.style.overflow = "hidden";
}

// 모달 닫기
function closeImageViewer() {
    imageViewer.classList.add("off");
    viewerContainer.innerHTML = "";

    // body 스크롤 복원
    document.body.style.overflow = "";
}

// 닫기 버튼 클릭
viewerCloseBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    closeImageViewer();
});

// 배경 클릭 시 닫기
viewerBackdrop.addEventListener("click", (e) => {
    if (e.target === viewerBackdrop) {
        closeImageViewer();
    }
});

// 상품 옵션이 없으면 리스트 안 보이기
if (itemData.itemOptions.length === 0) {
    const optionList = document.querySelector(".item-option-list");
    optionList.classList.add("off");
}
