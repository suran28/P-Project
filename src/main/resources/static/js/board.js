document.addEventListener("DOMContentLoaded", function () {
    var commentBtn = document.querySelector(".commentBtn");
    var commentAdd = document.querySelector(".commentAdd");

    commentBtn.addEventListener("click", function () {
        commentAdd.style.display = (commentAdd.style.display === "none") ? "block" : "none";
    });
});
