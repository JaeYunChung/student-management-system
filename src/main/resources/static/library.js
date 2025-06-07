const borrowForm = document.getElementById('borrowForm');
if (borrowForm) {
    borrowForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const title = borrowForm.querySelector('[name="title"]').value;
        try {
            const resp = await fetch('/api/book/borrow', {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title })
            });
            if (resp.ok) alert('대출 완료');
            else alert('대출 실패');
        } catch(err) {
            console.error('대출 오류', err);
        }
    });
}
