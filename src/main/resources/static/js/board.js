document.addEventListener("DOMContentLoaded", function () {
    var commentBtn = document.querySelector(".commentBtn");
    var commentInput = document.getElementById("comment");
    var commentAddBtn = document.querySelector(".commentAddBtn"); // .을 추가해야 합니다.

    commentBtn.addEventListener("click", function () {
        commentInput.style.display = (commentInput.style.display === "none") ? "block" : "none";
        commentAddBtn.style.display = (commentAddBtn.style.display === "none") ? "block" : "none";
    });
});
