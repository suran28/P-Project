document.addEventListener("DOMContentLoaded", () =>  {

    const certTitle = document.querySelector(".certTitle")

    const certTitleText = document.createElement("h2")
    certTitleText.textContent = certDetail.certName
    certTitle.appendChild(certTitleText)

    const rightCon = document.querySelector(".rightCon")

    const overview = document.createElement("div")
    overview.className = "overview"
    rightCon.appendChild(overview)

    const overviewText = document.createElement("h4")
    overviewText.textContent = "개요"
    overview.appendChild(overviewText)

    const overviewInfo = document.createElement("div")
    overviewInfo.className = "certInfo"
    overview.appendChild(overviewInfo)

    const overviewInfoText = document.createElement("p")
    overviewInfoText.textContent = certDetail.overview
    overviewInfo.appendChild(overviewInfoText)


    const duty = document.createElement("div")
    duty.className = "duty"
    rightCon.appendChild(duty)

    const dutyText = document.createElement("h4")
    dutyText.textContent = "수행직무"
    duty.appendChild(dutyText)

    const dutyInfo = document.createElement("div")
    dutyInfo.className = "certInfo"
    duty.appendChild(dutyInfo)

    const dutyInfoText = document.createElement("p")
    dutyInfoText.textContent = certDetail.jobInfo
    dutyInfo.appendChild(dutyInfoText)


    const test = document.createElement("div")
    test.className = "test"
    rightCon.appendChild(test)

    const testText = document.createElement("h4")
    testText.textContent = "시험 정보"
    test.appendChild(testText)

    const testInfo = document.createElement("div")
    testInfo.className = "certInfo"
    test.appendChild(testInfo)

    const w_subject = document.createElement("p")
    w_subject.textContent = certDetail.w_subject
    testInfo.appendChild(w_subject)
    const w_testInfo = document.createElement("p")
    w_testInfo.textContent = certDetail.w_testInfo
    testInfo.appendChild(w_testInfo)
    const w_criteria = document.createElement("p")
    w_criteria.textContent = certDetail.w_criteria
    testInfo.appendChild(w_criteria)

    const p_subject = document.createElement("p")
    p_subject.textContent = certDetail.p_subject
    testInfo.appendChild(p_subject)
    const p_testInfo = document.createElement("p")
    p_testInfo.textContent = certDetail.p_testInfo
    testInfo.appendChild(p_testInfo)
    const p_criteria = document.createElement("p")
    p_criteria.textContent = certDetail.p_criteria
    testInfo.appendChild(p_criteria)


    const outlook = document.createElement("div")
    outlook.className = "outlook"
    rightCon.appendChild(outlook)

    const outlookText = document.createElement("h4")
    outlookText.textContent = "진로 및 전망"
    outlook.appendChild(outlookText)

    const outlookInfo = document.createElement("div")
    outlookInfo.className = "certInfo"
    outlook.appendChild(outlookInfo)

    const outlookInfoText = document.createElement("p")
    outlookInfoText.textContent = certDetail.prospect
    outlookInfo.appendChild(outlookInfoText)

    const leftCon = document.querySelector('.leftCon');


})

