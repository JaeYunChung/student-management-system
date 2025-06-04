// 홈으로 이동하는 함수
function goHome() {
    window.location.href = '/';
}

// 서비스 표시 함수
function showService(serviceName) {
    // 여기에 각 서비스별 컨텐츠를 표시하는 로직 구현
    console.log(`${serviceName} 서비스가 선택되었습니다.`);
}

// 로그인 버튼 이벤트 리스너
document.getElementById('loginBtn').addEventListener('click', function() {
    // 로그인 모달 또는 페이지 표시 로직
    console.log('로그인 버튼이 클릭되었습니다.');
});

// 페이지 로드 시 초기화
document.addEventListener('DOMContentLoaded', function() {
    // 시간표 초기화
    initializeTimetable();
    
    // 공지사항 로드
    loadNotices();
});

// 시간표 초기화 함수
function initializeTimetable() {
    const timetableContent = document.getElementById('timetableContent');
    // 시간표 데이터 로드 및 표시 로직
}

// 공지사항 로드 함수
function loadNotices() {
    const noticeList = document.getElementById('noticeList');
    // 공지사항 데이터 로드 및 표시 로직
}

// 학생 정보 표시 함수
function displayStudentInfo(studentData) {
    document.getElementById('studentId').textContent = studentData.id;
    document.getElementById('studentName').textContent = studentData.name;
    document.getElementById('department').textContent = studentData.department;
    document.getElementById('grade').textContent = studentData.grade;
    document.getElementById('studentDetails').classList.remove('hidden');
}