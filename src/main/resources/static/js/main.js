
document.addEventListener("DOMContentLoaded", () => {
    const carousel = document.getElementById("myCarousel");

    console.log(carousel)
    const numSlides = bannerInfo.length;

    const indicatorList = document.createElement("ul");
    indicatorList.classList.add("carousel-indicators");

    for (var i = 0; i < numSlides; i++) {
        const indicatorItem = document.createElement("li");
        indicatorItem.setAttribute("data-target", "#myCarousel");
        indicatorItem.setAttribute("data-slide-to", i);
        if (i === 0) {
            indicatorItem.classList.add("active");
        }
        indicatorList.appendChild(indicatorItem);
    }
    carousel.appendChild(indicatorList);


    const innerList = document.createElement("div")
    innerList.classList.add("carousel-inner")

    for (var j = 0; j < numSlides; j++) {
        const innerItem = document.createElement("div");
        if (j === 0) {
            innerItem.className="carousel-item active"
        }
        else {
            innerItem.className="carousel-item"
        }

        const itemContent = document.createElement("div")
        itemContent.className = "itemContent"

        const itemDesDiv = document.createElement("div")
        itemDesDiv.className = "itemDesDiv"

        const itemName = document.createElement("h2")
        itemName.className="itemName"
        itemName.textContent = bannerInfo[j].certName;
        itemDesDiv.appendChild(itemName)

        const itemHost = document.createElement("h3")
        itemHost.className="itemAuthName"
        itemHost.textContent = bannerInfo[j].host;
        itemDesDiv.appendChild(itemHost)

        const itemDescription = document.createElement("p")
        itemDescription.className = "itemDescription"
        itemDescription.textContent = bannerInfo[j].overview;
        itemDesDiv.appendChild(itemDescription)

        const itemUrlDiv = document.createElement("div")
        itemUrlDiv.className = "itemUrlDiv"
        itemDesDiv.appendChild(itemUrlDiv)

        const itemUrl = document.createElement("a")
        itemUrl.href = bannerInfo[j].url;
        itemUrl.className = "itemUrl"
        itemUrl.textContent = "자격증 상세페이지로 이동"
        itemUrlDiv.appendChild(itemUrl)

        const itemImgDiv = document.createElement("div")
        itemImgDiv.className = "itemImgDiv"

        const itemImg = document.createElement("img")
        itemImg.className = "itemImg"
        itemImg.src = `images/1.jpg`
        itemImgDiv.appendChild(itemImg)

        itemContent.appendChild(itemDesDiv)
        itemContent.appendChild(itemImgDiv)

        innerItem.appendChild(itemContent)

        innerList.appendChild(innerItem)
    }
    carousel.appendChild(innerList);
})
