// 기숙사 등록 폼 처리
const regForm = document.getElementById('dormRegForm');
if (regForm) {
    regForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(regForm);
        const payload = {
            studentId: formData.get('studentId'),
            type: formData.get('type')
        };
        try {
            const resp = await fetch('/dormitory/reg', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            if (resp.ok) alert('신청 완료');
            else alert('신청 실패');
        } catch(err) {
            console.error('등록 오류', err);
        }
    });
}

// 기숙사 생성 폼 처리
const createForm = document.getElementById('createDormForm');
if (createForm) {
    createForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(createForm);
        const payload = {
            cost: Number(formData.get('cost')),
            type: formData.get('type'),
            size: Number(formData.get('size')),
            startAt: new Date(formData.get('startAt')).toISOString()
        };
        try {
            const resp = await fetch('/dormitory/create', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            if (resp.ok) alert('생성 완료');
            else alert('생성 실패');
        } catch(err) {
            console.error('생성 오류', err);
        }
    });
}
