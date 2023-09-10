/**
 * 
 */

function hideLoading() {
    let spinner = document.querySelector('.loading-spinner');
    spinner.style.display = "none";
}
     
    /*window.onload = hideLoading;*/
    
function showLoading() {
    let spinner = document.querySelector('.loading-spinner');
    spinner.style.display = "block"; // 스피너를 표시합니다.
    spinner.style.top = "50%"; // 화면 가운데로 이동합니다.
    spinner.style.left = "50%";
    spinner.style.transform = "translate(-50%, -50%)";
}

// 페이지 이동 시 스피너를 숨깁니다.
function navigateToJSPPage(jspPage) {
    let spinner = document.querySelector('.loading-spinner');
    spinner.style.display = "none"; // 페이지 이동 전에 스피너 숨김

    // JSP 페이지로 이동
    window.location.href = jspPage;
}

// 페이지 로드가 완료된 후 스피너를 숨깁니다.
window.onload = function () {
    setTimeout(hideLoading,1500);
};