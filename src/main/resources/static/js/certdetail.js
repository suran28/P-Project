document.addEventListener("DOMContentLoaded", () =>  {
    const sections = [
        { applicationdate: "2023-10-12", examinationdate: "2023-10-12"},
        { applicationdate: "2023-10-12", examinationdate: "2023-10-12"},
        { overview: '개요', content: '해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다. 해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다. 해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다.' },
        { duty: '수행직무', content: '해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다' },
        { info: '시험 정보', content: '해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다' },
        { outlook: '진로 및 전망', content: '해당 시험은 무슨 무슨 시험이다 이게 뭘까 뭔가 시험에 대한 설명을 담고 있으면 좋겠다' }
    ];

    const schedules = sections.slice(0, 2);
    const certifications = sections.slice(2);

    const leftCon = document.querySelector('.leftCon');

    schedules.forEach(schedule => {
        const newDate = document.createElement('div');
        newDate.classList.add('date');

        const sessionElement = document.createElement('div');
        sessionElement.classList.add('session');

        const sessionTitle = document.createElement('h4');
        sessionTitle.textContent = 'N 회 시험일정';

        const sessionDate = document.createElement('div');
        sessionDate.classList.add('sessionDate');

        const applicationDate = document.createElement('p');
        applicationDate.textContent = `접수일 | ${schedule.applicationdate}`;

        const examinationDate = document.createElement('p');
        examinationDate.textContent = `응시일 | ${schedule.examinationdate}`;

        sessionElement.appendChild(sessionTitle);
        sessionDate.appendChild(applicationDate);
        sessionDate.appendChild(examinationDate);

        newDate.appendChild(sessionElement);
        newDate.appendChild(sessionDate);

        leftCon.appendChild(newDate);
    });

    const rightCon = document.querySelector('.rightCon');

    certifications.forEach(section => {
        // section 객체의 키를 활용하여 동적으로 클래스명 생성
        const sectionKey = Object.keys(section)[0]; // 키 추출
        const sectionClass = sectionKey.toLowerCase(); // 소문자로 변환

        const newSection = document.createElement('div');
        newSection.classList.add(sectionClass);

        const titleElement = document.createElement('h4');
        titleElement.textContent = section[sectionKey]; // 키에 해당하는 값 사용

        const certInfoElement = document.createElement('div');
        certInfoElement.classList.add('certInfo');

        const contentElement = document.createElement('p');
        contentElement.textContent = section.content;

        certInfoElement.appendChild(contentElement);
        newSection.appendChild(titleElement);
        newSection.appendChild(certInfoElement);

        rightCon.appendChild(newSection);
    });
})

