const $joinBtn = document.querySelector('.join-btn');

//등록버튼 클릭시
$joinBtn.addEventListener('click', e => {
    // 데이터 검증(유효성 체크)
    if (!validChk()) return;
});


function validChk() {

    if (!memEmail.value.trim()) {
        memEmailErr.textContent = '필수 필드입니다.'
        if(!memEmailErr.classList.contains('fieldError')) {
            memEmailErr.classList.add('fieldError');
        }
    } else {
        memEmailErr.textContent = '';
        memEmailErr.classList.remove('fieldError');
    }

    return true;
}