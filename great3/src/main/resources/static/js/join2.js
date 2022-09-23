const $joinBtn = document.querySelector('.join-btn');

//입력데이터 가져오기
function getInputData() {

    const memId = document.querySelector('.memId').value;
    const memPassword = document.querySelector('.memPassword').value;
    const memName = document.querySelector('.memName').value;
    const memNickname = document.querySelector('.memNickname').value;
    const memEmail = document.querySelector('.memEmail').value;

    return {memId, memPassword, memName, memNickname, memEmail};
}

//등록버튼 클릭시
$joinBtn.addEventListener('click', e => {
    //1) 데이터 검증(유효성 체크)
    if (!validChk()) return;
    //2) 입력데이터 가져오기
    const member = getInputData();
    //3) 상품등록 처리
    join(member);
});

//회원가입 함수
function join(member) {
    const url = 'http://localhost:8080/member/join';
    fetch(url, {method: 'post', headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}, body: JSON.stringify(member)})
        .then(res => res.json())
        .then(mem => console.log('success: ', mem))
        .catch(error => console.log('error: ', error));
}

//검증 함수
function validChk() {
    //아이디 공백X
    if (!memId.value.trim()) {
        memIdErr.textContent = '필수 입력항목입니다.'
        if(!memIdErr.classList.contains('fieldError')) {
            memIdErr.classList.add('fieldError');
        }
    } else {
        memIdErr.textContent = '';
        memIdErr.classList.remove('fieldError');
    }

    //이메일 공백X
    if (!memEmail.value.trim()) {
        memEmailErr.textContent = '필수 입력항목입니다.'
        if(!memEmailErr.classList.contains('fieldError')) {
            memEmailErr.classList.add('fieldError');
        }
    } else {
        memEmailErr.textContent = '';
        memEmailErr.classList.remove('fieldError');
    }

    //이메일 형식
    const regExp = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
    if (memEmail.value.match(regExp) == null) {
        memEmailErr.textContent = '이메일 형식에 맞지 않습니다.'
        if(!memEmailErr.classList.contains('fieldError')) {
            memEmailErr.classList.add('fieldError');
        }
    } else {
        memEmailErr.textContent = '';
        memEmailErr.classList.remove('fieldError');
    }


    return true;
}