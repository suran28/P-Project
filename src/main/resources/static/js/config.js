const defaultHeaders = new Headers();
defaultHeaders.append('Content-Type', 'application/json');
defaultHeaders.append('Custom-Header', 'header-value');

// fetch 함수를 래핑하여 기본 헤더를 추가하는 함수
function fetchWithHeaders(url, options) {
    // 기본 헤더 복사
    const headers = new Headers(defaultHeaders);

    // 옵션에 헤더가 정의되어 있다면 추가
    if (options && options.headers) {
        for (const [key, value] of options.headers.entries()) {
            headers.append(key, value);
        }
    }

    // 새로운 옵션 객체 생성
    const newOptions = { ...options, headers };

    // fetch 함수 호출
    return fetch(url, newOptions);
}

// 사용 예시
const apiUrl = 'https://example.com/api';
const requestData = { /* 요청 데이터 */ };

fetchWithHeaders(apiUrl, {
    method: 'GET',
    body: JSON.stringify(requestData),
})
    .then(response => response.json())
    .then(data => console.log('Response received:', data))
    .catch(error => console.error('Error during fetch:', error));