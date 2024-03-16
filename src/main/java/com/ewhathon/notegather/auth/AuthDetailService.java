package com.ewhathon.notegather.auth;
import com.ewhathon.notegather.domain.entity.Student;
import com.ewhathon.notegather.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {
    /* 사용자 정보를 데이터베이스에서 로드 */
    private final StudentRepository studentRepository;

    @Override
    public AuthDetails loadUserByUsername(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        return new AuthDetails(student);
    }
}
