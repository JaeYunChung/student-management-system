package com.example.studentmanagementsystem.library;

import com.example.studentmanagementsystem.firebase.FCMRequest;
import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.firebase.repository.FirebaseTokenRepository;
import com.example.studentmanagementsystem.firebase.service.FcmService;
import com.example.studentmanagementsystem.library.repository.BookRepository;
import com.example.studentmanagementsystem.library.repository.BookStudentMappingRepository;
import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class BookService {
    private final BookStudentMappingRepository bookStudentMappingRepository;
    private final BookRepository bookRepository;
    //private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final FcmService fcmService;
    private final MemberRepository memberRepository;
    private final FirebaseTokenRepository firebaseTokenRepository;

//    @Transactional
//    public BookMemberMapping borrowBook(String title){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member = memberRepository.findByUsername(authentication.getName());
//        BookEntity book = bookRepository.findByTitle(title);
//        if (book.getSpare() == 0){
//            throw new IllegalArgumentException("남은 도서가 없습니다.");
//        }
//        BookMemberMapping bookMemberMapping = new BookMemberMapping(book, member);
//        bookRepository.updateBookSpare(book); //
//        saveFCMRequestMessage(member);
//        return bookStudentMappingRepository.save(bookMemberMapping);
//    }
//    private void saveFCMRequestMessage(Member member){
//        long limit = Instant.now().plus(5, ChronoUnit.DAYS).toEpochMilli();
//        FirebaseToken token = firebaseTokenRepository.findByMember(member);
//        FCMRequest request = FCMRequest.builder()
//                .token(token.getToken())
//                .title("책 반납 안내")
//                .body("오늘까지 반납기일입니다.")
//                .build();
//        try {
//            log.info("saveFCMRequestMessage - 메시지가 저장되었습니다");
//            String json = objectMapper.writeValueAsString(request);
//            redisTemplate.opsForZSet().add("notification_queue", json, limit);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void sendReturnRequest(){
//        long now = Instant.now().getEpochSecond();
//        Set<String> messages = redisTemplate.opsForZSet()
//                .rangeByScore("notification_queue", 0, now);
//        if (messages==null||messages.isEmpty()) return;
//        for (String msg : messages){
//            try {
//                FCMRequest request = objectMapper.readValue(msg, FCMRequest.class);
//                fcmService.sendNotification(request.getToken(), request.getTitle(), request.getBody());
//                redisTemplate.opsForZSet().remove("notification_queue", msg);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

}
