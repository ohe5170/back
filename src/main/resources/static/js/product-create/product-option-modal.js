// 상품 옵션 추가 부분
const optionAddDiv = document.getElementById("productOptionContainer");
const addedOptionsContainer = document.querySelector(".option-list-container");
const optionAddBtn = optionAddDiv.querySelector(".category-button");

// 상품 옵션 추가 모달 관련
const optionModal = document.querySelector(".item-option-modal");
const optionBackdrop = optionModal.firstElementChild;
const optionCloseBtn = optionModal.querySelector(".modal-close-btn");

// 상품 옵션 모달 input들
const optionNameInput = document.getElementById("optionName");
const optionDetailInput = document.getElementById("optionDetail");
const optionPriceInput = document.getElementById("optionPrice");
const optionSubmitBtn = optionModal.querySelector(".modal-submit-btn");

// 옵션들을 담을 곳
let options = itemData.itemOptions;

// 각 옵션을 담을 배열
let optionValues = {
    optionName: "",
    optionDetail: "",
    optionPrice: "",
};

optionAddBtn.addEventListener("click", (e) => {
    optionModal.classList.remove("off");
});

optionCloseBtn.addEventListener("click", (e) => {
    optionModal.classList.add("off");
});

optionNameInput.addEventListener("input", (e) => {
    const inputDiv = optionNameInput.parentElement;
    const inputMessage = inputDiv.parentElement.nextElementSibling;

    let value = e.target.value;

    if (value) {
        inputDiv.style.border = "1px solid rgb(228, 228, 228)";
        inputMessage.classList.add("off");

        optionValues.optionName = value;
    } else {
        inputDiv.style.border = "1px solid rgb(229, 60, 65)";
        inputMessage.classList.remove("off");
    }
});

optionDetailInput.addEventListener("input", (e) => {
    const textareaDiv = optionDetailInput.parentElement;
    const textareaMessage = textareaDiv.parentElement.nextElementSibling;

    let value = e.target.value;

    if (value) {
        optionValues.optionDetail = value;
        textareaDiv.style.border = "1px solid rgb(228, 228, 228)";
        textareaMessage.classList.add("off");
    } else {
        textareaDiv.style.border = "1px solid rgb(229, 60, 65)";
        textareaMessage.classList.remove("off");
    }
});

optionPriceInput.addEventListener("input", (e) => {
    const inputDiv = optionPriceInput.parentElement;
    const inputMessage = inputDiv.parentElement.nextElementSibling;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        optionPriceInput.value = parseInt(numericValue).toLocaleString();
        optionValues.optionPrice = numericValue;
        inputDiv.style.border = "1px solid rgb(228, 228, 228)";
        inputMessage.classList.add("off");
    } else {
        optionPriceInput.value = 0;
        inputDiv.style.border = "1px solid rgb(229, 60, 65)";
        inputMessage.classList.remove("off");
    }
});

// 옵션 추가 버튼 기능
optionSubmitBtn.addEventListener("click", (e) => {
    const isEmpty = Object.values(optionValues).some(
        (value) => !value || value.trim() === "",
    );

    if (isEmpty) {
        alert("모든 옵션 항목을 입력해주세요.");
        return;
    }

    itemData.itemOptions.push({ ...optionValues });

    optionValues.optionName = "";
    optionValues.optionDetail = "";
    optionValues.optionPrice = "";

    optionNameInput.value = "";
    optionDetailInput.value = "";
    optionPriceInput.value = "";

    generateOptionCard();
    optionModal.classList.add("off");
});

// 옵션 뿌리기 기능
// -- TODO --
// 돌아왔을 때, 출력되게 하기
function generateOptionCard() {
    const optionListContainer = document.querySelector(
        ".option-list-container",
    );

    optionListContainer.innerHTML = "";

    itemData.itemOptions.forEach((option, index) => {
        console.log(option);
        const optionCard = document.createElement("div");
        optionCard.className = "option-card-wrapper";
        optionCard.dataset.index = index;

        optionCard.innerHTML = `
            <div class="option-card-container">
                <div class="option-card">
                    <div class="option-price">
                        <div class="price-wrap">${parseInt(option.optionPrice).toLocaleString()}원</div>
                        <div class="option-name">${option.optionName}</div>
                    </div>
                    <ul class="option-explain">
                        <li class="item-list">
                            <span>${option.optionDetail}</span>
                        </li>
                    </ul>
                </div>
                <button class="option-delete-button">
                    <div class="option-delete-icon">
                        <svg viewBox="0 0 48 48"><path fill-rule="evenodd" clip-rule="evenodd" d="M38.814 42.172C38.814 42.946 38.064 43.574 37.144 43.574H10.856C9.936 43.574 9.186 42.946 9.186 42.172V12.218H38.814V42.172ZM17.564 4.426L30.542 4.524V9.794H17.462L17.564 4.426ZM44.786 9.794H32.968V4.524C32.968 3.13 31.832 2 30.436 2H17.564C16.168 2 15.03 3.13 15.03 4.524V9.794H3.212C2.542 9.794 2 10.336 2 11.006C2 11.676 2.542 12.218 3.212 12.218H6.76V42.172C6.76 44.284 8.598 46 10.856 46H37.144C39.402 46 41.24 44.284 41.24 42.172V12.218H44.786C45.456 12.218 46 11.676 46 11.006C46 10.336 45.456 9.794 44.786 9.794ZM18.857 36.9338C19.527 36.9338 20.069 36.3918 20.069 35.7218V20.0738C20.069 19.4038 19.527 18.8618 18.857 18.8618C18.187 18.8618 17.645 19.4038 17.645 20.0738V35.7218C17.645 36.3918 18.187 36.9338 18.857 36.9338ZM30.3542 35.7218C30.3542 36.3918 29.8122 36.9338 29.1422 36.9338C28.4722 36.9338 27.9302 36.3918 27.9302 35.7218V20.0738C27.9302 19.4038 28.4722 18.8618 29.1422 18.8618C29.8122 18.8618 30.3542 19.4038 30.3542 20.0738V35.7218Z"></path></svg>
                    </div>
                </button>
            </div>
        `;
        console.log(optionCard);

        optionListContainer.appendChild(optionCard);
    });
}

// 옵션 삭제 버튼 구현
const optionListDiv = document.querySelector(".option-list-container");

optionListDiv.addEventListener("click", (e) => {
    const removeBtn = e.target.closest(".option-delete-button");

    console.log(removeBtn);
});
